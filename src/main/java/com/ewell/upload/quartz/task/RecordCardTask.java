package com.ewell.upload.quartz.task;

import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.service.FybRecordCardService;
import com.ewell.upload.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
//@Component(value = "recordCardTask")
public class RecordCardTask {
    @Resource
    private FybRecordCardService service;
    public void taskMonitorEvent() throws Exception{
        log.info("in-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
        /*
        List<BaseResponse<FybOutInfo>> infoList = service.cardEventDeal();
        infoList.forEach(obj->{
            boolean flag = service.fyRecordDeal(obj);
            if (flag){
                System.out.println("建档成功"+obj.getData().getPatientId());
            }else{
                System.out.println("未完成建档"+obj.getData().getPatientId());
            }
        });
        */
        log.info("exit-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
    }
}
