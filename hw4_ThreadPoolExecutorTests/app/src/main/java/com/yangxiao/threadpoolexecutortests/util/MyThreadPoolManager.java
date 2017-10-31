package com.yangxiao.threadpoolexecutortests.util;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Yangxiao on 10/31/2017.
 */

public class MyThreadPoolManager {
	
	public static final String TAG = MyThreadPoolManager.class.getSimpleName();
	
	private static MyThreadPoolManager sInstance;
	
//	public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
	
	private final ThreadPoolExecutor mThreadExecutor;
	
	
	public static MyThreadPoolManager getInstance(int numCore, int numMaxCore, int queue, MyThreadPoolExe.MyThreadPoolListener
			listener)
	{
		if(sInstance == null) {
			Log.e(TAG, "Null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			synchronized (MyThreadPoolManager.class) {
				sInstance = new MyThreadPoolManager(numCore, numMaxCore, queue, listener);
			}
		}
		return sInstance;
	}
	
	private MyThreadPoolManager(int numCore, int numMaxCore, int queue, MyThreadPoolExe.MyThreadPoolListener listener){
		
		mThreadExecutor = new MyThreadPoolExe(
				numCore,
				numMaxCore,
				20L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(queue), listener);
	}
	
	public ThreadPoolExecutor getMythreadPool()
	{
		return mThreadExecutor;
	}
}
