package com.dongnao.concurrent.period3;

import java.util.concurrent.CountDownLatch;

public class Test_CountDownLatch {

    /*
    10、9、8 ... 3、2、1、0 点火
    //有好多任务，等任务执行完，
    //才往后继续执行
     */
    public static void test01() throws InterruptedException {
        CountDownLatch ctl = new CountDownLatch(11);

        //任务在异步的执行
        for (int i=10; i>=0; i--){
            int number = i;
            new Thread(){
                @Override
                public void run() {
                    System.out.println(">>>>" + number);
                    //当任务执行完，将count-1
                    ctl.countDown();
                }
            }.start();
            Thread.sleep(1000L);
        }

        //通过await来阻塞住
        ctl.await();
        System.out.println("点火...");
    }

    /*
    预备，跑！！！
     */
    public static void test02() throws InterruptedException {
        CountDownLatch ctl = new CountDownLatch(1);

        for (int i=0; i<6; i++){
            int number = i;
            new Thread(){
                @Override
                public void run() {
                    try {
                        ctl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("运动员%d起跑", number));

                }
            }.start();
        }

        System.out.println("预备");
        Thread.sleep(3000);
        ctl.countDown();
        System.out.println("跑！！！");

    }



    public static void main(String args[]) throws InterruptedException {
        test01();
        test02();
    }
}
