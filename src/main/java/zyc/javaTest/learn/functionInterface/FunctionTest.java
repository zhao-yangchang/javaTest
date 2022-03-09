package zyc.javaTest.learn.functionInterface;

import zyc.javaTest.learn.common.vo.PeopleVO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName FunctionTest
 * @Description Function test
 * @Author zhaoyangchang
 * @Date 2021/12/20 上午11:50
 * @Version 1.0.0
 */
public class FunctionTest {

    private static List<PeopleVO> peopleVOS = Arrays.asList(
            new PeopleVO("zhangsan", 24, 1),
            new PeopleVO("lisi", 28, 1),
            new PeopleVO("wangwu", 25, 1),
            new PeopleVO("xiaohong", 22, 2),
            new PeopleVO("xiaosao", 26, 2),
            new PeopleVO("xiaonai", 24, 2)
    );

    private static List<Integer> ageList = Arrays.asList(10, 19, 24, 29, 15);

    // apply test-1
    private static Integer applyTest(Integer i, Function<Integer, Integer> func) {
        return func.apply(i);
    }

    // apply test-2
    private static Integer applyVOTest(PeopleVO peopleVO, Function<PeopleVO, Integer>func) {
        return func.apply(peopleVO);
    }

    /**
     * compose VO test
     *      func1 -> func2
     * @param peopleVO
     * @param func1
     * @param func2
     * @return
     */
    private static Integer composeTest(PeopleVO peopleVO, Function<PeopleVO, Integer> func1, Function<Integer, Integer> func2) {
        return func2.compose(func1).apply(peopleVO);
    }

    /**
     * andThen Test
     *      fun1 -> func2
     * @param peopleVO
     * @param func1
     * @param func2
     * @return
     */
    private static Integer andThenTest(PeopleVO peopleVO, Function<PeopleVO, Integer> func1, Function<Integer, Integer> func2) {
        return func1.andThen(func2).apply(peopleVO);
    }

    /**
     * 二元函数
     * @param peopleVO
     * @param function
     * @return
     */
    private static Integer biFunction(PeopleVO peopleVO, BiFunction<PeopleVO, Function<Integer, Integer>, Integer> function) {
        return function.apply(peopleVO, i -> i * 2);
    }

    public static void main(String[] args) {
        // apply test
        List<Integer> applyList = ageList
                .stream()
                .map(age -> applyTest(age, i -> i + 1))
                .collect(Collectors.toList());
        System.out.println(applyList);

        // apply VO test
        List<Integer> applyVOList = peopleVOS
                .stream()
                .map(peopleVO -> applyVOTest(peopleVO, vo -> vo.getAge() + 10))
                .collect(Collectors.toList());
        System.out.println(applyVOList);

        // compose VO test
        List<Integer> composeVOList = peopleVOS
                .stream()
                .map(peopleVO -> composeTest(peopleVO, vo -> vo.getAge() + 1, i -> i * 3))
                .collect(Collectors.toList());
        System.out.println(composeVOList);

        // andThen VO test
        List<Integer>  andThenList = peopleVOS
                .stream()
                .map(peopleVO -> andThenTest(peopleVO, vo -> vo.getAge() + 10, i -> i * 2))
                .collect(Collectors.toList());
        System.out.println(andThenList);

        // todo identity - understand
        Map<String, PeopleVO> voMap = peopleVOS
                .stream()
                .collect(Collectors.toMap(PeopleVO::getName, Function.identity()));
        System.out.println(voMap);

        // 二元函数 => andThen
        List<Integer> biList = peopleVOS
                .stream()
                .map(peopleVO -> biFunction(peopleVO, (vo, i) -> i.apply(vo.getAge() + 10)))
                .collect(Collectors.toList());
        System.out.println(biList);

    }
}
