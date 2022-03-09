package zyc.javaTest.learn.reflect;

import java.io.File;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @ClassName AnalysisTest
 * @Description TODO
 * @Author zhaoyangchang
 * @Date 2022/1/25 下午3:49
 * @Version 1.0.0
 */
public class AnalysisTest {

    private static final String PATH = "/Users/zhaoyangchang/MyCode/javaTest/src/main/java/common/vo";

    private static final String CLASS_PATH = "common.vo.";

    public static void main(String[] args) {

        File filePath = new File(PATH);
        System.out.println(filePath);
        File[] files = filePath.listFiles();
        System.out.println(files);
        StringBuffer buffer = new StringBuffer();
        Arrays.stream(files)
                .forEach(file -> {
                    try {
                        Class aClass = Class.forName(CLASS_PATH + file.getName().replaceAll(".java", ""));

                        // 文件名
                        String className = aClass.getName();
                        buffer.append("\n文件名：" + className);

                        // 属性名
                        Arrays.stream(aClass.getDeclaredFields())
                                .forEach(field -> {
                                    try {
                                        field.setAccessible(true);
                                        String fieldName = field.getName();
                                        Object fieldValue = field.get(aClass.newInstance());
                                        buffer.append("\n属性名：" + fieldName + " | 属性值：" + fieldValue);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                });

                        // 方法
                        Arrays.stream(aClass.getMethods())
                                .forEach(method -> {
                                    String methodName = method.getName();
                                    buffer.append("\n方法名：" + methodName);
                                    // 方法参数
                                    Parameter[] parameters = method.getParameters();
                                    Arrays.stream(parameters)
                                            .forEach(parameter -> {
                                                String parameterName = parameter.getName();
                                                buffer.append("\n方法参数：" + parameterName);
                                            });
                                });

                        buffer.append("\n-------------------------------");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                });

        System.out.println(buffer);

    }

}
