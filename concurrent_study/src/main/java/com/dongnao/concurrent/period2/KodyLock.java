package com.dongnao.concurrent.period2;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

//一个不可重入锁的简单实现
public class KodyLock implements Lock {
    //锁拥有者
    AtomicReference<Thread> owner = new AtomicReference<>();

    //等待队列
    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();



    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public void lock() {
        if (!tryLock()){
            //加入等待队列
            waiters.offer(Thread.currentThread());


            for (;;){
                //若线程是队列头部，尝试加锁
                Thread head = waiters.peek();
                if (head == Thread.currentThread()){
                    if (!tryLock()){
                        LockSupport.park();
                    }else{
                        waiters.poll();
                        return;
                    }
                }else{
                    LockSupport.park();
                }
            }
        }
    }

    public boolean tryUnlock(){
        return owner.compareAndSet(Thread.currentThread(), null);
    }


    @Override
    public void unlock() {
        if (tryUnlock()){
            Thread head = waiters.peek();
            if (head !=null){
                LockSupport.unpark(head);
            }
        }
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
