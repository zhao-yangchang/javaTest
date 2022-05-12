package com.zhaoyc.javatest.learn.reflect;

import com.zhaoyc.javatest.learn.common.vo.WorkerVO;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName CopyTest
 * @Description object copy
 * @Author zhaoyangchang
 * @Date 2022/1/24 下午7:10
 * @Version 1.0.0
 */
public class CopyTest<T> {

    private static List<WorkerVO> workerVOS = WorkerVO.getWorkers();

    public static void main(String[] args) {

        List<WorkerVO> copies = workerVOS
                .stream()
                .map(workerVO -> {
                    try {
                        return (WorkerVO) copy(workerVO);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());

    }

    private static Object copy(Object object) throws Exception {

        System.out.println(object);
        Class aClass = object.getClass();
        Class superClass = aClass.getSuperclass();
        List<Field> subFields = Arrays.asList(aClass.getDeclaredFields());
        List<Field> superFields = Arrays.asList(superClass.getDeclaredFields());

        List<Field> fields = Stream.concat(Stream.of(subFields), Stream.of(superFields))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(fields);

        Object resObj = aClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
        fields
                .stream()
                .forEach(field -> {
                    field.setAccessible(true);
                    String name = field.getName();
                    try {
                        System.out.println(field.get(resObj));
                        Object param = field.get(object);
                        field.set(resObj, param);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(resObj);

        return resObj;
    }

}
