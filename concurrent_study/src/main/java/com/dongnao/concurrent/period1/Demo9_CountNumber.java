package com.dongnao.concurrent.period1;

/**
 * 使用synchronized互斥锁，使线程按顺序执行
 * notify 无法确认唤醒的线程到底是不是下一个
 * @ClassName: Demo9_CountNumber  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo9_CountNumber {
    private volatile static int count = 0;      //记录count值
    private static final Object lock = new Object();    //所有线程，公用一个锁对象

    public static void main(String args[]){
        Thread t1 = new Thread(() -> {
            int order = 0;      //指定线程执行的顺序
            while (count <= 100) {
                synchronized (lock){
                    while (count%3 != order){       //判断顺序是否正确
                        try {
                            lock.wait();        //不正确，wait挂起线程，释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(order + ":" + count);    //没有挂起，说明顺序正确，打印消息
                    count++;        //count+1
                    lock.notifyAll();   //唤醒所有的现场
                }
            }
        });
        Thread t2 = new Thread(() -> {
            int order = 1;      //指定线程执行的顺序
            while (count <= 100) {
                synchronized (lock){
                    while (count%3 != order){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(order + ":" + count);
                    count++;
                    lock.notifyAll();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            int order = 2;      //指定线程执行的顺序
            while (count <= 100) {
                synchronized (lock){
                    while (count%3 != order){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(order + ":" + count);
                    count++;
                    lock.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
