package com.zhaoyc.javatest.learn.reflect;

import com.zhaoyc.javatest.learn.common.vo.WorkerVO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ReflectTest
 * @Description class
 * @Author zhaoyangchang
 * @Date 2022/1/24 上午11:18
 * @Version 1.0.0
 */
public class ReflectTest {

    public static void main(String[] args) {
        try {
            Class aClass = Class.forName("zyc.javaTest.learn.common.vo.WorkerVO");
            System.out.println(aClass);

            Field area = aClass.getDeclaredField("area");
            System.out.println(area);

            List<Field> fields = Arrays.asList(aClass.getDeclaredFields());
            fields
                    .stream()
                    .forEach(field -> {
                        System.out.println(field.getName());
                    });

            WorkerVO workerVO = (WorkerVO) aClass.newInstance();
            System.out.println(workerVO);

            Method method = aClass.getMethod("getWorkers");
            System.out.println(method);

            Object o = method.invoke(null);
            List<WorkerVO> workerVOS = (List<WorkerVO>) o;
            System.out.println(workerVOS);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
