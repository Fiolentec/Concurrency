package myhw.ex93;

public class Main {

    public static void main(String[] args) {
        SimpleReadWriteLock simpleReadWriteLock = new SimpleReadWriteLock();
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                new Thread(() -> {
                    writeSmth(simpleReadWriteLock);
                }).start();
            } else {
                new Thread(() -> {
                    readSmth(simpleReadWriteLock);
                }).start();
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeSmth(SimpleReadWriteLock simpleReadWriteLock) {
        simpleReadWriteLock.writeLock().lock();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("I wrote down the value");
        simpleReadWriteLock.writeLock().unlock();
    }

    private static void readSmth(SimpleReadWriteLock simpleReadWriteLock) {
        simpleReadWriteLock.readLock().lock();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("i read the value");
        simpleReadWriteLock.readLock().unlock();
    }
}
