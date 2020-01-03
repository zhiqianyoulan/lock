package com.dongnao.concurrent.period1;

public class Demo10_CountNumber_advanced {
    public volatile static int count = 0;
    public static final Object lock = new Object();

    public static void main(String args[]){

        Thread t1 = new MyThread(0);        //给不同的线程指定不同的order
        Thread t2 = new MyThread(1);
        Thread t3 = new MyThread(2);

        t1.start();
        t2.start();
        t3.start();
    }

    static class MyThread extends Thread{
        private int order;
        public MyThread(int order){
            this.order = order;
        }

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock){        //获取锁
                    while (count%3 != order){           //判断顺序是否正确
                        try {
                            lock.wait();                //不正确，调用wait挂起线程
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(order + ":" + count);
                    count++;        //将count值加1
                    lock.notifyAll();       //唤醒所有的线程
                }
            }
        }
    }

}
