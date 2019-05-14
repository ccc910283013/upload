package com.ewell.upload.quartz.config;

import com.ewell.upload.quartz.util.ExecutionJob;
import com.ewell.upload.quartz.util.QuartzJobListener;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JobRunner implements ApplicationRunner {
    @Value("${job.Enable}")
    private boolean jobEnable;
    @Value("${job.Context}")
    private String jobContext;
    @Resource
    private Environment environment;
    private Object target;
    private Method method;
    private static final String JOB_NAME = "TASK_";
    @Resource
    private Scheduler scheduler;
    @Resource
    private QuartzJobListener jobLister;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("--------------------注入定时任务---------------------");
        if (jobEnable){
            List<String> taskList = Arrays.asList(jobContext.split(","));
            taskList.forEach(task ->{
                Boolean flag = environment.getProperty("job."+task+".Enable",Boolean.class);
                if (flag){
                    QuartzJobConfig jobConfig = QuartzJobConfig.getInstance(task);
                    String cronExpression = environment.getProperty("job."+task+".cronExpression");
                    try {
                        JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                                withIdentity(JOB_NAME + task).build();
                        Trigger cronTrigger = TriggerBuilder.newTrigger()
                                .withIdentity(JOB_NAME + task)
                                .startNow()
                                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                                .build();
                        JobDataMap jmap = cronTrigger.getJobDataMap();
                        jmap.put("beanName",jobConfig.getBeanName());
                        jmap.put("beanMethod",jobConfig.getMethodName());
                        jmap.put("params",jobConfig.getParams());
                        ((CronTriggerImpl)cronTrigger).setStartTime(new Date());
                        scheduler.scheduleJob(jobDetail,cronTrigger);
                        scheduler.getListenerManager().addJobListener(jobLister);
                    } catch (Exception e){
                        log.error(task +" error:"+e.getMessage());
                    }
                }else{
                    log.info(task+"定时任务未开启");
                }
            });
        }else{
            log.info("定时任务未开启");
        }
        System.out.println("--------------------定时任务注入完成---------------------");
    }

}
