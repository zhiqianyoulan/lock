package com.dongnao.concurrent.period2;

public class Demo1_CounterTest{
    public static void main(String[] args) throws InterruptedException {
        final CounterLock ct = new CounterLock();

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        ct.add();
                    }
                }
            }.start();
        }

        Thread.sleep(6000L);
        System.out.println("i:" + ct.i);
    }
}

