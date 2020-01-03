package com.dongnao.concurrent.period1;

/**
 * 线程中断机制(interrupt)
 * @ClassName: Demo8_interrupt_flag  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo8_interrupt_flag {

    public static void main(String[] args) throws InterruptedException {

        Thread th = new Thread(){
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("kody is handsome...");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        currentThread().interrupt();
                    }
                }
            }
        };
        th.start();
        Thread.sleep(5000);
        th.interrupt();

    }



}
