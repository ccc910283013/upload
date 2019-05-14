package com.ewell.upload.quartz.util;

import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.LoginToken;
import com.ewell.upload.util.JacksonUtil;
import com.ewell.upload.webservice.FYClient.Mchis;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class QuartzJobListener implements JobListener {
    public static LoginToken token = null;

    @Resource
    private Mchis mchis;
    /**
     * 获取任务名称
     * @return 返回任务名称
     */
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    /**
     * 将要被执行时调用这个方法。
     * @param jobExecutionContext 任务上下文
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext){
        System.out.println("任务将要被执行");
        if (token == null){
            String loginStr = mchis.getMchisHttpSoap11Endpoint().login("admin","123");
            BaseResponse<LoginToken> res = JacksonUtil.json2Bean(loginStr, new TypeReference<BaseResponse<LoginToken>>(){});
            token = res.getData();
        }
    }

    /**
     * 将被执行，但又被TriggerListerner否决时会调用该方法
     * @param jobExecutionContext 任务上下文
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("-------------");
    }

    /**
     * 被执行之后调用这个方法
     * @param jobExecutionContext 任务上下文
     * @param e 任务执行过程中的异常
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("被执行之后调用这个方法");
    }
}
