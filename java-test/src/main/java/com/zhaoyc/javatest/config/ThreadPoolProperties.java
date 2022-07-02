package com.zhaoyc.javatest.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.BlockingQueue;

/**
 * @ClassName ThreadPoolProperties
 * @Description 线程池参数
 * @Author zhaoyangchang
 * @Date 2022/7/2 17:33
 * @Version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadPoolProperties {

    public static final ThreadPoolProperties DEFAULT = new ThreadPoolProperties(
            "thread-pool-%d",
            3,
            5,
            60,
            10000,
            false
    );

    /**
     * 线程池名字
     */
    private String nameFormat;
    /**
     * 核心线程数
     */
    private int coreSize;
    /**
     * 最大线程数
     */
    private int maxSize;
    /**
     * 空闲线程存活时间
     */
    private long keepAlive;
    /**
     * 排队容量
     */
    private int queueCapacity;
    /**
     * 是否允许核心线程超时
     */
    private boolean allowCoreThreadTimeout;

}
