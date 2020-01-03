package com.dongnao.concurrent.period3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

public class Test_CyclicBarrier {
    public static void main(String args[]){

        CyclicBarrier barrier  = new CyclicBarrier(4);

        //传入一个Runnable，打印栅栏

        for (int i=0; i< 100; i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("上到摩天轮...");
                }
            }.start();
            LockSupport.parkNanos(1000 * 1000 * 1000L);
        }
    }
}
