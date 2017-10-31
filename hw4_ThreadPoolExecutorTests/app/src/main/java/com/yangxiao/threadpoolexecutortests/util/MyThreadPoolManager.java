package com.yangxiao.threadpoolexecutortests.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Yangxiao on 10/31/2017.
 */

public class MyThreadPoolManager {
	
	private static MyThreadPoolManager sInstance;
	
	public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
	
	private final ThreadPoolExecutor mThreadExecutor;
	
	public static MyThreadPoolManager getInstance(int numCore, int numMaxCore)
	{
		if(sInstance == null) {
			synchronized (MyThreadPoolManager.class) {
				sInstance = new MyThreadPoolManager(numCore, numMaxCore);
			}
		}
		return sInstance;
	}
	
	private MyThreadPoolManager(int numCore, int numMaxCore){
		
		mThreadExecutor = new ThreadPoolExecutor(
				numCore,
				numMaxCore,
				60L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
	}
	
	public ThreadPoolExecutor getMythreadPool()
	{
		return mThreadExecutor;
	}
}
