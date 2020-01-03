package com.dongnao.concurrent.period1.coordination;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatch_Demo2 {
    public static void main(String args[]){
        CountDownLatch ctl = new CountDownLatch(1);

        for (int i=10; i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        ctl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //访问 database
                }
            }.start();
        }

        //让计数减为0，所有线程一起执行
        ctl.countDown();
    }
}
