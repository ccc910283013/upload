package com.ewell.upload.service.impl;


import com.ewell.upload.bean.FybRisItemInfo;
import com.ewell.upload.dao.FybRisInfoDao;
import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.ComPush;
import com.ewell.upload.dto.data.LoginToken;
import com.ewell.upload.dto.data.RisQuery;
import com.ewell.upload.dto.data.sub.PushExamItem;
import com.ewell.upload.dto.data.sub.PushPerson;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.service.FybPushExamService;
import com.ewell.upload.util.DateUtil;
import com.ewell.upload.util.JacksonUtil;
import com.ewell.upload.util.StringUtils;
import com.ewell.upload.webservice.FYClientPro.Mchis;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class FybPushExamServiceImpl implements FybPushExamService {
    @Resource
    private FybRisInfoDao dao;
    @Resource
    private Mchis mchis;
    /**
     * 获取检查结果待推列表
     * @return 返回检查结果待推列表
     */
    @Override
    public BaseResponse<List<PushPerson>> queryPersonWcQtjc() {
        BaseRequest<RisQuery> req = new BaseRequest<>();//创建申请对象
        RisQuery query = new RisQuery();
        //设置查询范围 checkDate1 --> checkDate2
        query.setCheckDate1(DateUtil.timeStampToDate(DateUtil.addDate(new Date(),-2).getTime(),"yyyy-MM-dd"));
        query.setCheckDate2(DateUtil.timeStampToDate(DateUtil.addDate(new Date(),1).getTime(),"yyyy-MM-dd"));
        //-----------------------------------
        String loginStr = mchis.getMchisHttpSoap11Endpoint().login("320211196412053427","123");
        BaseResponse<LoginToken> res = JacksonUtil.json2Bean(loginStr, new TypeReference<BaseResponse<LoginToken>>(){});
        LoginToken l = res.getData();
        //-----------------------------------
        //query.setInputOrganCode(QuartzJobListener.token.getInputOrganCode());//获取接口连接token
        query.setInputOrganCode(l.getInputOrganCode());//获取接口连接token
        req.setRemark("围产其他检查");
        req.setOperate("queryPersonWcQtjc");
        req.setSource("comCheck");
        req.setData(query);
//        String resStr = mchis.getMchisHttpSoap11Endpoint().//查询妇幼检验结果待推列表
//        getData(l.getToken(),JacksonUtil.bean2Json(req));
        String resStr = mchis.getMchisHttpSoap11Endpoint().//查询妇幼检验结果待推列表
                getData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(req));
        return JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse<List<PushPerson>>>() {});
    }

    @Override
    public boolean saveWcQtjc(PushPerson person) {
        boolean status = false;
        if (StringUtils.isEmpty(person.getOutpatientNo())){
            return false;
        }
        List<FybRisItemInfo> infoList = dao.selectByOutpId(person.getOutpatientNo());//根据门诊号,查询病人检验结果
        if (infoList.size()<1){
            return false;
        }
        BaseRequest<ComPush<PushExamItem>> req = new BaseRequest<>();//创建请求对象
        ComPush<PushExamItem> cl = new ComPush();
        List<PushExamItem> result = new ArrayList<>();
        infoList.forEach(info->{ //将检验结果转成待推的请求格式
            PushExamItem item = new PushExamItem();
            item.setItemCode(info.getFybResItemCode());
            item.setItemResult(info.getResult());
            result.add(item);
        });
        cl.setResult(result);
        cl.setPerson(person);
        req.setData(cl);
        req.setSource("comCheck");
        req.setOperate("saveWcQtjc");
        req.setRemark("围产其他检查");
        //System.out.println(JacksonUtil.bean2Json(req));
        String resStr = mchis.getMchisHttpSoap11Endpoint().//推送病人检验结果
                saveData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(req));
        BaseResponse res = JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse>() {});//获取响应
        //System.out.println(JacksonUtil.bean2Json(req));
        //System.out.println(resStr);
        if ("success".equals(res.getResult()))
        {
            //如果发送成功,设置请求成功
            status = true;
        }
        return status;
    }
}
