package com.dongnao.concurrent.period3;

public class ThreadLocalTest {
    static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String args[]){
        new Thread(){
            @Override
            public void run() {
                tl.set("Kody");
                System.out.println("t1:" + tl.get());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println("t2:" + tl.get());
            }
        }.start();

    }
}
