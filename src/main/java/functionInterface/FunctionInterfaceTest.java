package functionInterface;

import common.enums.SexyEnum;
import functionInterface.VO.People;
import functionInterface.service.MyFunctionInterface;
import functionInterface.service.NoReturnMultiParam;

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

        // test VO
        List<People> filterPeoples = peoples
                .stream()
                .filter(people -> checkPeople(people, m -> m.getAge() > 25 ))
                .collect(Collectors.toList());

        System.out.println(filterPeoples);

        // test Function
        testFunction();
    }

    private static void testFunction() {

        NoReturnMultiParam noReturnMultiParam = new NoReturnMultiParam() {
            @Override
            public void method(int a, int b) {
                System.out.println("a + b: " + (a + b));
            }
        };
        // noReturnMultiParam.method(1, 2);

        /*functionToMethod(1, 2, new NoReturnMultiParam() {
            @Override
            public void method(int a, int b) {
                System.out.println(a + b);
            }
        });*/

        functionToMethod(1, 2, (a, b) -> {
            System.out.println(1 + 2);
        });

    }

    private static void functionToMethod(int a, int b, NoReturnMultiParam noReturnMultiParam) {
        noReturnMultiParam.method(a, b);
    }

}
