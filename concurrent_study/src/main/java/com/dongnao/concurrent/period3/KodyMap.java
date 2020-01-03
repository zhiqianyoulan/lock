package com.dongnao.concurrent.period3;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class KodyMap {
    private HashMap<Object, Object> map = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    //Hashtable<String,String> table ;            //put get  方法都用synchronized修饰

    //读取的时候，可以并发进行
    //写的时候，只允许一个线程进行

    //ReadWriteLock帮我们提升互斥锁的性能


    //currentHashMap

    public Object get(Object key){
        lock.readLock().lock();
        try {
            return map.get(key);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void put(Object key, Object value){
        lock.writeLock().lock();
        try {
            map.put(key, value);
        }finally {
            lock.writeLock().unlock();
        }
    }


}
