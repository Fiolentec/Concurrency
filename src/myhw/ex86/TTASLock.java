package myhw.ex86;

import myhw.ex93.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock {
    AtomicBoolean locker = new AtomicBoolean(false);

    public void lock() {
        while (true) {
            while (locker.get()) {
            }
            if (!locker.getAndSet(true)) {
                return;
            }
        }
    }

    public void unlock() {
        locker.set(false);
    }
}
