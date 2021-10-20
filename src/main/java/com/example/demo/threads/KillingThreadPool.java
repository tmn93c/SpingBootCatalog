package com.example.demo.threads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KillingThreadPool {

    public static void main(String[] args) {  
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);  
        KillingThreadId.stopThread("t1");
    }  
}