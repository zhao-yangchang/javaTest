package com.zhaoyc.javatest.sync;

import java.util.concurrent.Callable;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className CallableTest
 * @description: TODO
 * @date 2022/2/12 4:28 下午
 */
public class CallableTest {

    private static Integer callTest(Integer a, Callable<Integer> callable) throws Exception {
        return callable.call();
    }

    /*public static void main(String[] args) {

        Future<Integer> future = callTest(10, () -> {
            
        });

    }*/

}
