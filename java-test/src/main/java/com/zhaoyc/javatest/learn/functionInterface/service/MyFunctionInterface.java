package com.zhaoyc.javatest.learn.functionInterface.service;

@FunctionalInterface
public interface MyFunctionInterface<T> {
    boolean test(T t);
}

