package com.ewell.upload.quartz.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuartzJobConfig {
    private String jobName;
    private String beanName;
    private String methodName;
    private String params;
    private String cronExpression;
    /**
     * 获取任务
     * @param taskName 任务名称
     * @return
     */
    public static QuartzJobConfig getInstance(String taskName){
        QuartzJobConfig jobConfig = new QuartzJobConfig();
        switch (taskName){
            case "FYOutpMonitor":
                jobConfig.jobName = taskName;
                jobConfig.beanName = "recordCardTask";
                jobConfig.params = null;
                jobConfig.methodName = "taskOutpEvent";
                break;
            case "FYInpMonitor":
                jobConfig.jobName = taskName;
                jobConfig.beanName = "recordCardTask";
                jobConfig.params = null;
                jobConfig.methodName = "taskInpEvent";
                break;
            case "PushReportTask":
                jobConfig.jobName = taskName;
                jobConfig.beanName = "pushReportTask";
                jobConfig.methodName = "taskMonitorEvent";
                jobConfig.params = null;
                break;
            default:
        }
        return jobConfig;
    }
}
