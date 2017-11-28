package com.yw.hw5_musicplayerservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	TextView SongTitle;
	MyMusicService mService;
	boolean mBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button startButton = (Button) findViewById(R.id.playButton);
		Button stopButton = (Button) findViewById(R.id.stopButton);
		Button pauseButton = (Button) findViewById(R.id.pauseButton);
		SongTitle = findViewById(R.id.songTitle);
		startButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);
		pauseButton.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Bind to LocalService
		Intent intent = new Intent(this, MyMusicService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(mConnection);
		mBound = false;
	}



	@Override
	public void onClick(View v) {

		Intent service = new Intent(MainActivity.this, MyMusicService.class);
		service.putExtra("TITLE", "ringtone");

		switch (v.getId()) {
			case R.id.playButton:
				SongTitle.setText("ringtone");
				if (!MyMusicService.IS_SERVICE_RUNNING) {
					service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
					MyMusicService.IS_SERVICE_RUNNING = true;
//					mService.start();
					startService(service);
				} else if (MyMusicService.IS_PAUSED) {
					service.setAction(Constants.ACTION.CONTINUE);
					MyMusicService.IS_PAUSED = false;
					startService(service);
				}
				break;
			case R.id.stopButton:
				if (MyMusicService.IS_SERVICE_RUNNING) {
					service.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
					MyMusicService.IS_SERVICE_RUNNING = false;
					startService(service);
				}

				break;
			case R.id.pauseButton:
				if (MyMusicService.IS_SERVICE_RUNNING) {
					service.setAction(Constants.ACTION.PAUSE_ACTION);
					MyMusicService.IS_PAUSED = true;
					startService(service);
				}
				break;
			default:
				break;
		}

	}

	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className,
									   IBinder service) {
			// We've bound to LocalService, cast the IBinder and get LocalService instance
			MyMusicService.LocalBinder binder = (MyMusicService.LocalBinder) service;
			mService = binder.getService();
			mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mBound = false;
		}
	};
}
