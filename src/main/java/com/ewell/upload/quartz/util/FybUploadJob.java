package com.ewell.upload.quartz.util;

import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.sub.PushPerson;
import com.ewell.upload.service.FybPushExamService;
import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.service.FybRecordCardService;
import com.ewell.upload.util.DateUtil;
import com.ewell.upload.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FybUploadJob extends QuartzJobBean {
    @Resource
    private FybRecordCardService service;
    @Resource
    private FybPushLabService labService;
    @Resource
    private FybPushExamService examService;
    @Resource
    private ThreadPoolTaskExecutor executor;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取待推病人列表
        List<BaseResponse<FybOutInfo>> infoList = service.cardEventDeal();
        //List<BaseResponse<FybOutInfo>> infoList = service.test();
        infoList.forEach(obj->{
            boolean flag = service.fyRecordDeal(obj);
        });
        //合并的逻辑
        BaseResponse<List<PushPerson>> resObject = labService.queryPersonWcFzjc();
        if ("success".equals(resObject.getResult())){
            //List<List<PushPerson>> personListasList = ListUtil.averageAssign(resObject.getData(),5);
            resObject.getData().forEach(person->{
                try {
                    QuartzRunnable runnable = new QuartzRunnable("pushLabTask", "taskMonitorEvent", person);
                    executor.execute(runnable);
                }catch (NoSuchMethodException e){
                    log.error(e.getMessage());
                }
            });
            resObject.getData().forEach(person->{
                try {
                    QuartzRunnable runnable = new QuartzRunnable("pushExamTask", "taskMonitorEvent", person);
                    executor.execute(runnable);
                }catch (NoSuchMethodException e){
                    log.error(e.getMessage());
                }
            });
        }else {
            log.info("查询待推病人检查检验列表失败------->"+resObject.getMessage());
        }

    }
}
