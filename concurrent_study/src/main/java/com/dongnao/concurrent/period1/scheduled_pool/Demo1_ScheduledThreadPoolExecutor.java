package com.dongnao.concurrent.period1.scheduled_pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Demo1_ScheduledThreadPoolExecutor {
    static ScheduledThreadPoolExecutor pool =
            new ScheduledThreadPoolExecutor(5);


    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        test3();
    }


    // 提交一个 一次性任务
    private static void test1() throws Exception {
        //定义一个Runnable对象
        Runnable cmd = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务执行了。。。");
            }
        };

        //提交一个一次性任务
        pool.schedule(cmd, 3, TimeUnit.SECONDS);


    }

    //scheduleWithFixedDelay 提交一个重复执行的任务
    public static void test2(){
        Runnable cmd = new Runnable() {
            @Override
            public void run() {
                LockSupport.parkNanos(1000 * 1000 * 1000 * 1L);  //暂停一秒钟，和Thread.sleep(1000L) 效果类似
                System.out.println("任务执行，现在时间：" + System.currentTimeMillis());
            }
        };

        //提交定时任务
        pool.scheduleWithFixedDelay(cmd, 1000, 2000, TimeUnit.MILLISECONDS);
    }

    //scheduleAtFixedRate 提交一个重复执行的任务
    private static void test3() throws Exception {
        Runnable cmd = new Runnable() {
            @Override
            public void run() {
                LockSupport.parkNanos(1000 * 1000 * 1000 * 1L);  //暂停一秒钟，和Thread.sleep(1000L) 效果类似
                System.out.println("任务执行，现在时间：" + System.currentTimeMillis());
            }
        };

        pool.scheduleAtFixedRate(cmd, 1000, 2000, TimeUnit.MILLISECONDS);


    }

}
