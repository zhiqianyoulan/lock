package com.dongnao.concurrent.period2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程共享变量i++，并发线程不安全
 * 解决方案
 * 1.使用原子类
 * 2.使用lock锁
 * 3.使用synchronized
 * @ClassName: Counter  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Counter {
	//AtomicInteger i = new AtomicInteger();
    volatile int i = 0;
    //Lock lock = new ReentrantLock();
    public void add() {
    	//lock.lock();
        i++;
    	//i.getAndIncrement();
        //lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter ct = new Counter();

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        ct.add();
                    }
                }
            }.start();
        }

        Thread.sleep(6000L);
        System.out.println("i:" + ct.i);
    }
}

