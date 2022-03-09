package zyc.javaTest.learn.stream;

import zyc.javaTest.learn.common.vo.PeopleVO;
import zyc.javaTest.learn.common.enums.SexyEnum;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className PolyTest
 * @description: max/min/count
 * @date 2022/1/14 9:39 下午
 */
public class PolyTest {

    private static List<PeopleVO> peopleVOS = PeopleVO.getPeopleVOS();

    private static Integer maxTest(PeopleVO vo1, PeopleVO vo2, Comparator<Integer> comparator) {
        return comparator.compare(vo1.getAge(), vo2.getAge());
    }

    public static void main(String[] args) {

        // max
        PeopleVO max = peopleVOS
                .stream()
                .max(Comparator.comparing(PeopleVO::getAge))
                .orElse(null);
        System.out.println(max);

        // min
        PeopleVO min = peopleVOS
                .stream()
                .min(Comparator.comparing(PeopleVO::getAge))
                .orElse(null);
        System.out.println(min);

        // count
        long count = peopleVOS
                .stream()
                .filter(peopleVO -> SexyEnum.WOMAN.getCode() == peopleVO.getSex())
                .count();
        System.out.println(count);

    }


}
