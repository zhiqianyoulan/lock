package com.dongnao.concurrent.period1;

import java.util.concurrent.locks.LockSupport;

public class Demo12_TooMoreThraed {
    public static void main(String args[]) throws InterruptedException {
        int count = 100;  //new Integer(args[0]);
        for (int i=0;i<1000000;i++){
            new Thread(){
                @Override
                public void run() {
                    LockSupport.park();
                }
            }.start();
            Thread.sleep(10);
            System.out.println("creating Thread" + i);
        }

    }
}
