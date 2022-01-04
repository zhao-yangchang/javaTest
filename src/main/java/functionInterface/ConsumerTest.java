package functionInterface;

import common.VO.ConsumerVO;
import common.VO.PeopleVO;
import common.enums.SexyEnum;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    private static List<PeopleVO> peopleVOList = Arrays.asList(
            new PeopleVO("zhangsan", 24, 1),
            new PeopleVO("lisi", 28, 1),
            new PeopleVO("wangwu", 25, 1),
            new PeopleVO("xiaohong", 22, 2),
            new PeopleVO("xiaosao", 26, 2),
            new PeopleVO("xiaonai", 24, 2)
    );

    // 无返回值的function
    private static void acceptTest(PeopleVO peopleVO, Consumer<PeopleVO> consumer) {
        consumer.accept(peopleVO);
    }

    // andThen
    private static void andThen(PeopleVO peopleVO, Consumer<PeopleVO> con1, Consumer<PeopleVO> con2) {
        con1.andThen(con2).accept(peopleVO);
    }

    public static void main(String[] args) {

        // general
        peopleVOList
                .stream()
                .forEach(
                        peopleVO -> acceptTest(
                                peopleVO,
                                vo -> {
                                    if (SexyEnum.WOMAN.getCode() == vo.getSex()) {
                                        System.out.println(vo.getName());
                                     }
                                }
                        )
                );

        // lambda
        peopleVOList
                .stream()
                .forEach(peopleVO -> {
                    Consumer<PeopleVO> consumer = vo -> System.out.println(vo.getAge());
                    consumer.accept(peopleVO);
                });

        // andThen
        peopleVOList
                .stream()
                .forEach(peopleVO -> andThen(
                        peopleVO,
                        vo1 -> System.out.print(vo1.getName() + " is "),
                        vo2 -> System.out.println(vo2.getAge() + " years old.")
                ));

        // Consumer升级
        ConsumerVO consumerVO = new ConsumerVO
                .builder()
                .set(vo -> vo.setName("zhangsan"))
                .set(vo -> vo.setAge(18))
                .set(vo -> vo.setBehavior("shopping"))
                .builder();
        System.out.println(consumerVO);

    }

}
