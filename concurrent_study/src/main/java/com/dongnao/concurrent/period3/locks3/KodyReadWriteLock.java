package com.dongnao.concurrent.period3.locks3;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

//读写锁里面的代码， 和ReentrantLock代码，有大量重复
public class KodyReadWriteLock {

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


    public void lockShared(){
        mask.lockShared();
    }

    public int tryLockShared(){
        return tryLockShared();
    }

    public void unlockShared(){
        mask.unLockShared();
    }




}
