package functionInterface.service.anonymousInnerClass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Child extends Father{
    @Override
    void eat() {
        log.info("child eat cake");
    }

    public static void main(String[] args) {

        Father father = new Child();
        father.eat();

    }

}
