package com.ewell.upload.quartz.util;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@Slf4j
@Async
public class ExecutionJob extends QuartzJobBean{
    @Resource
    private ThreadPoolTaskExecutor executor;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String beanName = (String) jobExecutionContext.getMergedJobDataMap().get("beanName");//获取任务对象
        String methodName = (String)jobExecutionContext.getMergedJobDataMap().get("beanMethod");//获取任务方法名称
        Object params = jobExecutionContext.getMergedJobDataMap().get("params");//获取任务参数
        try{
            /*
            if ("".equals(beanName)){

            }
            */
            QuartzRunnable task = new QuartzRunnable(beanName, methodName, params);
            Future<?> future = executor.submit(task);
            future.get();
        }catch (Exception e){
            log.error("定时器异常------>"+e.getMessage());
        }

    }
}
