package com.dongnao.concurrent.period1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable不能直接传入Thread，要用FutureTask包裹
 * Thread th = new Thread(future);
 * @ClassName: Demo3_GetResult03  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo3_GetResult03 {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        Callable<String> cTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //Thread.sleep(5000);
                return "Kody is Handsome..." ;
            }
        };

        //Callable不能直接传入Thread，要用FutureTask包裹
        FutureTask<String> future = new FutureTask<>(cTask);
        Thread th = new Thread(future);
        th.start();

        System.out.println("begain to get...");
        //阻塞
        String result = future.get();
        System.out.println("result:" + result);
    }


}
