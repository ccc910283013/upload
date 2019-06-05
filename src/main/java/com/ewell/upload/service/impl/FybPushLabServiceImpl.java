package com.ewell.upload.service.impl;

import com.ewell.upload.bean.FybLisItemInfo;
import com.ewell.upload.dao.FybLisInfoDao;
import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.ComPush;
import com.ewell.upload.dto.data.LisQuery;
import com.ewell.upload.dto.data.sub.PushLabItem;
import com.ewell.upload.dto.data.sub.PushPerson;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.service.FybPushLabService;
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
public class FybPushLabServiceImpl implements FybPushLabService {
    @Resource
    private FybLisInfoDao dao;
    @Resource
    private Mchis mchis;

    /**
     * 获取检验结果待推列表
     * @return 返回检验结果待推列表
     */
    @Override
    public BaseResponse<List<PushPerson>> queryPersonWcFzjc(){
        BaseRequest<LisQuery> req = new BaseRequest<>();//创建申请对象
        LisQuery query = new LisQuery();
        //设置查询范围 checkDate1 --> checkDate2
        query.setCheckDate1(DateUtil.timeStampToDate(DateUtil.addDate(new Date(),-2).getTime(),"yyyy-MM-dd"));
        query.setCheckDate2(DateUtil.timeStampToDate(DateUtil.addDate(new Date(),1).getTime(),"yyyy-MM-dd"));
        //query.setCheckDate1(DateUtil.timeStampToDate(DateUtil.addDate(new Date(),-2).getTime(),"yyyy-MM-dd"));
        //query.setCheckDate2(DateUtil.timeStampToDate(DateUtil.addDate(new Date(),1).getTime(),"yyyy-MM-dd"));
        query.setInputOrganCode(QuartzJobListener.token.getInputOrganCode());//获取接口连接token
        req.setRemark("围产辅助检查");
        req.setOperate("queryPersonWcFzjc");
        req.setSource("comLis");
        req.setData(query);
        //System.out.println(JacksonUtil.bean2Json(req));
        String resStr = mchis.getMchisHttpSoap11Endpoint().//查询妇幼检验结果待推列表
                getData(QuartzJobListener.token.getToken(),JacksonUtil.bean2Json(req));
        //System.out.println(resStr);
        return JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse<List<PushPerson>>>() {});
    }

    /**
     *  推送检验结果
     * @param person 待推检验病人信息
     * @return 推送结果
     */
    @Override
    public boolean saveWcFzjc(PushPerson person) {
        boolean status = false;
        if (StringUtils.isEmpty(person.getOutpatientNo())){
            return false;
        }
        List<FybLisItemInfo> infoList = dao.selectByOutpId(person.getOutpatientNo());//根据门诊号,查询病人检验结果
        if (infoList.size()<1){
            return false;
        }
        BaseRequest<ComPush<PushLabItem>> req = new BaseRequest<>();//创建请求对象
        ComPush<PushLabItem> cl = new ComPush();
        List<PushLabItem> result = new ArrayList<>();
        infoList.forEach(info->{ //将检验结果转成待推的请求格式
            PushLabItem item = new PushLabItem();
            item.setItemCode(info.getFybResItemCode());
            item.setItemResult(info.getResult());
            result.add(item);
        });
        cl.setResult(result);
        cl.setPerson(person);
        req.setData(cl);
        req.setSource("comLis");
        req.setOperate("saveWcFzjc");
        req.setRemark("围产辅助检查");
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
