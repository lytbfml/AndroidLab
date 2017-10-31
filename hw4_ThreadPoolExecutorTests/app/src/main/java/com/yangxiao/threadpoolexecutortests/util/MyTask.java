package com.yangxiao.threadpoolexecutortests.util;

import java.util.Random;

/**
 * @author Yangxiao on 10/31/2017.
 */

public class MyTask implements Runnable {
	
	private static final String TAG = MyTask.class.getSimpleName();
	
	public MyTask() {
	
	}
	
	@Override
	public void run() {
		
		try {
			Random rand = new Random();
			//1s - 5s
			long sleepTime = rand.nextInt(4000) + 1000;
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
