package com.ewell.upload.service.impl;

import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.bean.FybOutTotal;
import com.ewell.upload.bean.FybWomanCheck;
import com.ewell.upload.bean.FybWomanMain;
import com.ewell.upload.dao.FybOutTotalDao;
import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.GetHealthNo;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.service.FybRecordCardService;
import com.ewell.upload.util.DateUtil;
import com.ewell.upload.util.JacksonUtil;
import com.ewell.upload.util.StringUtils;
import com.ewell.upload.util.UploadConstant;
import com.ewell.upload.webservice.FYClientPro.Mchis;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.tools.rngom.parse.host.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class FybRecordCardServiceImpl implements FybRecordCardService {
    @Resource
    private FybOutTotalDao fybOutTotalDao;
    //@Resource
    //private Mchis mchis;

//    @Resource
//    private TestServiceImpl testImpl;

    @Override
    public List<BaseResponse<FybOutInfo>> test() {
        List<BaseResponse<FybOutInfo>> outInfoList = new ArrayList<>();
        //查询门诊待上传病人
        List<FybOutTotal> totalList = fybOutTotalDao.select();
        totalList.forEach(totalObject->{
            if (StringUtils.isNotEmpty(totalObject.getIdNo())) {
                BaseRequest<GetHealthNo> req = new BaseRequest<>();//创建申请请求
                req.setData(new GetHealthNo(totalObject.getIdNo()));
                req.setSource("womanMain");
                req.setOperate("get");
                req.setRemark("孕妇建卡");
                //System.out.println("get建卡信息"+JacksonUtil.bean2Json(req));
                //调阅病人保健号
                //log.debug("send Fy womanMain----->"+JacksonUtil.bean2Json(req));
                String resStr = Mchis.getInstance().getMchisHttpSoap11Endpoint().getData(QuartzJobListener.token.getToken(), JacksonUtil.bean2Json(req));
                //log.debug("resp Fy womanMain----->"+resStr);
                BaseResponse<List<FybWomanMain>> res = JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse<List<FybWomanMain>>>() {
                });
                BaseResponse<FybOutInfo> infoBody = new BaseResponse<FybOutInfo>();
                FybOutInfo info = new FybOutInfo();
                info.setPatientId(totalObject.getPatientId());
                info.setOutpCheckNo(totalObject.getOutpCheckNo());
                info.setStatus(totalObject.getStatus());
                info.setRegTime(totalObject.getRegTime());
                info.setIdNo(totalObject.getIdNo());
                if ("success".equals(res.getResult())) {
                    infoBody.setMessage("成功");
                    infoBody.setResult("success");
                    info.setSysId(res.getData().get(0).getSysId());
                    info.setHealthcareNo(res.getData().get(0).getHealthNo());
                    infoBody.setData(info);
                    outInfoList.add(infoBody);
                }else {
                    infoBody.setMessage("调阅病人保健号失败");
                    infoBody.setResult(res.getResult());
                    infoBody.setData(info);
                    outInfoList.add(infoBody);
                }
            }
        });
        return outInfoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean fyRecordDeal(BaseResponse<FybOutInfo> info){
        Boolean status = false;
        if ("fail".equals(info.getResult())){
            int i = fybOutTotalDao.updateErrorMessage(info.getData().getPatientId(),
                    info.getData().getOutpCheckNo(),
                    info.getMessage(),"2");
        }else if("success".equals(info.getResult())){
            int i = fybOutTotalDao.insertOutpInfo(info.getData());
            status = i>0;
        }else if("exists".equals(info.getResult())){
            status = true;
        }else{
            int i = fybOutTotalDao.updateErrorMessage(info.getData().getPatientId(),
                    info.getData().getOutpCheckNo(),
                    "更新时出现了未知异常","2");
        }
        if (status){
            //删除待建档病人列表
            int i = fybOutTotalDao.deleteByOutp(info.getData().getOutpCheckNo());
            status = i >0;
        }
        return status;
    }
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<BaseResponse<FybOutInfo>> cardEventDeal() {
        fybOutTotalDao.update();
        List<BaseResponse<FybOutInfo>> outInfoList = new ArrayList<>();
        //查询门诊待上传病人
        List<FybOutTotal> totalList = fybOutTotalDao.select();
        if (totalList.size()>0){
            fybOutTotalDao.updateDealStatus(totalList);
        }
        totalList.forEach(totalObject->{
            if (StringUtils.isNotEmpty(totalObject.getIdNo())) {
                if (fybOutTotalDao.selectHealthNoByOutpNo(totalObject.getOutpCheckNo()) == null)//判断是否存在当前病人
                {
                    BaseRequest<GetHealthNo> req = new BaseRequest<>();//创建申请请求
                    req.setData(new GetHealthNo(totalObject.getIdNo()));
                    req.setSource("womanMain");
                    req.setOperate("get");
                    req.setRemark("孕妇建卡");
                    //System.out.println("get建卡信息"+JacksonUtil.bean2Json(req));
                    //调阅病人保健号
                    //log.debug("send Fy womanMain----->"+JacksonUtil.bean2Json(req));
                    String resStr = Mchis.getInstance().getMchisHttpSoap11Endpoint().getData(QuartzJobListener.token.getToken(), JacksonUtil.bean2Json(req));
                    //log.debug("resp Fy womanMain----->"+resStr);
                    BaseResponse<List<FybWomanMain>> res = JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse<List<FybWomanMain>>>() {
                    });
                    BaseResponse<FybOutInfo> infoBody = new BaseResponse<FybOutInfo>();
                    FybOutInfo info = new FybOutInfo();
                    info.setPatientId(totalObject.getPatientId());
                    info.setOutpCheckNo(totalObject.getOutpCheckNo());
                    info.setStatus(totalObject.getStatus());
                    info.setRegTime(totalObject.getRegTime());
                    info.setIdNo(totalObject.getIdNo());
                    //如果调阅成功
                    if ("success".equals(res.getResult())) {
                        //System.out.println(JacksonUtil.bean2Json(resStr));
                        FybWomanCheck check = fybOutTotalDao.selectWomanCheckByOutpId(totalObject.getOutpCheckNo());
                        if (null==check){
                            infoBody.setData(info);
                            infoBody.setResult(UploadConstant.FAIL);
                            infoBody.setMessage("不存在病历信息");
                            outInfoList.add(infoBody);
                            return;
                        }
                        //FybWomanCheck check = testImpl.test(totalObject.getOutpCheckNo());
                        check.setOrganCode(QuartzJobListener.token.getInputOrganCode());
                        check.setOrgan(QuartzJobListener.token.getInputOrganName());
                        check.setInputOrgan(QuartzJobListener.token.getInputOrganName());
                        check.setInputOrganCode(QuartzJobListener.token.getInputOrganCode());
                        check.setInputDoctorCode(QuartzJobListener.token.getUserIdcard());
                        check.setInputDoctor(QuartzJobListener.token.getUserName());
                        check.setInputDate(DateUtil.getCurrentTime("yyyy-MM-dd"));
                        check.setIdcard(totalObject.getIdNo());
                        check.setCardType("01");
                        check.setCardNo(totalObject.getIdNo());
                        check.setHealthNo(res.getData().get(0).getHealthNo());
                        //check.setGestDays("1");
                        //check.setSureDays("1");
                        BaseRequest<FybWomanCheck> reqCheck = new BaseRequest<>();
                        reqCheck.setData(check);
                        reqCheck.setRemark("孕妇产检");
                        reqCheck.setOperate("save");
                        reqCheck.setSource("womanCheck");
                        info.setSysId(res.getData().get(0).getSysId());//暂不做建档，目前为调阅sysId，无用
                        info.setHealthcareNo(res.getData().get(0).getHealthNo());
                        infoBody.setData(info);
                        //推送产前检查信息
                        log.debug("send Fy womanCheck----->"+JacksonUtil.bean2Json(reqCheck));
                        String resStrCheck = Mchis.getInstance().getMchisHttpSoap11Endpoint().saveData(QuartzJobListener.token.getToken(), JacksonUtil.bean2Json(reqCheck));
                        log.debug("resp Fy womanCheck----->"+resStr);
                        BaseResponse resCheck = JacksonUtil.json2Bean(resStrCheck, new TypeReference<BaseResponse>() {
                        });
                        if ("success".equals(resCheck.getResult())) {
                            infoBody.setResult(res.getResult());
                            infoBody.setMessage(res.getMessage());
                        } else {
                            infoBody.setResult(UploadConstant.FAIL);
                            infoBody.setMessage(resCheck.getMessage());
                        }
                    } else {
                        info.setSysId("");//暂不做建档，目前为调阅sysId，无用
                        info.setHealthcareNo("");
                        infoBody.setData(info);
                        infoBody.setResult(UploadConstant.FAIL);
                        infoBody.setMessage("调阅病人保健号失败");
                    }
                    outInfoList.add(infoBody);
                } else {
                    //如果存在当前病人,则无需调阅
                    BaseResponse<FybOutInfo> infoBody = new BaseResponse<FybOutInfo>();
                    FybOutInfo info = new FybOutInfo();
                    info.setPatientId(totalObject.getPatientId());
                    info.setOutpCheckNo(totalObject.getOutpCheckNo());
                    info.setStatus(totalObject.getStatus());
                    info.setRegTime(totalObject.getRegTime());
                    info.setIdNo(totalObject.getIdNo());
                    info.setSysId(fybOutTotalDao.selectHealthNoByOutpNo(totalObject.getOutpCheckNo()).getSysId());//暂不做建档，目前为调阅sysId，无用
                    info.setHealthcareNo(fybOutTotalDao.selectHealthNoByOutpNo(totalObject.getOutpCheckNo()).getHealthcareNo());
                    infoBody.setResult("exists");
                    infoBody.setMessage("查询成功");
                    infoBody.setData(info);
                    outInfoList.add(infoBody);
                }
            }else{//如果病人没有身份证号
                BaseResponse<FybOutInfo> infoBody = new BaseResponse<FybOutInfo>();
                FybOutInfo info = new FybOutInfo();
                info.setPatientId(totalObject.getPatientId());
                info.setOutpCheckNo(totalObject.getOutpCheckNo());
                info.setStatus(totalObject.getStatus());
                info.setRegTime(totalObject.getRegTime());
                info.setIdNo(totalObject.getIdNo());
                infoBody.setResult(UploadConstant.FAIL);
                infoBody.setMessage("病人ID_NO为空");
                infoBody.setData(info);
                outInfoList.add(infoBody);
            }
        });
        return outInfoList;
    }
}
