package com.hj.config;

import com.hj.core.DisruptorHandle;
import com.hj.core.DisruptorProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: hj
 * @date: 2023/1/18
 * @time: 9:48 AM
 */
public class Config {


    @Bean
    DisruptorConfig disruptorConfig() {
        return new DisruptorConfig();
    }

    @Bean
    DisruptorProducer disruptorProducer() {
        return new DisruptorProducer();
    }
    @Bean
    DisruptorHandle disruptorHandle() {
        return new DisruptorHandle();
    }


    @Bean("disTaskExecutor")
    public Executor esTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置线程池核心容量
        executor.setCorePoolSize(10);
        // 设置线程池最大容量
        executor.setMaxPoolSize(20);
        // 设置任务队列长度
        executor.setQueueCapacity(200);
        // 设置线程超时时间
        executor.setKeepAliveSeconds(60);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("taskExecutor-");
        // 设置任务丢弃后的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }


}
