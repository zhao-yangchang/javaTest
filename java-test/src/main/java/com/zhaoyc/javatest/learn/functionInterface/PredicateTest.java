package com.zhaoyc.javatest.learn.functionInterface;

import com.zhaoyc.javatest.learn.common.enums.SexyEnum;
import com.zhaoyc.javatest.learn.common.vo.PeopleVO;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ClassName SupplierTest
 * @Description Supplier 谓词型 Test
 * @Author zhaoyangchang
 * @Date 2022/01/07 下午10:24
 * @Version 1.0.0
 */
public class PredicateTest {

    private static List<PeopleVO> peopleVOList = Arrays.asList(
            new PeopleVO("zhangsan", 24, 1),
            new PeopleVO("lisi", 28, 1),
            new PeopleVO("wangwu", 25, 1),
            new PeopleVO("xiaohong", 22, 2),
            new PeopleVO("xiaosao", 26, 2),
            new PeopleVO("xiaonai", 24, 2)
    );

    // predicate test
    private static boolean predicateTest(PeopleVO peopleVO, Predicate<PeopleVO> predicate) {
        return predicate.test(peopleVO);
    }

    // and Test
    private static boolean andTest(PeopleVO peopleVO, Predicate<PeopleVO> pred1, Predicate<PeopleVO> pre2) {
        return pred1.and(pre2).test(peopleVO);
    }

    // negate test
    private static boolean negateTest(PeopleVO peopleVO, Predicate<PeopleVO> pre1) {
        return pre1.negate().test(peopleVO);
    }

    // or test
    private static boolean orTest(PeopleVO peopleVO, Predicate<PeopleVO> pre1, Predicate<PeopleVO> pre2) {
        return pre1.or(pre2).test(peopleVO);
    }

    // isEqual test
    private static boolean isEqualTest(String name1, String name2) {
        return Predicate.isEqual(name1).test(name2);
    }

    public static void main(String[] args) {

        // filter womenList
        List<PeopleVO> womenList= peopleVOList
                .stream()
                .filter(peopleVO ->
                        // 筛选女性
                        predicateTest(peopleVO,
                                vo -> SexyEnum.WOMAN.getCode() == vo.getSex()))
                .collect(Collectors.toList());
        System.out.println(womenList);

        // filter age > 25 menList
        List<PeopleVO> menAgeList = peopleVOList
                .stream()
                .filter(peopleVO ->
                        andTest(
                                peopleVO,
                                vo1 -> vo1.getAge() > 25,
                                vo2 -> SexyEnum.MAN.getCode() == vo2.getSex())
                )
                .collect(Collectors.toList());
        System.out.println(menAgeList);

        // filter menList
        List<PeopleVO> menList = peopleVOList
                .stream()
                .filter(peopleVO ->
                        negateTest(peopleVO, vo -> SexyEnum.WOMAN.getCode() == vo.getSex()))
                .collect(Collectors.toList());
        System.out.println(menList);

        // filter age == 22 || age == 26
        List<PeopleVO> ageList = peopleVOList
                .stream()
                .filter(peopleVO ->
                        orTest(peopleVO, vo1 -> vo1.getAge() == 22, vo2 -> vo2.getAge() == 26))
                .collect(Collectors.toList());
        System.out.println(ageList);

        // isEqual test
        String targetName = "zhangsan";
        List<PeopleVO> sameNameList = peopleVOList
                .stream()
                .filter(peopleVO -> isEqualTest(targetName, peopleVO.getName()))
                .collect(Collectors.toList());
        System.out.println(sameNameList);

    }

}
