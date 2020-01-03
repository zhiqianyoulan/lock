package com.dongnao.concurrent.period2;

public class CounterSync {
    volatile int i = 0;

    public synchronized void add() {
        i++;
    }
}
