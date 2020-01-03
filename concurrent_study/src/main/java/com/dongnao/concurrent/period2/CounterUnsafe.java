package com.dongnao.concurrent.period2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CounterUnsafe {
    volatile int i = 0;

    private static Unsafe unsafe = null;

    private static long valueOffset;

    static {
         //unsafe = Unsafe.getUnsafe();
        try{
            //@@@4 java经常出现这样的情况,设计上不让你...,就像抢电视剧中的强吻
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);

            //@@@5 我们用Unsafe的目标是什么？不是玩反射吧？
            Field iField = Counter.class.getDeclaredField("i");
            valueOffset = unsafe.objectFieldOffset(iField);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void add() {
        for (;;){
            int curValue = unsafe.getIntVolatile(this, valueOffset);
            //每次CAS都是一次尝试
            if (unsafe.compareAndSwapInt(this, valueOffset, curValue, curValue + 1))
                break;
        }
    }

}



