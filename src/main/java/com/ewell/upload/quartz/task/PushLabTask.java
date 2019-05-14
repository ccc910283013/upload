package com.ewell.upload.quartz.task;

import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.sub.PushPerson;
import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component(value = "pushLabTask")
public class PushLabTask {
    @Resource
    private FybPushLabService service;
    public void taskMonitorEvent() throws Exception{
        log.info("in-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
        BaseResponse<List<PushPerson>> resObject = service.queryPersonWcFzjc();
        if ("success".equals(resObject.getResult())){
            resObject.getData().forEach(person->{
                boolean flag = service.saveWcFzjc(person);
                if (!flag){
                    log.info("病人"+person.getPatientNo()+"检验项目推送失败");
                }else{
                    log.info("病人"+person.getPatientNo()+"推送成功");
                }
            });
        }else {
            log.info("当天没有待推数据");
        }
        log.info("exit-------------------------------"+DateUtil.getCurrentTime()+" "+Thread.currentThread());
    }

}
