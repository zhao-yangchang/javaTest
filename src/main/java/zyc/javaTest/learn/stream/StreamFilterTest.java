package zyc.javaTest.learn.stream;

import zyc.javaTest.learn.common.vo.PeopleVO;
import zyc.javaTest.learn.common.enums.SexyEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className StreamFilterTest
 * @description: filter
 * @date 2022/1/13 11:34 下午
 */
public class StreamFilterTest {

    private static List<PeopleVO> peopleVOS = PeopleVO.getPeopleVOS();

    public static void main(String[] args) {

        // filter
        List<PeopleVO> filters = peopleVOS
                .stream()
                .filter(peopleVO -> SexyEnum.WOMAN.getCode() == peopleVO.getSex())
                .collect(Collectors.toList());
        System.out.println(filters);

    }

}
