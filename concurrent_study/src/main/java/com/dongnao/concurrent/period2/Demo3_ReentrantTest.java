package com.dongnao.concurrent.period2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3_ReentrantTest {

    private static  int i = 0;
    //private final static Lock lc = new ReentrantLock();     //可重入锁

    private final static Lock lc = new KodyLock();   //不可重入锁


    public static void recursive() throws InterruptedException {
        lc.lock();

        i++;

        System.out.println("here i am...");
        Thread.sleep(1000L);
        recursive();

        lc.unlock();
    }


    public static void main(String args[]) throws InterruptedException {

       recursive();

    }
}
