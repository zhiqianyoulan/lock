package com.dongnao.concurrent.period1.coordination;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatch_Demo1 {
    public static int[] arr = new int[1000];

    public static void main(String args[]) throws InterruptedException {
        //初始化到计数器
        CountDownLatch ctl = new CountDownLatch(0);

        for (int i=0; i<10; i++){
            final int j = i;
            new Thread(){
                @Override
                public void run() {
                    Random ram = new Random();
                    for (int k=j*100;k<j*100 +100;k++){
                        arr[k] = ram.nextInt() +1;
                    }
                    ctl.countDown();    //完成后，计数器减一
                }
            }.start();
        }

        //计数器减为0时，结束阻塞
        ctl.await();

        //使用数组
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
