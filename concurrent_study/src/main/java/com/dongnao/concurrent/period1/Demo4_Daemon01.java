package com.dongnao.concurrent.period1;

public class Demo4_Daemon01 {
    public static void main(String args[]){
        Thread th = new Thread(){
            @Override
            public void run() {
                System.out.println("Kody is handsome...");
            }
        };
        th.setDaemon(true);
        
        th.start();

        System.out.println(th.isDaemon());
    }
}
