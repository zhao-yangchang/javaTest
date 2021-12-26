package anonymousInnerClass;

public class RunnableTest {

    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("current thread: " + Thread.currentThread().getId());
            }
        };
        Runnable runnable2 = () -> {
            System.out.println("current thread: " + Thread.currentThread().getId());
        };
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

}
