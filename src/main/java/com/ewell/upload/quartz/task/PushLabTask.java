package com.ewell.upload.quartz.task;

import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.sub.PushPerson;
import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.service.FybRecordCardService;
import com.ewell.upload.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component(value = "pushLabTask")
public class PushLabTask {
    @Resource
    private FybRecordCardService service;
    @Resource
    private FybPushLabService labService;
    public void taskMonitorEvent(PushPerson person) throws Exception{
        Boolean flag = labService.saveWcFzjc(person);
        if (log.isTraceEnabled()){
            log.info(Thread.currentThread()+"执行上传检验"+(flag?"成功":"失败")+"病人门诊号:"+person.getOutpatientNo());
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
