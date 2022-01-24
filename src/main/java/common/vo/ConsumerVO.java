package common.vo;

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

        public builder setName(String name) {
            target.setName(name);
            return this;
        }

        public builder setAge(int age) {
            target.setAge(age);
            return this;
        }

        public builder setBehavior(String behavior) {
            target.setBehavior(behavior);
            return this;
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
