package com.example.demo.interviews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadPoolInterview {


    // Tạo 50 thread mỗi thread loop 200K in ra 10 triệu;static int count = 0; yêu cầu thread chạy song song
    static int count = 0;
	public static void main(String[] args) throws InterruptedException, IOException {
		ArrayList<Thread> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 200000; i++) {
						count++;
						// System.out.print(count);  
					}
				}
			});
			th.setName("name " + i);
			th.start();
			list.add(th);
		}
		TimerTask task = new TimerTask() {
			public synchronized void run() {
				System.out.print("\033[H\033[2J");  
				System.out.flush();  
				 
				for (Thread t : list) {
					String name = t.getName();
					Thread.State state = t.getState();
					
					int priority = t.getPriority();
					String type = t.isDaemon() ? "Daemon" : "Normal";
					System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
					try {
						t.join();
					} catch (InterruptedException ex) {
					}
				}
				System.out.print(count);  
			}
		};
		Timer timer = new Timer("Timer");
		timer.schedule(task, 0, 2000);
	}

}