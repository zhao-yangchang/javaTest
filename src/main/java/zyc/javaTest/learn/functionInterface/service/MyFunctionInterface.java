package zyc.javaTest.learn.functionInterface.service;

@FunctionalInterface
public interface MyFunctionInterface<T> {
    boolean test(T t);
}
