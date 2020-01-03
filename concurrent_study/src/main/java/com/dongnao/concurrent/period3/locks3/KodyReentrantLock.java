package com.dongnao.concurrent.period3.locks3;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class KodyReentrantLock {
    CommonMask mask = new CommonMask();

    public void lock(){
        mask.lock();
    }

    public boolean tryLock(){
        return mask.tryLock(1);
    }

    public void unlock(){
        mask.unlock();
    }

}
