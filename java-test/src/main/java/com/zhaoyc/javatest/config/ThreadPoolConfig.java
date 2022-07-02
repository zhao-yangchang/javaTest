package com.zhaoyc.javatest.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolConfig
 * @Description 线程池配置
 * @Author zhaoyangchang
 * @Date 2022/7/2 17:31
 * @Version 1.0.0
 */
@Data
@Configuration
// apollo配置
@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolConfig {

    /**
     * 定义线程池参数
     */
    public ThreadPoolProperties threadPoolProperties = ThreadPoolProperties
            .builder()
            .nameFormat("thread-pool-%d")
            .coreSize(3)
            .maxSize(5)
            .keepAlive(60)
            .queueCapacity(5)
            .allowCoreThreadTimeout(false)
            .build();

    /**
     * 线程池执行器
     * @return
     */
    public ThreadPoolExecutor poolExecutor() {

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat(threadPoolProperties.getNameFormat())
                .build();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                threadPoolProperties.getCoreSize(),
                threadPoolProperties.getMaxSize(),
                threadPoolProperties.getKeepAlive(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(threadPoolProperties.getQueueCapacity(), false),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        executor.allowCoreThreadTimeOut(threadPoolProperties.isAllowCoreThreadTimeout());
        return executor;
    }

}
