package com.zhaoyc.javatest.learn.lambda;

import com.zhaoyc.javatest.learn.lambda.service.NoReturnMultiParam;

public class LambdaTest {

    private static void testFunction() {
        NoReturnMultiParam noReturnMultiParam = new NoReturnMultiParam() {
            @Override
            public void method(int a, int b) {
                System.out.println("a + b: " + (a + b));
            }
        };
        functionToMethod(1, 2, (a, b) -> {
            System.out.println(1 + 2);
        });

    }

    private static void functionToMethod(int a, int b, NoReturnMultiParam noReturnMultiParam) {
        noReturnMultiParam.method(a, b);
    }

    public static void main(String[] args) {
        // 匿名内部类实现方式
        NoReturnMultiParam noReturnMultiParam = new NoReturnMultiParam() {
            @Override
            public void method(int a, int b) {
                System.out.println("匿名内部类：" + (a+b));
            }
        };
        noReturnMultiParam.method(1, 2);

        // lambda1
        NoReturnMultiParam lamdda = (a, b) -> System.out.println("lambda1:" + (a+b));
        lamdda.method(3, 4);

        // lambda2
        functionToMethod(5, 5, (a, b) -> System.out.println(" lambda2: " + (a+b)));

    }

}
