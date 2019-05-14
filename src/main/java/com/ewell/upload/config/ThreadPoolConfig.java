package com.ewell.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {
    /**
     * 核心线程数
     */
    @Value("${threadPool.core-pool-size}")
    private int corePoolSize;
    /**
     * 最大线程数
     */
    @Value("${threadPool.max-pool-size}")
    private int maxPoolSize;
    /**
     * 队列最大长度
     */
    @Value("${threadPool.queue-capacity}")
    private int queueCapacity;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    @Value("${threadPool.keep-alive-seconds}")
    private int keepAliveSeconds;

    @Bean
    public ThreadPoolTaskExecutor threadPool(){
        ThreadPoolTaskExecutor threadPools = new ThreadPoolTaskExecutor();
        threadPools.setThreadNamePrefix("databasePool");
        threadPools.setCorePoolSize(corePoolSize);
        threadPools.setMaxPoolSize(maxPoolSize);
        threadPools.setQueueCapacity(queueCapacity);
        threadPools.setKeepAliveSeconds(keepAliveSeconds);
        //线程池对拒绝任务(无线程可用)的处理策略
        threadPools.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return  threadPools;
    }
}
