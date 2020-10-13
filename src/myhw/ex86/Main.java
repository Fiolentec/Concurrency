package myhw.ex86;

public class Main {
    private static final Integer N = 10;

    public static void main(String[] args) {
//        Barrier barrier = new FirstBarrier(N);
        Barrier barrier = new SecondBarrier(N);
        for (int i = 0; i < N; ++i) {
            new Thread(() -> {
                foo();
                barrier.await();
                bar();
            }).start();
        }

    }

    public static void foo() {
        System.out.println("foo");
    }

    public static void bar() {
        System.out.println("bar");
    }
}
