package com.ewell.upload.service.impl;

import com.ewell.upload.bean.*;
import com.ewell.upload.dao.FybInpTotalDao;
import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.ChildMain;
import com.ewell.upload.dto.data.GetHealthNo;
import com.ewell.upload.dto.data.WomanDelivery;
import com.ewell.upload.dto.data.pull.PullSysId;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.service.FybInpService;
import com.ewell.upload.util.DateUtil;
import com.ewell.upload.util.JacksonUtil;
import com.ewell.upload.util.StringUtils;
import com.ewell.upload.util.UploadConstant;
import com.ewell.upload.webservice.client.Mchis;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class FybInpServiceImpl implements FybInpService {
    @Resource
    private FybInpTotalDao fybInpTotalDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public Boolean fyRecordDeal(BaseResponse<FybInpInfo> fybInpInfo) {
        Boolean status = false;
        fybInpInfo.getData().setException("success".equals(fybInpInfo.getResult())?"":fybInpInfo.getMessage());
        fybInpInfo.getData().setDealStatus("success".equals(fybInpInfo.getResult())?"0":"2");
        if(fybInpTotalDao.selectHealthNoByInpNo(fybInpInfo.getData().getInpNo())==null){
            int i = fybInpTotalDao.insertInpInfo(fybInpInfo.getData());
            status = i>0;
        }else{
            int i = fybInpTotalDao.updateException(fybInpInfo.getData().getInpNo(),fybInpInfo.getMessage(),fybInpInfo.getData().getDealStatus());
            status = i>0;
        }
        if (status){
            //删除待建档病人列表
            int i = fybInpTotalDao.deleteByInpNo(fybInpInfo.getData().getInpNo());
            status = i >0;
        }
        return status;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<BaseResponse<FybInpInfo>> cardEventDeal() {
        Boolean status = false;
        List<BaseResponse<FybInpInfo>> fybInpInfoList = new ArrayList<>();
        List<FybInpTotal> fybInpTotals = fybInpTotalDao.select();
        if(fybInpTotals.size()>0){
            fybInpTotalDao.updateDealStatus(fybInpTotals);
            fybInpTotals.forEach(fybInpTotal -> {
                Boolean healthNoFlag = false;//是否有保健号标识
                BaseResponse<FybInpInfo> resBase = new BaseResponse<>();
                final FybInpInfo fybInpInfo;
                FybInpInfo fybInpInfoSelect = fybInpTotalDao.selectHealthNoByInpNo(fybInpTotal.getInpNo());
                if (StringUtils.isNotEmpty(fybInpTotal.getIdNo())) {
                    if (fybInpInfoSelect == null) {
                        fybInpInfo = new FybInpInfo();
                        fybInpInfo.setInpNo(fybInpTotal.getInpNo());
                        fybInpInfo.setSaveTime(fybInpTotal.getSaveTime());
                        fybInpInfo.setSysId("");
                        fybInpInfo.setHealthcareNo("");
                        fybInpInfo.setIdNo(fybInpTotal.getIdNo());
                        BaseRequest<GetHealthNo> req = new BaseRequest<>();//创建申请请求
                        req.setData(new GetHealthNo(fybInpInfo.getIdNo()));
                        req.setSource("womanMain");
                        req.setOperate("get");
                        req.setRemark("孕妇建卡");
                        //调阅病人保健号
                        String resStr = Mchis.getInstance().getMchisHttpSoap11Endpoint().getData(QuartzJobListener.token.getToken(), JacksonUtil.bean2Json(req));
                        BaseResponse<List<FybWomanMain>> res = JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse<List<FybWomanMain>>>() {});
                        if (null == res) {
                            resBase.setResult(UploadConstant.FAIL);
                            resBase.setMessage("调阅保健号失败");
                            resBase.setData(fybInpInfo);
                            fybInpInfoList.add(resBase); //如果没有调阅到保健号，则直接跳过当前数据
                            return;
                        }
                        if ("success".equals(res.getResult())){
                            fybInpInfo.setHealthcareNo(res.getData().get(0).getHealthNo());
                            healthNoFlag = true;
                        }
                    }else{
                        fybInpInfo = fybInpInfoSelect;
                        healthNoFlag = true;
                    }
                    if(healthNoFlag){
                        FybMaternalDelivery fybMaternalDelivery = fybInpTotalDao.selectWomanDelivery(fybInpTotal.getInpNo());
                        if (null==fybMaternalDelivery){
                            resBase.setResult(UploadConstant.FAIL);
                            resBase.setMessage("查询分娩信息失败");
                            resBase.setData(fybInpInfo);
                            fybInpInfoList.add(resBase); //如果没有获取到分娩信息，则直接跳过当前数据
                            return;
                        }
                        WomanDelivery womanDelivery = new WomanDelivery(fybMaternalDelivery);
                        womanDelivery.setSrc("无锡人民医院");
                        womanDelivery.setOrganCode(QuartzJobListener.token.getInputOrganCode());
                        womanDelivery.setOrgan(QuartzJobListener.token.getInputOrganName());
                        womanDelivery.setInputOrgan(QuartzJobListener.token.getInputOrganName());
                        womanDelivery.setInputOrganCode(QuartzJobListener.token.getInputOrganCode());
                        womanDelivery.setInputDoctorCode(QuartzJobListener.token.getUserIdcard());
                        womanDelivery.setInputDoctor(QuartzJobListener.token.getUserName());
                        womanDelivery.setInputDate(DateUtil.getCurrentTime("yyyy-MM-dd"));
                        womanDelivery.setIdcard(fybInpTotal.getIdNo());
                        womanDelivery.setCardType("01");
                        womanDelivery.setCardNo(fybInpTotal.getIdNo());
                        womanDelivery.setHealthNo(fybInpInfo.getHealthcareNo());
                        womanDelivery.setDoctor(QuartzJobListener.token.getUserName());
                        womanDelivery.setDoctorCode(QuartzJobListener.token.getUserIdcard());
                        womanDelivery.setHealthNo(fybInpInfo.getHealthcareNo());
                        womanDelivery.setSysId(fybInpInfo.getSysId());//系统唯一号,第一次为空,第二次有值
                        BaseRequest<WomanDelivery> reqWomanDelivery = new BaseRequest<>();
                        reqWomanDelivery.setData(womanDelivery);
                        reqWomanDelivery.setSource("womanDelivery");
                        reqWomanDelivery.setOperate("save");
                        reqWomanDelivery.setRemark("孕妇分娩");
                        //推送孕妇分娩信息
                        String resWomanDelivery = Mchis.getInstance().getMchisHttpSoap11Endpoint().
                                saveData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(reqWomanDelivery));
                        BaseResponse<PullSysId> resPullSysId = JacksonUtil.json2Bean(resWomanDelivery, new TypeReference<BaseResponse<PullSysId>>() {});
                        if (null == resPullSysId){
                            resBase.setResult(UploadConstant.FAIL);
                            resBase.setMessage("推送分娩信息失败");
                            resBase.setData(fybInpInfo);
                            fybInpInfoList.add(resBase); //如果推送分娩信息失败，则直接跳过当前数据
                            return;
                        }
                        if ("success".equals(resPullSysId.getResult())){
                            StringBuffer sb = new StringBuffer();
                            resBase.setData(fybInpInfo);
                            List<FybNewBorn> fybNewBorns = fybInpTotalDao.selectAllNewBorn(fybInpTotal.getInpNo());
                            fybNewBorns.forEach(fybNewBorn -> {
                                ChildMain childMain = new ChildMain(fybNewBorn);
                                childMain.setSrc("无锡人民医院");
                                childMain.setOrganCode(QuartzJobListener.token.getInputOrganCode());
                                childMain.setOrgan(QuartzJobListener.token.getInputOrganName());
                                childMain.setInputOrgan(QuartzJobListener.token.getInputOrganName());
                                childMain.setInputOrganCode(QuartzJobListener.token.getInputOrganCode());
                                childMain.setInputDoctorCode(QuartzJobListener.token.getUserIdcard());
                                childMain.setInputDoctor(QuartzJobListener.token.getUserName());
                                childMain.setInputDate(DateUtil.getCurrentTime("yyyy-MM-dd"));
                                childMain.setIdcard(fybInpTotal.getIdNo());
                                childMain.setCardType("01");
                                childMain.setCardNo(fybInpTotal.getIdNo());
                                childMain.setDoctor(QuartzJobListener.token.getUserName());
                                childMain.setDoctorCode(QuartzJobListener.token.getUserIdcard());
                                childMain.setHealthNo(fybInpInfo.getHealthcareNo()+fybNewBorn.getBabyNo());
                                childMain.setMumHealthNo(fybInpInfo.getHealthcareNo());
                                childMain.setSysId(fybInpInfo.getSysId());//系统唯一号,第一次为空,第二次有值
                                BaseRequest<ChildMain> reqChildMain = new BaseRequest<>();
                                reqChildMain.setData(childMain);
                                reqChildMain.setOperate("saveXse");
                                reqChildMain.setSource("childMain");
                                reqChildMain.setRemark("新生儿");
                                String resChildMain = Mchis.getInstance().getMchisHttpSoap11Endpoint().
                                        saveData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(reqChildMain));
                                BaseResponse<PullSysId> resPullSysIdSub = JacksonUtil.json2Bean(resChildMain, new TypeReference<BaseResponse<PullSysId>>() {});
                                if (null == resPullSysIdSub){
                                    sb.append("病人新生儿推送失败,保健号:");
                                    sb.append(childMain.getHealthNo());
                                    sb.append(";");
                                    return;
                                }
                                if ("fail".equals(resPullSysIdSub.getResult())){
                                    sb.append("病人新生儿推送失败,保健号:");
                                    sb.append(childMain.getHealthNo());
                                    sb.append(";");
                                }
                            });
                            if (StringUtils.isEmpty(sb.toString())){
                                resBase.setResult(UploadConstant.SUCCESS);
                                resBase.setMessage("推送成功");
                                resBase.setData(fybInpInfo);
                                fybInpInfoList.add(resBase);
                            }else{
                                resBase.setResult(UploadConstant.FAIL);
                                resBase.setMessage(sb.toString());
                                resBase.setData(fybInpInfo);
                                fybInpInfoList.add(resBase);
                            }
                        }else {
                            resBase.setResult(UploadConstant.FAIL);
                            resBase.setMessage("推送分娩信息失败");
                            resBase.setData(fybInpInfo);
                            fybInpInfoList.add(resBase); //如果推送分娩信息失败，则直接跳过当前数据
                        }
                    }
                }else{//如果病人没有身份证号
                    fybInpInfo = new FybInpInfo();
                    fybInpInfo.setInpNo(fybInpTotal.getInpNo());
                    fybInpInfo.setSaveTime(fybInpTotal.getSaveTime());
                    fybInpInfo.setSysId("");
                    fybInpInfo.setHealthcareNo("");
                    fybInpInfo.setIdNo(fybInpTotal.getIdNo());
                    resBase.setResult(UploadConstant.FAIL);
                    resBase.setMessage("获取病人身份证号失败");
                    resBase.setData(fybInpInfo);
                    fybInpInfoList.add(resBase);
                }
            });
        }
        return fybInpInfoList;
    }
}
