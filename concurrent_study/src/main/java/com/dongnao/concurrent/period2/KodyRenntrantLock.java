package com.dongnao.concurrent.period2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class KodyRenntrantLock implements Lock {

    //onwer
    volatile Thread owner;

    //count
    AtomicInteger count = new AtomicInteger(0);

    //等待队列
    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();


    @Override
    public boolean tryLock() {
        //判断count是否为0， count！= 0 锁被占用，
        int ct = count.get();
        if (ct !=0){
            //判断是否是当前编程，若是，做重入
            if (owner == Thread.currentThread()){
                count.set(ct +1);
                return true;
            }else{
                //若不是，返回false
                return false;
            }
        }else{  //所未被占用,CAS抢锁
            if (count.compareAndSet(ct, ct+1)){
                owner = Thread.currentThread();
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public void lock() {
        if (!tryLock()){
            //如果抢锁失败
            waiters.offer(Thread.currentThread());

            for (;;){
                //判断是否是队列头部，若是，才抢锁
                Thread head = waiters.peek();
                if (head == Thread.currentThread()){
                    //抢锁
                    if (!tryLock()){
                        //抢锁失败，继续挂起
                        LockSupport.park();
                    }else{
                        //成功，出队列
                        waiters.poll();
                        return;
                    }
                }else{      //如果不是头部，说明是伪唤醒导致的，继续挂起，
                    LockSupport.park();
                }
            }
        }
    }

    @Override
    public void unlock() {
        if (tryUnlock()){
            Thread th = waiters.peek();
            if (th !=null){
                LockSupport.unpark(th);
            }
        }
    }

    public boolean tryUnlock(){
        //首先判断，是否是当前线程
        if (owner != Thread.currentThread()){
            throw new IllegalMonitorStateException();
        }else{
            //若是，将count-1
            int ct = count.get();
            int nextc = ct-1;
            count.set(nextc);

            //判断count是否为0
            if (nextc == 0){
                owner = null;
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
