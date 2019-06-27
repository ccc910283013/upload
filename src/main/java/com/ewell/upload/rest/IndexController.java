package com.ewell.upload.rest;


import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.GetHealthNo;
import com.ewell.upload.dto.data.LoginToken;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.util.JacksonUtil;
import com.ewell.upload.webservice.FYClientPro.Mchis;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class IndexController {
    @GetMapping(value = "getWomanMain",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String getWomanMain(@RequestParam(value = "idNo") String idNo){
        String loginStr = Mchis.getInstance().getMchisHttpSoap11Endpoint().login("320211196412053427","123");
        BaseResponse<LoginToken> res = JacksonUtil.json2Bean(loginStr, new TypeReference<BaseResponse<LoginToken>>(){});
        BaseRequest<GetHealthNo> req = new BaseRequest<>();//创建申请请求
        req.setData(new GetHealthNo(idNo));
        req.setSource("womanMain");
        req.setOperate("get");
        req.setRemark("孕妇建卡");
        return Mchis.getInstance().getMchisHttpSoap11Endpoint().getData(QuartzJobListener.token.getToken(), JacksonUtil.bean2Json(req));
    }
}
