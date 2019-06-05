package com.ewell.upload.quartz.task;

import com.ewell.upload.dto.data.sub.PushPerson;
import com.ewell.upload.service.FybPushExamService;
import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.service.FybRecordCardService;
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
        if (log.isTraceEnabled()){
            log.info(Thread.currentThread()+"执行上传检查"+(flag?"成功":"失败")+"病人门诊号:"+person.getOutpatientNo());
        }
    }

    /*
            //获取待推病人列表
        List<BaseResponse<FybOutInfo>> infoList = service.cardEventDeal();
        infoList.forEach(obj->{
            boolean flag = service.fyRecordDeal(obj);
        });

        //合并的逻辑
        BaseResponse<List<PushPerson>> resObject = labService.queryPersonWcFzjc();
        if ("success".equals(resObject.getResult())){
            resObject.getData().forEach(person->{
                boolean flag = labService.saveWcFzjc(person);
                if (!flag){
                    log.info("病人门诊号:"+person.getOutpatientNo()+"未出检验结果");
                }
            });
        }else {
            log.info("查询待推病人列表失败------->"+resObject.getMessage());
        }
     */
}
