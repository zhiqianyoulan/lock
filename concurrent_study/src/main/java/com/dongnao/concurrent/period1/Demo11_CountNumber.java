package com.dongnao.concurrent.period1;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport.park与unpark
 * 设置线程顺序 :A.setNextThread(B); //指定下一个线程
        	   B.setNextThread(C);
        	   C.setNextThread(A);
 *  LockSupport.unpark(this.nextThread)用于唤醒下一个线程
 * @ClassName: Demo11_CountNumber  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo11_CountNumber {

    private volatile int count = 1;     //记录count值
    //private int threadCount = 3;        //共有多少个线程呢

    class Printer extends Thread{

        private int order = 0;
        private Thread nextThread = null;   //每个线程，要知道下一个线程的引用

        public Printer (int order){
            this.order = order;
        }

        public void setNextThread(Thread nextThread){
            this.nextThread = nextThread;
        }

        @Override
        public void run() {
            while (count <=100){
                while ((count-1)%3 != this.order){      //判断顺序对不对
                    LockSupport.park();     //顺序不对，调用park，将当前线程挂起
                }
                System.out.println(order + ":  "+ count);   //打印题目要求的信息
                count++;                                //将count+1
                LockSupport.unpark(this.nextThread);    //唤醒下一个线程
                LockSupport.park();                     //挂起当前线程
            }
        }
    }

    public static void main(String args[]){
        Demo11_CountNumber demo = new Demo11_CountNumber();

        Printer A = demo.new Printer(0);    //给线程指定不同的顺序
        Printer B = demo.new Printer(1);
        Printer C = demo.new Printer(2);

        A.setNextThread(B); //指定下一个线程
        B.setNextThread(C);
        C.setNextThread(A);

        A.start();      //启动线程
        B.start();
        C.start();
    }

}