package zyc.javaTest.learn.anonymousInnerClass;

import lombok.extern.slf4j.Slf4j;

/**
 * 不使用匿名内部类来实现抽象方法
 *      问题：
 *          对于只使用一次的实现类，将其编写为独立的一个类岂不是很麻烦？
 *      解决办法：
 *          匿名内部类
 */
@Slf4j
public class Child extends Father{
    @Override
    void eat() {
        log.error("1");
        System.out.println("child is eating");
    }

    public static void main(String[] args) {

        Father father = new Child();
        father.eat();

    }
}
