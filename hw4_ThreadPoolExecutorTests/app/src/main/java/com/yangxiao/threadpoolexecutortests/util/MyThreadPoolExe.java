package com.yangxiao.threadpoolexecutortests.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yangxiao on 10/31/2017.
 */

public class MyThreadPoolExe extends ThreadPoolExecutor{
	
	private boolean isPaused;
	private ReentrantLock pauseLock = new ReentrantLock();
	private Condition unpaused = pauseLock.newCondition();
	private MyThreadPoolListener listener;
	Handler mHandler;
	
	public MyThreadPoolExe(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
	                       BlockingQueue<Runnable> workQueue, MyThreadPoolListener listener) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.listener = listener;
		mHandler = new Handler(Looper.getMainLooper()){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
			}
		};
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		pauseLock.lock();
		try {
			while (isPaused) unpaused.await();
		} catch (InterruptedException ie) {
			t.interrupt();
		} finally {
			pauseLock.unlock();
		}
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				listener.threadCount(getActiveCount());
			}
		});
		
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				listener.threadCount(getActiveCount());
			}
		});
	}
	
	@Override
	protected void terminated() {
		super.terminated();
	}
	
	public void pause() {
		pauseLock.lock();
		try {
			isPaused = true;
		} finally {
			pauseLock.unlock();
		}
	}
	
	public void resume() {
		pauseLock.lock();
		try {
			isPaused = false;
			unpaused.signalAll();
		} finally {
			pauseLock.unlock();
		}
	}
	
	
	public interface MyThreadPoolListener{
		void threadCount(int count);
	}
}
