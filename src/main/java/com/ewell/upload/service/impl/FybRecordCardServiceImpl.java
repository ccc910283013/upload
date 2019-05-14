package com.ewell.upload.service.impl;

import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.bean.FybOutTotal;
import com.ewell.upload.bean.FybWomanMain;
import com.ewell.upload.dao.FybOutTotalDao;
import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.WomanMain;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.service.FybRecordCardService;
import com.ewell.upload.util.JacksonUtil;
import com.ewell.upload.util.StringUtils;
import com.ewell.upload.webservice.FYClient.Mchis;
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
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class FybRecordCardServiceImpl implements FybRecordCardService {
    @Resource
    private FybOutTotalDao fybOutTotalDao;
    @Resource
    private Mchis mchis;
    @Override
    public boolean fyRecordDeal(BaseResponse<FybOutInfo> info){
        Boolean status = false;
        if ("fail".equals(info.getResult())){
            int i = fybOutTotalDao.updateErrorMessage(info.getData().getPatientId(),
                    info.getData().getOutpCheckNo(),
                    info.getMessage());
        }else if("success".equals(info.getResult())){
            int i = fybOutTotalDao.insertOutpInfo(info.getData());
            status = i>0;
        }else if("exists".equals(info.getResult())){
            status = true;
        }else{
            int i = fybOutTotalDao.updateErrorMessage(info.getData().getPatientId(),
                    info.getData().getOutpCheckNo(),
                    "更新时出现了未知异常");
        }
        return status;
    }
    @Override
    public List<BaseResponse<FybOutInfo>> cardEventDeal() {
        List<BaseResponse<FybOutInfo>> outInfoList = new ArrayList<>();
        List<FybOutTotal> totalList = fybOutTotalDao.select();
        totalList.forEach(totalObject->{
            String b = fybOutTotalDao.selectHealthNoByOutpNo(totalObject.getOutpCheckNo()).getHealthcareNo();
            if (StringUtils.isEmpty(b))
            {
                FybWomanMain womanMain = fybOutTotalDao.selectWomanMain(totalObject.getPatientId(),totalObject.getOutpCheckNo());
                /*
                womanMain.setOrgan("");
                womanMain.setOrganCode("");
                womanMain.setRecordOrgan("");
                womanMain.setRecordOrganCode("");
                womanMain.setDoctorCode("");
                womanMain.setDoctor("");
                womanMain.setCheckDate("");
                */
                womanMain.setIdcard("440126196109094249");
                womanMain.setExpectedDate("2017-02-02 00:00:00");
                womanMain.setTel("13958701111");
                womanMain.setLmp("2017-02-02 00:00:00");
                womanMain.setProv("浙江省");
                womanMain.setCounty("苍南");
                womanMain.setAddrCode("1234546");
                womanMain.setAge("30");
                womanMain.setGravidity("1");
                womanMain.setCity("杭州");
                womanMain.setCardNo("330327199901220222");
                womanMain.setCheckDate("");
                BaseRequest<FybWomanMain> req = new BaseRequest<FybWomanMain>();
                req.setData(womanMain);
                req.setSource("womanMain");
                req.setOperate("save");
                req.setRemark("孕妇建卡");
                String resStr = mchis.getMchisHttpSoap11Endpoint().saveData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(req));
                BaseResponse<WomanMain> res = JacksonUtil.json2Bean(resStr,new TypeReference<BaseResponse<WomanMain>>(){});
                BaseResponse<FybOutInfo> infoBody = new BaseResponse<FybOutInfo>();
                FybOutInfo info = new FybOutInfo();
                info.setPatientId(totalObject.getPatientId());
                info.setOutpCheckNo(totalObject.getOutpCheckNo());
                info.setStatus(totalObject.getStatus());
                info.setRegTime(totalObject.getRegTime());
                info.setSysId(res.getData().getSysId());
                info.setHealthcareNo(res.getData().getHealthNo());
                infoBody.setResult(res.getResult());
                infoBody.setMessage(res.getMessage());
                infoBody.setData(info);
                outInfoList.add(infoBody);
                if ("success".equals(res.getResult())){
                    System.out.println(JacksonUtil.bean2Json(resStr));
                }
            }else{
                BaseResponse<FybOutInfo> infoBody = new BaseResponse<FybOutInfo>();
                FybOutInfo info = new FybOutInfo();
                info.setPatientId(totalObject.getPatientId());
                info.setOutpCheckNo(totalObject.getOutpCheckNo());
                info.setStatus(totalObject.getStatus());
                info.setRegTime(totalObject.getRegTime());
                info.setSysId(fybOutTotalDao.selectHealthNoByOutpNo(totalObject.getOutpCheckNo()).getSysId());
                info.setHealthcareNo(fybOutTotalDao.selectHealthNoByOutpNo(totalObject.getOutpCheckNo()).getHealthcareNo());
                infoBody.setResult("exists");
                infoBody.setMessage("查询成功");
                infoBody.setData(info);
                outInfoList.add(infoBody);
            }
        });
        return outInfoList;
    }
}
