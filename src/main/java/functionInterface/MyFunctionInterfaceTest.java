package functionInterface;

import common.vo.PeopleVO;
import common.enums.SexyEnum;
import functionInterface.service.MyFunctionInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyFunctionInterfaceTest {

    private static List<PeopleVO> peoples = Arrays.asList(
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

    private static Integer calAge(Integer age, Function<Integer, Integer> function) {
        return function.apply(age);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 5, 4, 6);

        List<Integer> filters = list
                .stream()
                .filter(i -> check(i, num -> num < 5))
                .collect(Collectors.toList());

        System.out.println(filters);

        List<PeopleVO> filterPeoples = peoples
                .stream()
                .filter(people -> checkPeople(people, m -> m.getAge() > 25 ))
                .collect(Collectors.toList());

        List<Integer> ages = peoples
                .stream()
                .map(peopleVO -> calAge(peopleVO.getAge(), age -> age + 1))
                .collect(Collectors.toList());


        System.out.println(filterPeoples);

    }

}
