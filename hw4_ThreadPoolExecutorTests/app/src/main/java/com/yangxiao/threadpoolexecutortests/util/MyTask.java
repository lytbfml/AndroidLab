package com.yangxiao.threadpoolexecutortests.util;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.Random;
import android.os.Handler;

/**
 * @author Yangxiao on 10/31/2017.
 */

public class MyTask implements Runnable{
	
	private static final String TAG = MyTask.class.getSimpleName();
	TaskListener listener;
	Handler mHandler;
	
	public MyTask(TaskListener listener) {
		this.listener = listener;
		mHandler = new Handler(Looper.getMainLooper()){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
			}
		};
	}
	
	public interface TaskListener{
		void taskStarted();
		void taskFinished();
	}
	
	@Override
	public void run() {
		Log.v(TAG, "Mytask Begin");
		try {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					listener.taskStarted();
				}
			});
			
			Random rand = new Random();
			
			//1s - 5s
			long sleepTime = rand.nextInt(4000) + 1000;
			
			Log.d(TAG, "Mytask - Sleeping for " +  sleepTime + " milliseconds...");
			
			Thread.sleep(sleepTime);
			
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					listener.taskFinished();
				}
			});
			
			Log.d(TAG, "Mytask finished");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
