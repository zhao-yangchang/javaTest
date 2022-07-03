package com.zhaoyc.javatest.sync.Service;

import com.zhaoyc.javatest.learn.functionInterface.service.MyFunc;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @ClassName ThreadManager
 * @Description TODO
 * @Author zhaoyangchang
 * @Date 2022/7/3 10:43
 * @Version 1.0.0
 */
public class ThreadManager {

    // 启动类需添加 @EnableAnsync
    @Async("poolExecutor")
    public CompletableFuture<Object> invoke(MyFunc.Supplier<Object> supplier) {
        return CompletableFuture.completedFuture(supplier.get());
    }

    @Async("poolExecutor")
    public CompletableFuture invokeVoid(MyFunc.ConsumerVoid consumer) {
        consumer.get();
        return CompletableFuture.completedFuture(null);
    }

}
