package com.aircjm.limon.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author aircjm
 */
@Configuration
public class ExecutorConfig {


    /**
     * 异步处理线程池
     *
     * @return 线程池
     */
    @Bean(name = "asyncExecutor")
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(3);
        executor.setQueueCapacity(2000);
        executor.setKeepAliveSeconds(300);
        executor.setThreadNamePrefix("asyncExecutor-pool ");
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
