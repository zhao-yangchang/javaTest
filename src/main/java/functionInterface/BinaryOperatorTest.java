package functionInterface;

import common.VO.PeopleVO;
import org.springframework.beans.BeanUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * @ClassName BinaryOperatorTest
 * @Description BinaryOperator Test
 * @Author zhaoyangchang
 * @Date 2022/01/08 下午04:24
 * @Version 1.0.0
 */
public class BinaryOperatorTest {

    private static List<PeopleVO> peopleVOS = PeopleVO.getPeopleVOS();

    // 获得两个人中年龄较小的那个
    private static PeopleVO minByTest(PeopleVO vo1,
                                        PeopleVO vo2,
                                        Comparator<PeopleVO> comparator) {
        return BinaryOperator.minBy(comparator).apply(vo1, vo2);
    }

    /**
     * 筛选年龄最小的人
     * @param peopleVOS
     * @param comparator
     * @return
     */
    private static PeopleVO minAgeFromList(List<PeopleVO> peopleVOS, Comparator<PeopleVO> comparator) {

        PeopleVO minimalAge = new PeopleVO();
        peopleVOS
                .stream()
                .forEach(peopleVO -> {
                    PeopleVO minAge = BinaryOperator.minBy(comparator).apply(minimalAge, peopleVO);
                    BeanUtils.copyProperties(minAge, minimalAge);
                });
        return minimalAge;
    }

    public static void main(String[] args) {

        PeopleVO peopleVO = minByTest(peopleVOS.get(0), peopleVOS.get(1), (o1, o2) -> {
            if (o1.getAge() <= o2.getAge()) {
                return -1;
            }
            return 1;
        });
        System.out.println(peopleVO);

        PeopleVO minimalAge = minAgeFromList(peopleVOS, (o1, o2) -> {
            if (Objects.isNull(o1.getAge())) {
                return 1;
            } else {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(minimalAge);

    }

}
