package com.dongnao.concurrent.period1;

public class Demo6_Thread_State {
    public static void main(String args[]) throws InterruptedException {

        synchronized (Demo6_Thread_State.class){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    //抢锁失败，会进入 Blocked状态
                    synchronized (Demo6_Thread_State.class){
                        //do something ...
                    }
                }
            };

            Thread th = new Thread(task);   //NEW
            System.out.println(th.getState());

            th.start();                 //Runnable
            System.out.println(th.getState());

            Thread.sleep(10000);     //mian进入Timed Waiting状态
        }
        //main执行完，进入Terminated状态
    }


}
