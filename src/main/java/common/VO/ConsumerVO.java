package common.VO;

import lombok.Builder;
import lombok.Data;

import java.util.function.Consumer;

/**
 * 建造者模式 + Consumer
 */
@Data
public class ConsumerVO {

    private String name;

    private int age;

    private String behavior;

    public ConsumerVO() {

    }

    public static class builder {

        private ConsumerVO target;

        public builder() {
            target = new ConsumerVO();
        }

        public builder set(Consumer<ConsumerVO> consumer) {
            consumer.accept(target);
            return this;
        }

        public ConsumerVO builder() {
            return target;
        }

    }

}
