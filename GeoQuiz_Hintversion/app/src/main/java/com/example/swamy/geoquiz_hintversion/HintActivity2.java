package com.example.swamy.geoquiz_hintversion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Yangxiao on 9/22/2017.
 */

public class HintActivity2 extends AppCompatActivity {
	private static final long TIME_OUT = 5000;
	private int hIndex = 0;
	private int MaxQ = 3;
	private TextView hintText;
	private int usefulness = 0;
	
	private Button useful;
	private Button notuseful;
	private int usefulN;
	
	private static int HINT_ACTIVITY = 2;
	
	private Handler handler = new Handler();
	private Runnable autoNext;
	
	private Button NextHint;
	
	private String hintList[] = {"Location of  Supreme Court",
			"One of Delhi city's 11 districts", "Temple of Olympian Zeus", "No Hint Found"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hint2);
		
		Log.d("GEO", "Hint2 starting");
		
		useful = (Button) findViewById(R.id.button5);
		notuseful = (Button) findViewById(R.id.button6);
		hintText = (TextView) findViewById(R.id.mytext) ;
		
		usefulN = getIntent().getIntExtra("USEFULNUM", 0);
		
		useful.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				usefulness = 1;
			}
			
		});
		
		notuseful.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				usefulness = 0;
			}
		});
		
		hIndex = getIntent().getIntExtra("QUEST_INDEX", MaxQ);
		hintText.setText(hintList[hIndex]);
		
		autoNext = new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(getApplicationContext(), HintActivity3.class);
				i.putExtra("QUEST_INDEX", hIndex);
				i.putExtra("USEFULNUM", usefulness + usefulN);
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
				Intent i = new Intent(getApplicationContext(), HintActivity3.class);
				i.putExtra("QUEST_INDEX", hIndex);
				i.putExtra("USEFULNUM", usefulness + usefulN);
				i.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
				startActivity(i);
				finish();
			}
		});
	}
	
	
	void sendResult(int hintNum) {
		Intent intent2Main = new Intent();
		intent2Main.putExtra("USEFULNESS", usefulN + usefulness);
		intent2Main.putExtra("HINTNUM", hintNum);
		setResult(RESULT_OK, intent2Main);
	}
	
	@Override
	public void onBackPressed() {
		handler.removeCallbacks(autoNext);
		sendResult(HINT_ACTIVITY);
		super.onBackPressed();
	}
	
	@Override
	protected void onDestroy() {
		Log.d("GEO", "Hint 2 destroy");
		super.onDestroy();
	}
}
