package com.yangxiao.threadpoolexecutortests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.yangxiao.threadpoolexecutortests.settings.SettingActivity;
import com.yangxiao.threadpoolexecutortests.thread.ThreadExeActivity;
import com.yangxiao.threadpoolexecutortests.util.Constants;
import com.yangxiao.threadpoolexecutortests.util.MyTask;
import com.yangxiao.threadpoolexecutortests.util.MyThreadPoolExe;
import com.yangxiao.threadpoolexecutortests.util.MyThreadPoolManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyTask.TaskListener, MyThreadPoolExe.MyThreadPoolListener {
	
	public static final String TAG = MainActivity.class.getSimpleName();
	
	public static final int PrefRequest = 2;
	
	TextView num_tasks, num_threads, num_threads_max, num_queue;
	TextView num_tasks_completed_view, num_threads_active_view;
	
	private int num_tasks_completed = 0;
	private int num_tasks_started = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		num_tasks = findViewById(R.id.num_tasks);
		num_threads = findViewById(R.id.num_threads);
		num_threads_max = findViewById(R.id.num_threads_max);
		num_queue = findViewById(R.id.num_queue);
		
		num_tasks_completed_view = findViewById(R.id.num_tasks_complete);
		num_threads_active_view = findViewById(R.id.num_threads_active);
		
		Button btn_start = findViewById(R.id.btn_start);
		Button btn_pref = findViewById(R.id.btn_pref);
		btn_start.setOnClickListener(this);
		btn_pref.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		popluatePrefs(getPrefs());
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_start:
				startThreadExe();
				break;
			case R.id.btn_pref:
				Intent p = new Intent(this, SettingActivity.class);
				startActivityForResult(p, PrefRequest);
				break;
		}
	}
	
	public void startThreadExe() {
		findViewById(R.id.statusContainer).setVisibility(View.VISIBLE);
		findViewById(R.id.titleS2).setVisibility(View.VISIBLE);
		
		prefsHolder holder = getPrefs();
		
		for (int i = 0; i < holder.num_tasks; i++) {
			MyThreadPoolManager.getInstance(holder.num_threads, holder.num_threads_max, holder.num_queue, this).getMythreadPool
					().execute(new MyTask(this));
		}
	}
	
	private prefsHolder getPrefs() {
		//get preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		Resources res = getResources();
		
		int tasks = Integer.parseInt(prefs.getString("mp_number_tasks", "20"));
		
		int threads = Integer.parseInt(prefs.getString("mp_number_threadsPool", "5"));
		
		int threads_max = Integer.parseInt(prefs.getString("mp_number_threadsPool_max", "100"));
		
		int queue = Integer.parseInt(prefs.getString("mp_number_queue", "20"));
		
		Log.d("DebugRealNow", tasks + " " + threads + " " + threads_max + " " + queue);
		return new prefsHolder(tasks, threads, threads_max, queue);
	}
	
	public void popluatePrefs(prefsHolder prefs) {
		String temp1 = getString(R.string.number_of_tasks) + prefs.num_tasks;
		String temp2 = getString(R.string.number_of_threads) + prefs.num_threads;
		String temp3 = getString(R.string.number_of_maximum_threads) + prefs.num_threads_max;
		String temp4 = getString(R.string.number_of_queses) + prefs.num_queue;
		
		num_tasks.setText(temp1);
		num_threads.setText(temp2);
		num_threads_max.setText(temp3);
		num_queue.setText(temp4);
	}
	
	@Override
	public void taskStarted() {
		num_tasks_started++;
	}
	
	@Override
	public void taskFinished() {
		num_tasks_completed++;
		String temp1 = getString(R.string.number_of_tasks_complete) + num_tasks_completed;
		num_tasks_completed_view.setText(temp1);
		Log.d(TAG, "Finished: " + num_tasks_completed);
	}
	
	@Override
	public void threadCount(int count) {
		String temp1 = getString(R.string.number_of_threads_active) + count;
		num_threads_active_view.setText(temp1);
	}
	
	static class prefsHolder {
		int num_tasks, num_threads, num_threads_max, num_queue;
		
		public prefsHolder(int num_tasks, int num_threads, int num_threads_max, int num_queue) {
			this.num_tasks = num_tasks;
			this.num_threads = num_threads;
			this.num_threads_max = num_threads_max;
			this.num_queue = num_queue;
		}
	}
}
