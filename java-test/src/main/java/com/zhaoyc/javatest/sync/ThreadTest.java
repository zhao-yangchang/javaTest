package com.zhaoyc.javatest.sync;

import java.util.Date;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className ThreadTest
 * @description: TODO
 * @date 2022/1/21 11:08 下午
 */
public class ThreadTest extends Thread{

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " - ThreadTest - start, time: " + new Date().getTime());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " - ThreadTest - end, time: " + new Date().getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
