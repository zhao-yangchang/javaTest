package com.zhaoyc.javatest.sync;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className SyncTest
 * @description: TODO
 * @date 2022/1/21 11:06 下午
 */
public class SyncTest {

    public static int count = 0;

    public static synchronized void addCount() {
        count++;
    }

    public static void main(String[] args) {

        /*ThreadTest threadTest = new ThreadTest();
        threadTest.start();*/
        SyncTest test = new SyncTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < 100; j++) {
                    test.addCount();
                }
                System.out.println(count);
            });
            thread.start();
        }

        try {
            //主程序暂停3秒，以保证上面的程序执行完成
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = " + count);

    }

}
