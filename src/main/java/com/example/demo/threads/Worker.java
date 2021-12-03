package com.example.demo.threads;

import java.util.concurrent.CountDownLatch;

class Worker extends Thread {
    private int delay;
    private CountDownLatch latch;
    static int count2 = 200000;
    static int count = 0;
    private int num;

    public Worker(int delay, CountDownLatch latch, String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    public synchronized static void sync() {

    }

    @Override
    public void run() {
        for (int i = 0; i < count2; i++) {
            count++;
        }
        System.out.println(count);
        latch.countDown();
        System.out.println(Thread.currentThread().getName() +
                " has finished");

    }
}
