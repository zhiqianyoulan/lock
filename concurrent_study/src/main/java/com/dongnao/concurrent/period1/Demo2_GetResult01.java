package com.dongnao.concurrent.period1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * runnable示例
 * @ClassName: Demo2_GetResult01  
 * @Description: TODO()  
 * @author sx  
 * @date 2020年1月1日
 */
public class Demo2_GetResult01 {
    private static List<Integer> resultList = new ArrayList<>();

    public static void main(String args[]) throws InterruptedException {
        for (int i=0; i<10; i++){

            Runnable rTask = new Runnable() {
                @Override
                public void run() {
                    // do something ...

                    int result = new Random().nextInt();
                    resultList.add(result);     //返回结果
                }
            };
            new Thread(rTask).start();
        }

        Thread.sleep(1000);
        System.out.println(resultList);
    }

}
