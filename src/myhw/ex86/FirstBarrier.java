package myhw.ex86;

public class FirstBarrier implements Barrier {
    private final int threadNum;
    TTASLock ttasLock;
    private volatile Integer counter;

    public FirstBarrier(int threadNum) {
        this.threadNum = threadNum;
        counter = 0;
        ttasLock = new TTASLock();
    }

    public void await() {
        ttasLock.lock();
        try {
            counter++;
        } finally {
            ttasLock.unlock();
        }
        while (counter < threadNum) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

}
