package com.dongnao.concurrent.period1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 任务被执行一次
 * @ClassName: Demo3_GetResult04  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo3_GetResult04 {
    public static void main(String args[]){
        Callable<String> cTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("here I am...");

                return "Kody is Handsome...";
            }
        };

        FutureTask<String> future = new FutureTask(cTask);

        new Thread(future).start();
        new Thread(future).start();
    }
}
