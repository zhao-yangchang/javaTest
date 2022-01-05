package functionInterface;

import common.VO.PeopleVO;
import common.enums.SexyEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName Function2Test
 * @Description Function 函数式接口 test2
 * @Author zhaoyangchang
 * @Date 2021/12/30 下午5:12
 * @Version 1.0.0
 */
public class Function2Test {

    private static List<PeopleVO> peopleVOS = Arrays.asList(
            new PeopleVO("zhangsan", 25, SexyEnum.MAN.getCode()),
            new PeopleVO("lisi", 23, SexyEnum.MAN.getCode()),
            new PeopleVO("xiaohong", 20, SexyEnum.WOMAN.getCode()),
            new PeopleVO("xiaosao", 27, SexyEnum.WOMAN.getCode())
    );

    private static List<Integer> ageList = Arrays.asList(20, 21, 22, 23, 24, 25);

    private static Integer applyAge(Integer age, Function<Integer, Integer> function) {
        return function.apply(age);
    }

    /**
     * 先 func1 -> 后 func2
     * @param age
     * @param func1
     * @param func2
     * @return
     */
    private static Integer composeAge(Integer age, Function<Integer, Integer> func1, Function<Integer, Integer> func2) {
        return func2.compose(func1).apply(age);
    }


    /**
     * 对VO的age进行compose
     * @param peopleVO
     * @param func1
     * @param func2
     * @return
     */
    private static Integer composeVO(PeopleVO peopleVO, Function<PeopleVO, Integer> func1, Function<Integer, Integer> func2) {
        return func2.compose(func1).apply(peopleVO);
    }


    /**
     * andThen test
     * @param peopleVO
     * @param func1
     * @param func2
     * @return
     */
    private static Integer andThen(PeopleVO peopleVO, Function<Integer, Integer> func1, Function<PeopleVO, Integer> func2) {
        return func2.andThen(func1).apply(peopleVO);
    }

    public static void main(String[] args) {

        // apply测试
        List<Integer> applyList = ageList
                .stream()
                .map(age -> applyAge(age, i -> i + 10))
                .collect(Collectors.toList());
        System.out.println(applyList);

        // compose测试
        List<Integer> composeList = ageList
                .stream()
                .map(age -> composeAge(age, i -> i + 5, i -> i * 2))
                .collect(Collectors.toList());
        System.out.println(composeList);

        // composeVO test
        List<Integer> composeVOList = peopleVOS
                .stream()
                .map(peopleVO -> composeVO(peopleVO, vo -> vo.getAge() + 10, i -> i * 2))
                .collect(Collectors.toList());
        System.out.println(composeVOList);

        // andThen test
        List<Integer> andThenList = peopleVOS
                .stream()
                .map(peopleVO -> andThen(peopleVO, i -> i + 1, vo -> vo.getAge() * 10))
                .collect(Collectors.toList());
        System.out.println(andThenList);

    }


}
