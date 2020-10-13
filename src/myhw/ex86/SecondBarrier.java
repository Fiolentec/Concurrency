package myhw.ex86;

import java.util.concurrent.atomic.AtomicInteger;

public class SecondBarrier implements Barrier {
    private int threadNum;
    private volatile int[] array;
    private AtomicInteger num;

    public SecondBarrier(int threadNum) {
        this.threadNum = threadNum;
        array = new int[threadNum];
        num = new AtomicInteger(0);
    }

    public void await() {
        int myNum = num.getAndIncrement();
        if (myNum == 0) {
            array[myNum] = 1;
        } else if (array[myNum - 1] == 1) {
            array[myNum] = 1;
        }
        if (myNum == threadNum - 1) {
            array[myNum] = 2;
            return;
        }
        while (array[myNum + 1] != 2) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        array[myNum] = 2;
    }
}
