package com.dongnao.concurrent.period2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2_Reentrant {

    //static Lock lock =  new KodyLock();    //不可重入锁
    static Lock lock = new ReentrantLock();     //可重入锁

    public static void main(String args[]) throws InterruptedException {
        lock.lock();    //当前线程已获取锁
        System.out.println("get lock 1...");

        lock.lock();    //再次获取，是否能成功
        System.out.println("get lock 2...");

        lock.unlock();
        lock.unlock();

        lock.unlock();


        new Thread(){
            @Override
            public void run() {
                System.out.println("child:start to get lock...");
                lock.lock();
                System.out.println("child:get lock...");

                lock.unlock();

            }
        }.start();

    }


}
