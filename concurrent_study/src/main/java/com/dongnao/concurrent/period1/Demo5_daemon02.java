package com.dongnao.concurrent.period1;


/**
 * DOTO 守护线程用法，当主线程中调用子线程的setDaemon(true)方法，把主线程设置为守护线程
 * 当主线程执行完毕后，子线程也立刻结束
 * @ClassName: Demo5_daemon02  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo5_daemon02 {
    public static void main(String args[]) throws InterruptedException {
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("child thread done...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.setDaemon(true);
        th.start();

        Thread.sleep(1000);
    }
}
