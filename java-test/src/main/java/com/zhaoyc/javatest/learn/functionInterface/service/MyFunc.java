package com.zhaoyc.javatest.learn.functionInterface.service;

/**
 * @ClassName MyFunc
 * @Description 函数式接口 class
 * @Author zhaoyangchang
 * @Date 2022/7/3 16:29
 * @Version 1.0.0
 */
public class MyFunc {

    @FunctionalInterface
    public interface ConsumerVoid{
        void get();
    }

    @FunctionalInterface
    public interface Supplier<Object>{
        Object get();
    }
}
