package reflect;

import common.vo.WorkerVO;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Field> fields = Arrays.asList(aClass.getDeclaredFields());
        Object resObj = aClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
        fields
                .stream()
                .forEach(field -> {
                    field.setAccessible(true);
                    String name = field.getName();
                    System.out.println(name);
                    try {
                        System.out.println(field.get(resObj));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        return t;
    }

}
