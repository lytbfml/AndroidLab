package com.example.swamy.geoquiz_hintversion;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HintActivity extends AppCompatActivity {
	
	private int hIndex = 0;
	private int MaxQ = 3;
	private TextView hintText;
	
	private int usefulness = 0;
	private static final long TIME_OUT = 5000;
	private static int HINT_ACTIVITY = 1;
	
	private Button useful;
	private Button notuseful;
	
	private Button NextHint;
	
	private Handler handler = new Handler();
	private Runnable autoNext;
	
	private String hintList[] = {"Location of White House",
			"Northern part of India", "Greek goddess", "No Hint Found"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hint);
		
		useful = (Button) findViewById(R.id.button5);
		useful.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				usefulness = 1;
			}
			
		});
		
		notuseful = (Button) findViewById(R.id.button6);
		notuseful.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				usefulness = 0;
			}
			
		});
		
		hintText = (TextView) findViewById(R.id.mytext);
		hIndex = getIntent().getIntExtra("QUEST_INDEX", MaxQ);
		hintText.setText(hintList[hIndex]);
		
		autoNext = new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(getApplicationContext(), HintActivity2.class);
				i.putExtra("QUEST_INDEX", hIndex);
				i.putExtra("USEFULNUM", usefulness);
				i.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
				startActivity(i);
				finish();
			}
		};
		handler.postDelayed(autoNext, TIME_OUT);
		
		NextHint = (Button) findViewById(R.id.nextHint);
		NextHint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.removeCallbacks(autoNext);
				Intent i = new Intent(getApplicationContext(), HintActivity2.class);
				i.putExtra("QUEST_INDEX", hIndex);
				i.putExtra("USEFULNUM", usefulness);
				i.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
				startActivity(i);
				finish();
			}
		});
		
		
	}
	
	@Override
	protected void onDestroy() {
		Log.d("GEO", "Hint 1 destroy");
		super.onDestroy();
	}
	
	
	void sendResult(int hintNum) {
		Intent intent2Main = new Intent();
		intent2Main.putExtra("USEFULNESS", usefulness);
		intent2Main.putExtra("HINTNUM", hintNum);
		setResult(RESULT_OK, intent2Main);
	}
	
	@Override
	public void onBackPressed() {
		handler.removeCallbacks(autoNext);
		sendResult(HINT_ACTIVITY);
		super.onBackPressed();
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//
//		if (resultCode == RESULT_OK) {
//			HINT_ACTIVITY = data.getIntExtra("HINTNUM", 1);
//			if (requestCode == 1) {
//				onBackPressed();
//			}
//		} else {
//			//handle this situation
//		}
//	}
}
