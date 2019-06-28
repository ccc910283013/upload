package com.ewell.upload.quartz.util;

import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.push.PushPerson;
import com.ewell.upload.service.FybPushLabService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Async
public class ExecutionJob extends QuartzJobBean{
    @Resource
    private FybPushLabService labService;
    @Resource
    private ThreadPoolTaskExecutor executor;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String beanName = (String) jobExecutionContext.getMergedJobDataMap().get("beanName");//获取任务对象
        String methodName = (String)jobExecutionContext.getMergedJobDataMap().get("beanMethod");//获取任务方法名称
        Object params = jobExecutionContext.getMergedJobDataMap().get("params");//获取任务参数
        try{
            if ("pushReportTask".equals(beanName)){
                //BaseResponse<List<PushPerson>> resObject = labService.queryPersonWcFzjc();
                BaseResponse<List<PushPerson>> resObject = new BaseResponse<>();
                resObject.setResult("success");
                resObject.setMessage("成功");
                PushPerson pushPerson = new PushPerson();
                List<PushPerson> pushPersonList = new ArrayList<>();
                pushPerson.setSrc("无锡人民医院");
                pushPerson.setOutpatientNo("123456");
                pushPersonList.add(pushPerson);
                resObject.setData(pushPersonList);
                if ("success".equals(resObject.getResult())){
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
            }else {
                QuartzRunnable task = new QuartzRunnable(beanName, methodName, params);
                Future<?> future = executor.submit(task);
                future.get();
            }
        }catch (Exception e){
            log.error("定时器异常------>"+e.getMessage());
        }

    }
}
