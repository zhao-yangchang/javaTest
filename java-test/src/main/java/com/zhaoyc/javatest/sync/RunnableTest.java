package com.zhaoyc.javatest.sync;

import java.util.Date;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className CallableTest
 * @description: TODO
 * @date 2022/1/21 10:35 下午
 */
public class RunnableTest implements Runnable {

    private static synchronized int add(int count) {
        System.out.println(count+=1);
        return count+=1;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "- RunnableTest- start, time: " + new Date().getTime());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "- RunnableTest- end, time: " + new Date().getTime());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "start, time: " + new Date().getTime());
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "end, time: " + new Date().getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        Thread thread1 = new Thread();

        System.out.println(Thread.currentThread().getName() + "start");

    }

}
