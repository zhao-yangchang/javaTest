package com.zhaoyc.javatest.learn.anonymousInnerClass;

/**
 * 通过匿名内部类实现Person方法
 */
public class AnonymousInnerPerson {
    public static void main(String[] args) {
        // 多态
        Person p = new Person() {
            @Override
            void eat() {
                System.out.println("person is eating");
            }

            @Override
            void sleep() {
                System.out.println("person is sleeping");
            }
        };
        p.eat();
        p.sleep();
    }
}
