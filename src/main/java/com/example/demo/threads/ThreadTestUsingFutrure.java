package com.example.demo.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTestUsingFutrure {
    static int count = 0;
    static int count2 = 200000;
    static Boolean status = false;
    
    
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> listInt = new ArrayList<>();
        // create a list to hold the Future object associated with Callable
        List<Future<Integer>> list = new ArrayList<>();
        // Get ExecutorService from Executors utility class, thread pool size is 5
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Callable<Integer> callable;
        Future<Integer> future;
        for (int i = 1; i <= 50; i++) {
            callable = new CallableWorker(i);
            // submit Callable tasks to be executed by thread pool
            future = executor.submit(callable);
            // add Future to the list, we can get return value using Future
            list.add(future);
        }
        // shut down the executor service now
        executor.shutdown();
        // Wait until all threads are finish
        while (!executor.isTerminated()) {
            // Running ...
        }
        int sum = 0;
        for (Future<Integer> f : list) {
            try {
                sum = f.get() > sum ? f.get() : sum;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finished all threads: ");
        System.out.println("Sum all = " + sum);
    }
}