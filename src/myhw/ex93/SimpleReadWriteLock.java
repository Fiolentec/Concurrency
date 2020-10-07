package myhw.ex93;


public class SimpleReadWriteLock implements LocalReadWriteLock {
    Lock readLock, writeLock;
    private int readers;
    private boolean writer;
    private Object locker = new Object();

    public SimpleReadWriteLock() {
        readers = 0;
        writer = false;
        readLock = new ReadLock();
        writeLock = new WriteLock();
    }

    public Lock readLock() {
        return readLock;
    }

    public Lock writeLock() {
        return writeLock;
    }

    private void sleep(Object o) {
        try {
            o.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    class ReadLock implements Lock {
        public void lock() {
            synchronized (locker) {
                while (writer) {
                    sleep(locker);
                }
                readers++;
            }
        }

        public void unlock() {
            synchronized (locker) {
                readers--;
                if (readers == 0) {
                    locker.notifyAll();
                }
            }
        }
    }

    class WriteLock implements Lock {
        public void lock() {
            synchronized (locker) {
                while (readers > 0 || writer) {
                    sleep(locker);
                }
                writer = true;
            }
        }

        public void unlock() {
            synchronized (locker) {
                writer = false;
                locker.notifyAll();
            }
        }
    }
}
