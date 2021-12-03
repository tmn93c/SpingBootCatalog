package com.example.demo.threads;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ThreadPoolInterview1 {


    // Tạo 50 thread mỗi thread loop 200K in ra 10 triệu;static int count = 0;
    static int count = 0;
	public static void main(String[] args) throws InterruptedException {
		ArrayList<Thread> list = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 200000; i++) {
						count++;
					}
				}
			});
			th.start();
			list.add(th);
		}
        

		// for (Thread t : list) {
        //     String name = t.getName();
        //     Thread.State state = t.getState();
        //     int priority = t.getPriority();
        //     String type = t.isDaemon() ? "Daemon" : "Normal";
        //     System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
		// 	try {
		// 		t.join();
		// 	} catch (InterruptedException ex) {
		// 	}
		// }
        
		System.out.println("count: " + count);
	}

}