package functionInterface;

import common.enums.SexyEnum;
import functionInterface.VO.People;
import functionInterface.service.MyFunctionInterface;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionInterfaceTest {

    private static List<People> peoples = Arrays.asList(
            new People("zhangsan", 25, SexyEnum.MAN.getCode()),
            new People("lisi", 23, SexyEnum.MAN.getCode()),
            new People("xiaohong", 20, SexyEnum.WOMAN.getCode()),
            new People("xiaosao", 27, SexyEnum.WOMAN.getCode())
    );

    private static boolean check(Integer i, MyFunctionInterface<Integer> m) {

        return m.test(i);

    }

    private static boolean checkPeople(People p, MyFunctionInterface<People> m) {
        return m.test(p);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 5, 4, 6);

        List<Integer> filters = list
                .stream()
                .filter(i -> check(i, num -> num < 5))
                .collect(Collectors.toList());

        System.out.println(filters);

        List<People> filterPeoples = peoples
                .stream()
                .filter(people -> checkPeople(people, m -> m.getAge() > 25 ))
                .collect(Collectors.toList());

        System.out.println(filterPeoples);

    }

}
