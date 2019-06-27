package com.ewell.upload.quartz.util;

import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.service.FybRecordCardService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class FybUploadJob extends QuartzJobBean {
    @Resource
    private FybRecordCardService service;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取待推病人列表
        List<BaseResponse<FybOutInfo>> infoList = service.cardEventDeal();
        infoList.forEach(obj->{
            boolean flag = service.fyRecordDeal(obj);
        });

    }
}
