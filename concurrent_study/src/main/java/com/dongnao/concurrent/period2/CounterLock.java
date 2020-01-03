package com.dongnao.concurrent.period2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
    volatile int i = 0;

    Lock lock = new KodyRenntrantLock();

    public void add() {
        lock.lock();
        i++;
        lock.unlock();
    }
}
