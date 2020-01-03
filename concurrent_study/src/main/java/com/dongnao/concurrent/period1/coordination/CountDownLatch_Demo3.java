package com.dongnao.concurrent.period1.coordination;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch协同执行任务用法
 * cdln.countDown() 计数器减一
 * cdln.await() 等待别的线程
 * 当计数器减为0后，所有等待的线程被释放，开始继续执行任务
 * @ClassName: CountDownLatch_Demo3  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class CountDownLatch_Demo3 {
    public static void main(String[] args) {

        // 并发数
        int concurrency = 100;

        // 创建倒计数为并发数的N锁存器，用来协同等待N个完成
        final CountDownLatch cdln = new CountDownLatch(concurrency);

        // 用于生成随机等待时长的随机数对象
        final Random rd = new Random();

        // 循环创建需要数量的并发线程，并启动线程
        for (int i = 0; i < concurrency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 代进行时间不等的准备工作....
                    try {
                        Thread.sleep(rd.nextInt(5000));
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    // 准备就绪
                    System.out.println(
                            Thread.currentThread().getName() + " 准备就绪。");

                    // 调用countDown()报告完成任务（准备就绪）
                    cdln.countDown();

                    // 协同：等待别的线程都准备就绪
                    try {
                        cdln.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }


                    // 从等待中被释放，继续执行
                    System.out.println(
                            Thread.currentThread().getName() + " 开始工作....");
                }
            }).start();
        }


    }
}
