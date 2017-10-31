package com.yangxiao.threadpoolexecutortests.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yangxiao.threadpoolexecutortests.R;
import com.yangxiao.threadpoolexecutortests.util.MyTask;
import com.yangxiao.threadpoolexecutortests.util.MyThreadPoolManager;

/**
 * @author Yangxiao on 10/31/2017.
 */

public class ThreadExeFragment extends Fragment{
	
	public static final String TAG = ThreadExeFragment.class.getSimpleName();
	
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_thread_exe, container, false);
		
		
		
		return view;
	}
	
	
	public void enQThreadExe()
	{
//		for(int i = 0; i < holder.num_tasks; i++)
//		{
//			MyThreadPoolManager.getInstance(holder.num_threads, holder.num_threads_max).getMythreadPool().execute(new MyTask());
//		}
	}
	
}
