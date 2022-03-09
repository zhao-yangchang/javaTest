package zyc.javaTest.learn.anonymousInnerClass;

public class ThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread(){
            public void run() {
                System.out.println("current thread: " + Thread.currentThread().getId());
            }
        };
        Thread thread1 = new Thread(() -> {
             System.out.println("current thread: " + Thread.currentThread().getId());
        });
        thread.start();
        thread1.start();
    }

}
