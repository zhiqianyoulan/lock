package com.dongnao.concurrent.period1;

/**
 * 线程调用使用runnable方式启动线程
 * @ClassName: Demo1_Thread  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo1_Thread {
    public static void main(String args[]){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("kody is handsome...");

                //new Student()
                //stu.shout()

                //...

                System.out.println("end...");
            }
        };

        //重写run方法
        new Thread(task).start();


    }

}


