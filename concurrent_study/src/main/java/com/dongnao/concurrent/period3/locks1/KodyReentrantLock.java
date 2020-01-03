package com.dongnao.concurrent.period3.locks1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class KodyReentrantLock implements Lock {
    //记录锁的拥有者
    AtomicReference<Thread> owner = new AtomicReference<>();

    //等待队列，锁池
    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    //重入次数
    AtomicInteger count = new AtomicInteger(0);


    @Override
    public boolean tryLock() {
        //判断count值是否为0
        int ct = count.get();
        if (ct !=0){
            //锁被占用,判断是否是当前线程
            if (owner.get() == Thread.currentThread()){
                count.set(ct + 1);
                return true;
            }else{
                return false;
            }
        }else{
            //所未被占用，用CAS修改count值，抢锁
            if (count.compareAndSet(ct, ct + 1)){
                owner.set(Thread.currentThread());
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public void lock() {
        if (!tryLock()){
            //将线程放入度列排队
            waiters.offer(Thread.currentThread());

            for (;;){
                //判断是否是队列头部,防止伪唤醒
                Thread head = waiters.peek();
                if (head == Thread.currentThread()){
                    if (!tryLock()){
                        //没抢到所，继续park
                        LockSupport.park();
                    }else{
                        //抢到锁，将线程出队列
                        waiters.poll();
                        return;
                    }
                }else{
                    //为唤醒后，继续park
                    LockSupport.park();
                }
            }
        }
    }

    @Override
    public void unlock() {
        if (tryUnlock()){
            Thread head = waiters.peek();
            if  (head != null){
                LockSupport.unpark(head);
            }
        }
    }

    public boolean tryUnlock(){
        //判断是否是当前线程，若不是，抛异常
        if (owner.get() != Thread.currentThread()){
            throw new IllegalMonitorStateException();
        }else{
            //需要将count-1
            int ct = count.get();
            int nextc = ct -1 ;
            count.set(nextc);

            //若count变为0，解锁成功
            if (nextc == 0){
                owner.compareAndSet(Thread.currentThread(), null);
                return true;
            }else{
                return false;
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
