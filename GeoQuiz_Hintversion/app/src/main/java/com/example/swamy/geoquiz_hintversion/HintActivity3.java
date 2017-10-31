package com.example.swamy.geoquiz_hintversion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Yangxiao on 9/22/2017.
 */

public class HintActivity3 extends AppCompatActivity {
	private int hIndex = 0;
	private int MaxQ = 3;
	private int usefulness = 0;
	
	private static final long TIME_OUT = 5000;
	
	private Button useful;
	private Button notuseful;
	private int usefulN;
	
	private static int HINT_ACTIVITY = 3;
	
	private ImageView imageView;
	
	private Handler handler = new Handler();
	private Runnable autoNext;
	
	int res[] = {R.drawable.dc, R.drawable.in, R.drawable.gre};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hint3);
		
		useful = (Button) findViewById(R.id.button5);
		notuseful = (Button) findViewById(R.id.button6);
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
		
		imageView = (ImageView) findViewById(R.id.imageHint);
		imageView.setImageResource(res[hIndex]);
		
		autoNext = new Runnable() {
			@Override
			public void run() {
				onBackPressed();
			}
		};
		
		handler.postDelayed(autoNext, TIME_OUT);
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
}
