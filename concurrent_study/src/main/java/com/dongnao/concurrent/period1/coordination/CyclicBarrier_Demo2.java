package com.dongnao.concurrent.period1.coordination;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的多次协同方式
 * @ClassName: CyclicBarrier_Demo2  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class CyclicBarrier_Demo2 {

    // 阶段计数
    static int P = 0;

    public static void main(String[] args) {

        // 参与数
        int parties = 3;

        // 创建循环屏障协同对象，指定了参与数 和 barrierAction
        final CyclicBarrier cb = new CyclicBarrier(parties, new Runnable() {
            @Override
            public void run() {
                switch (P) {
                    case 0:
                        System.out.println("******************第一阶段，大家都到公司了，出发去公园！");
                        break;
                    case 1:
                        System.out
                                .println("******************第二阶段：大家都到公园大门，出发去餐厅！");
                        break;
                    case 2:
                        System.out.println("******************第三阶段：大家都到餐厅了，开始用餐！");
                        break;
                }
                System.out.println(
                        "屏障动作是" + Thread.currentThread().getName() + " 执行的");
                // 阶段数增1
                P++;
            }
        });

        // 用于生成随机等待时长的随机数对象
        final Random rd = new Random();

        for (int i = 0; i < parties; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 员工
                    String staff = "员工【" + Thread.currentThread().getName()
                            + "】";

                    try {
                        /* 第一阶段任务：从家出发，到公司 */
                        System.out.println(staff + "从家出发了...");
                        Thread.sleep(rd.nextInt(5000));
                        System.out.println(staff + "到达公司！");

                        // 协同： 第一次等待大家到齐
                        cb.await();

                        /* 第二阶段任务：去公园玩，再到公园大门口集合 */
                        System.out.println(staff + "出发去公园玩。。。");
                        // 在公园玩了很久
                        Thread.sleep(rd.nextInt(5000));
                        System.out.println(staff + "到达公园大门集合！");

                        // 协同： 第二次等待大家到齐
                        cb.await();

                        /* 第三阶段任务：去餐厅 */
                        System.out.println(staff + "出发去餐厅。。。");
                        // 经过一段时间后，到达餐厅
                        Thread.sleep(rd.nextInt(5000));
                        System.out.println(staff + "到达餐厅！");

                        // 协同： 第三次等待大家到齐
                        cb.await();

                        /* 第四阶段任务：就餐 */
                        System.out.println(staff + "开始用餐。。。");
                        Thread.sleep(rd.nextInt(5000));
                        // 吃完饭回家了
                        System.out.println(staff + "回家了。。。");

                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

}
