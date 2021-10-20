package com.example.demo.threads;

import static com.jayway.awaitility.Awaitility.await;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

public class KillingThreadIdTest {
    private static ExecutorService executorService;
    @BeforeClass
	public static void setup() {
		executorService = Executors.newCachedThreadPool(); 
	}
    @Test
    public void whenStoppedThreadIsStopped() throws InterruptedException {
        // MyTask killingThreadId = new MyTask();
        int interval = 50000;
        new KillingThreadId("t1",interval);
        new KillingThreadId("t2",interval);
        KillingThreadId.stopThread("t1");

    }

    @Test
    public void whenFixedPoolStoppedThreadIsStopped() throws InterruptedException {
        // MyTask killingThreadId = new MyTask();
        int interval = 50000;
        KillingThreadId2 t1 = new KillingThreadId2("t1",interval);
        KillingThreadId2 t2 = new KillingThreadId2("t2",interval);

        // add fixed pool 
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.execute(t1);
        executor.execute(t2);
        KillingThreadId.stopThread("t1");
    }
}
