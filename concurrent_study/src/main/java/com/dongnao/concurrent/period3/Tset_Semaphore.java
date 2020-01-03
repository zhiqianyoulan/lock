package com.dongnao.concurrent.period3;

import java.util.concurrent.Semaphore;

public class Tset_Semaphore {
    static Semaphore sp = new Semaphore(6);

    public static void main(String args[]){
        for (int i=0; i<1000; i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        sp.acquire();       //抢信号量、就是在加锁
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queryDB("localhost:3006");
                    sp.release();       //释放信号量，就是解锁
                }
            }.start();
        }
    }

    public static void queryDB(String url){
        System.out.println("query " + url);
    }
}
