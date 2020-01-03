package com.dongnao.concurrent.period1.coordination;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的用法
 * CyclicBarrier的await方法，等待所有线程准备就绪
 * @ClassName: CyclicBarrier_Demo1  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class CyclicBarrier_Demo1 {

    public static void main(String[] args) {

        // 并发数
        int concurrency = 100;
        // 用于生成随机等待时长的随机数对象
        final Random rd = new Random();

        final CyclicBarrier cb = new CyclicBarrier(concurrency, new Runnable() {
            @Override
            public void run() {
                System.out.println("*****************准备完成！************");
            }
        });

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

                    System.out.println(
                            Thread.currentThread().getName() + " 准备就绪。");

                    // 等待大家都到达
                    try {
                        cb.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                        return;
                    }

                    // 都准备就绪后，再一起开始执行下面的代码
                    System.out.println(
                            Thread.currentThread().getName() + " 开始工作....");
                }
            }).start();
        }

    }
}
