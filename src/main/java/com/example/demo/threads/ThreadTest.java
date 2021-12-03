package com.example.demo.threads;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ThreadTest {
    static int count = 0;
    static int count2 = 200000;
    static Boolean status = false;
    public void sync(){
        for (int i=0;i<10;i++){
           System.out.println("Running "+Thread.currentThread().getName());
       }
  }
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> list = new ArrayList<>();
        ArrayList<Integer> listInt = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    for (int i = 0; i < count2; i++) {
                        count++;
                        if(count == (count2-1))System.out.println("Count " + count);
                        
                    }
                    listInt.add(1);
                }
            });
            th.start();
            
            list.add(th);
        }
        List<Thread> allThreads = new ArrayList<Thread>();
        for(Thread thread : list){

            if(null != thread){
    
                  if(thread.isAlive()){
    
                        allThreads.add(thread);
    
                  }
    
            }
    
      }
    
    //   while(!allThreads.isEmpty()){
    
    //         Iterator<Thread> ite = allThreads.iterator();
    
    //         while(ite.hasNext()){
    
    //               Thread thread = ite.next();
    
    //               if(!thread.isAlive()){
    
    //                    ite.remove();
    //               }
    
    //         }
    
    //    }
        // System.out.println("Count " + count);
    }
}