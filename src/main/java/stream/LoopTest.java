package stream;

import common.VO.PeopleVO;
import common.enums.SexyEnum;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

/**
 * @author zhaoyangchang
 * @version 1.0.0
 * @className LoopTest
 * @description: stream 遍历
 * @date 2022/1/13 11:17 下午
 */
public class LoopTest {

    private static List<PeopleVO> peopleVOS = PeopleVO.getPeopleVOS();

    public static void main(String[] args) {

        // foreach
        peopleVOS
                .stream()
                .forEach(System.out::println);

        //findFirst
        Optional<PeopleVO> optionalPeopleVO = peopleVOS
                .stream()
                .filter(peopleVO -> SexyEnum.WOMAN.getCode() == peopleVO.getSex())
                .findFirst();
        System.out.println(optionalPeopleVO.orElse(null));

        // findAny
        PeopleVO findAny = peopleVOS
                .parallelStream()
                .filter(peopleVO -> SexyEnum.WOMAN.getCode() == peopleVO.getSex())
                .findAny()
                .orElse(null);
        System.out.println(findAny);

        // anyMatch
        boolean anyMatch = peopleVOS
                .stream()
                .anyMatch(peopleVO -> SexyEnum.WOMAN.getCode() == peopleVO.getSex());
        System.out.println(anyMatch);

    }

}
