package common.VO;

import java.util.function.Supplier;

public class SupplierVO extends PeopleVO{
    public SupplierVO(String name, Integer age, int sex) {
        super(name, age, sex);
    }

    public static SupplierVO supplier(Supplier<SupplierVO> supplier) {
        return supplier.get();
    }

}
