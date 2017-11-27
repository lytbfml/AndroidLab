package com.yw.hw5_musicplayerservice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	TextView SongTitle;

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
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.playButton:
				SongTitle.setText("ringtone");
				MyMusicService.startActionStart(this, new String[]{"1"}, "2");
				break;
			case R.id.stopButton:
				MyMusicService.startActionStop(this, "1", "2");
				break;
			case R.id.pauseButton:
				MyMusicService.pauseSong(this, "1", "2");
				break;
			default:
				break;
		}
	}
}
