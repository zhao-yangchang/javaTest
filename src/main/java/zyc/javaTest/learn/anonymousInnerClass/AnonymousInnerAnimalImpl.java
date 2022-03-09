package zyc.javaTest.learn.anonymousInnerClass;

/**
 * 通过匿名内部类实现接口
 */
public class AnonymousInnerAnimalImpl {
    public static void main(String[] args) {
        // 此处不是创建了一个接口对象，而是一个接口的实现类
        Animal animal = new Animal() {
            @Override
            public void eat() {
                System.out.println("animal is eating");
            }
        };
        animal.eat();
    }
}
