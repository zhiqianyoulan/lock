package com.dongnao.concurrent.period1;

import java.util.concurrent.*;

/**
 * 线程池创建线程，使用FutureTask接收线程中callable任务返回的结果
 * @ClassName: Demo3_GetResult02  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo3_GetResult02 {
    public static void main(String args[]) throws ExecutionException, InterruptedException {

        Callable<Integer> cTask = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("子线程在进行计算");
                Thread.sleep(3000);
                int sum = 0;
                for(int i=0;i<100;i++)
                    sum += i;
                return sum;
            }
        };


        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        //提交任务并获取执行结果
        Future<Integer> future = executor.submit(cTask);

        if (future.get() !=null){
            System.out.println(future.get());
        }

        //关闭线程池
        executor.shutdown();
    }
}
