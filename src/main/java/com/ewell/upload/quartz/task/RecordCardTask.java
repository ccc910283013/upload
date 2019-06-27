package com.ewell.upload.quartz.task;

import com.ewell.upload.bean.FybInpInfo;
import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.service.FybInpService;
import com.ewell.upload.service.FybRecordCardService;
import com.ewell.upload.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component(value = "recordCardTask")
public class RecordCardTask {
    @Resource
    private FybRecordCardService fybRecordCardService;
    @Resource
    private FybInpService fybInpService;

    /**
     * 门诊任务
     */
    public void taskOutpEvent() throws Exception{
        //log.info("in-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
        //获取待推病人列表
        List<BaseResponse<FybOutInfo>> infoList = fybRecordCardService.cardEventDeal();
        infoList.forEach(obj->{
            fybRecordCardService.fyRecordDeal(obj);
        });
        //log.info("exit-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
    }

    /**
     * 住院任务
     */
    public void taskInpEvent() throws Exception{
        //log.info("in-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
        //获取待推病人列表
        List<BaseResponse<FybInpInfo>> infoList = fybInpService.cardEventDeal();
        infoList.forEach(obj->{
            fybInpService.fyRecordDeal(obj);
        });
        //log.info("exit-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
    }
}
