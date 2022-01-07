package functionInterface;

import common.VO.PeopleVO;
import common.VO.SupplierVO;
import common.enums.SexyEnum;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @ClassName SupplierTest
 * @Description Supplier 供给型 Test
 * @Author zhaoyangchang
 * @Date 2022/01/06 下午11:30
 * @Version 1.0.0
 */
public class SupplierTest {

    private static Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

    private static PeopleVO supplierVO(Supplier<PeopleVO> supplier) {
        return supplier.get();
    }

    private static Integer randomSupplier(Supplier<Integer> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {

        // 匿名内部类
        Supplier<PeopleVO> supplier = () ->
                new PeopleVO("xiaosao", 25, SexyEnum.WOMAN.getCode());
        PeopleVO peopleVO1 = supplier.get();
        System.out.println(peopleVO1);

        // new
        PeopleVO peopleVO2 = supplierVO(() ->
                new PeopleVO("zhangsan", 18, SexyEnum.MAN.getCode()));
        System.out.println(peopleVO2);

        SupplierVO supplierVO = SupplierVO.supplier(() ->
                new SupplierVO("xiaonai", 22, SexyEnum.WOMAN.getCode()));
        System.out.println(supplierVO);

        //
        Optional<Integer> optional = stream
                .filter(i -> i > 6)
                .findFirst();
        Integer theI1 = optional
                .orElse(randomSupplier(() -> new Random().nextInt()));
        System.out.println(theI1);

    }

}
