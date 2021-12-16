package com.example.demo.threads;

public class MyRunnable implements Runnable {
    private final int countUntil;
    int sum = 0;
    MyRunnable(int countUntil) {
        this.countUntil = countUntil;
    }

    @Override
    public void run() {
        for (int i = 0; i < countUntil; i++) {
            sum++;
        }
        System.out.println(sum);
    }
}
