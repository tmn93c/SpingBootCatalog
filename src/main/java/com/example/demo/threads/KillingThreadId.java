package com.example.demo.threads;

import java.util.Set;

public class KillingThreadId implements Runnable {

    // to stop the thread
    int sleep = 500;
    private boolean exit;
 
    private String name;
    Thread t;
 
    KillingThreadId(String threadname, int sleep) throws InterruptedException
    {
        this.sleep = sleep;
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        exit = false;
        t.start(); // Starting the thread
        // t.join();
    }
 
    // execution of thread starts from run() method
    public void run()
    {
        int i = 0;
        if (!t.isInterrupted()) {
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(sleep);
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(name + " Stopped.");
    }
    
    // for stopping the thread
    public static void stopThread(String threadname)
    {
        for(Thread t : Thread.getAllStackTraces().keySet()) {
            if(t.getName().equals(threadname)) {
                /* do what you need to do */
                t.interrupt();
                break;
            }
        }
    }
    // for stopping the thread
    public void stop()
    {
        exit = true;
    }
}