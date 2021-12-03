package com.example.demo.threads;

import java.util.concurrent.Callable;

public class CallableWorker implements Callable<Integer> {
    static int count = 0;
    static int count2 = 200000;
    private int num;

    public synchronized static void sync() {
        for (int i = 0; i < count2; i++) {
            count++;
        }
    }

    public CallableWorker(int num) {
        this.num = num;
    }

    public Integer call() throws Exception {
        sync();
        return count;
    }
}