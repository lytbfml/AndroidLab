package com.yangxiao.threadpoolexecutortests.thread;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.yangxiao.threadpoolexecutortests.R;

public class ThreadExeActivity extends AppCompatActivity {
	
	private static final String TAG = ThreadExeActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		Fragment fragment = getSupportFragmentManager().findFragmentByTag(ThreadExeFragment.TAG);
		if(fragment == null)
		{
			fragment = new ThreadExeFragment();
			fragment.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment, ThreadExeFragment.TAG).commit();
		}
	}
	
	private void setupActionBar() {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			// Show the Up button in the action bar.
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
}
