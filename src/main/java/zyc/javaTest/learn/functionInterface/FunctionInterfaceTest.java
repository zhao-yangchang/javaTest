package zyc.javaTest.learn.functionInterface;

import zyc.javaTest.learn.common.enums.SexyEnum;
import zyc.javaTest.learn.common.vo.PeopleVO;
import zyc.javaTest.learn.functionInterface.service.MyFunctionInterface;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName FunctionInterfaceTest
 * @Description 函数式接口Test
 * @Author zhaoyangchang
 * @Date 2021/12/20 上午11:50
 * @Version 1.0.0
 */
public class FunctionInterfaceTest {

    private static List<PeopleVO> peopleVOS = Arrays.asList(
            new PeopleVO("zhangsan", 25, SexyEnum.MAN.getCode()),
            new PeopleVO("lisi", 23, SexyEnum.MAN.getCode()),
            new PeopleVO("xiaohong", 20, SexyEnum.WOMAN.getCode()),
            new PeopleVO("xiaosao", 27, SexyEnum.WOMAN.getCode())
    );

    private static boolean check(Integer i, MyFunctionInterface<Integer> m) {

        return m.test(i);

    }

    private static boolean checkPeople(PeopleVO p, MyFunctionInterface<PeopleVO> m) {
        return m.test(p);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 5, 4, 6);

        List<Integer> filters = list
                .stream()
                .filter(i -> check(i, num -> num < 5))
                .collect(Collectors.toList());

        System.out.println(filters);

        // test VO
        List<PeopleVO> filterPeopleVOS = peopleVOS
                .stream()
                .filter(peopleVO -> checkPeople(peopleVO, m -> m.getAge() > 25 ))
                .collect(Collectors.toList());

        System.out.println(filterPeopleVOS);

    }

}
