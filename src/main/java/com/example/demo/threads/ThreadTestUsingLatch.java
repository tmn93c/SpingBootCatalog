package com.example.demo.threads;

import java.util.concurrent.CountDownLatch;

public class ThreadTestUsingLatch {
    static int count = 0;
    static int count2 = 200000;
    static Boolean status = false;
    
    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(50);
        // Main thread will wait until all thread finished
        for (int i = 1; i <= 50; i++) {
            Worker worker = new Worker(1000, latch, "WORKER-"+i);
            worker.start();
        }
        latch.await();
        System.out.println("Finished all threads: ");
    }
}