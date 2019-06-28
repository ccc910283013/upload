package com.ewell.upload.quartz.task;

import com.ewell.upload.dto.data.push.PushPerson;
import com.ewell.upload.service.FybPushExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component(value = "pushExamTask")
public class PushExamTask {
    @Resource
    private FybPushExamService examService;
    public void taskMonitorEvent(PushPerson person) throws Exception{
        Boolean flag = examService.saveWcQtjc(person);
        if (log.isInfoEnabled()){
            log.info(Thread.currentThread()+"执行上传检查"+(flag?"成功":"失败")+"病人门诊号:"+person.getOutpatientNo());
        }
    }

}
