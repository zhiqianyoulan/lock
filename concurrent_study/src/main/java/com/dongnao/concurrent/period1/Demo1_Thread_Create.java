package com.dongnao.concurrent.period1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 线程创建用法
 * @ClassName: Demo1_Thread_Create  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo1_Thread_Create {
    public static void main(String args[]){
        //重写Thread Run方法
        new Thread(){
            @Override
            public void run() {
                //do something...
            }
        }.start();

        //实现Runnable
        Runnable rTask = new RunnableTask();
        new Thread(rTask).start();

        //实现Callable
        Callable cTask = new CallableTask();
        new Thread(new FutureTask<>(cTask)).start();
    }
}

class RunnableTask implements Runnable{

    @Override
    public void run() {
        //do something...
    }
}

class CallableTask implements Callable{

    @Override
    public Object call() throws Exception {
        //do something...
        return "result";
    }
}
