package com.dongnao.concurrent.period4.queue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class Demo4_PriorityBlockingQueue {

    static PriorityBlockingQueue<Student> queue = new PriorityBlockingQueue<>(5,
            new Comparator<Student>() {
                @Override
                public int compare(Student st1, Student st2) {
                    if (st1.age < st2.age){
                        return 1;
                    }else if(st1.age == st2.age){
                        return 0;
                    }else{
                        return -1;
                    }
                }
            });
    
    public static void main(String args[]){
        queue.put(new Student(10, "emily"));
        queue.put(new Student(20, "Tony"));
        queue.put(new Student(5, "baby"));

        for (; queue.size() > 0; ){
            try {
                System.out.println(queue.take().name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Student{
    public int age;
    public String name;

    public Student(int age, String name){
        this.age = age;
        this.name = name;
    }
}