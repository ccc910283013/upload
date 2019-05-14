package com.ewell.upload.service.impl;

import com.ewell.upload.bean.FybLisItemInfo;
import com.ewell.upload.dao.FybLisInfoDao;
import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.ComLis;
import com.ewell.upload.dto.data.LisQuery;
import com.ewell.upload.dto.data.sub.PushLabItem;
import com.ewell.upload.dto.data.sub.PushPerson;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.util.DateUtil;
import com.ewell.upload.util.JacksonUtil;
import com.ewell.upload.webservice.FYClient.Mchis;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class FybPushLabServiceImpl implements FybPushLabService {
    @Resource
    private FybLisInfoDao dao;
    @Resource
    private Mchis mchis;
    @Override
    public BaseResponse<List<PushPerson>> queryPersonWcFzjc() {
        BaseRequest<LisQuery> req = new BaseRequest<>();
        LisQuery query = new LisQuery();
        query.setCheckDate1(DateUtil.timeStampToDate(DateUtil.addDate(new Date(),-3).getTime(),"yyyy-MM-dd"));
        query.setCheckDate2(DateUtil.getCurrentTime("yyyy-MM-dd"));
        query.setInputOrganCode(QuartzJobListener.token.getInputOrganCode());
        req.setRemark("围产辅助检查");
        req.setOperate("queryPersonWcFzjc");
        req.setSource("comLis");
        req.setData(query);
        System.out.println(JacksonUtil.bean2Json(req));
        String resStr = mchis.getMchisHttpSoap11Endpoint().getData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(req));
        return JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse<List<PushPerson>>>() {});
    }

    @Override
    public boolean saveWcFzjc(PushPerson person) {
        boolean status = false;
        BaseRequest<ComLis> req = new BaseRequest<>();
        ComLis cl = new ComLis();
        List<PushLabItem> result = new ArrayList<>();
        List<FybLisItemInfo> infoList = dao.selectByPatientId(person.getPatientNo());
        infoList.forEach(info->{
            PushLabItem item = new PushLabItem();
            item.setItemCode(info.getFybResItemCode());
            item.setItemResult(info.getResult());
            result.add(item);
        });
        cl.setResult(result);
        cl.setPerson(person);
        req.setData(cl);
        req.setSource("comLis");
        req.setOperate("围产辅助检查");
        req.setRemark("saveWcFzjc");
        //System.out.println(JacksonUtil.bean2Json(req));
        String resStr = mchis.getMchisHttpSoap11Endpoint().saveData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(req));
        BaseResponse res = JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse>() {});
        if ("success".equals(res.getResult()))
        {
            status = true;
        }
        return status;
    }
}
