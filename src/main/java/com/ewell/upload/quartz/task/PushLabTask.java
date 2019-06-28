package com.ewell.upload.quartz.task;

import com.ewell.upload.dto.data.push.PushPerson;
import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.service.FybRecordCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component(value = "pushLabTask")
public class PushLabTask {

    @Resource
    private FybPushLabService labService;
    public void taskMonitorEvent(PushPerson person) throws Exception{
        Boolean flag = labService.saveWcFzjc(person);
        if (log.isInfoEnabled()){
            log.info(Thread.currentThread()+"执行上传检验"+(flag?"成功":"失败")+"病人门诊号:"+person.getOutpatientNo());
        }
    }


}
