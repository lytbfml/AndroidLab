package com.example.swamy.geoquiz_hintversion;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//GeoQuiz Hint version
//update - add a show hint button
//update - show toast indicating usefulness of hint
//author - swamy d ponpandi
//date - sept 6 2017
//class - cpre 388
//demo startactivity, intents

public class MainActivity extends AppCompatActivity {
	
	private static final String TAG = "GeoQuizHint";
	
	private Button mYesButton;
	private Button mNoButton;
	
	private TextView mTextDisplay;
	private TextView mHintNumText;
	private TextView mHintNumUseful;
	
	private int[] mHintUsed = new int[3];
	
	private Button mNextButton;
	private Button mHintButton;
	
	private int qIndex = 0;
	private int MaxQ = 3;
	private int usefulness[] = new int[3];
	
	private String questList[] = {"Capital of USA is Washington DC",
			"Capital of India is New Delhi", "Capital of Greece is olympia"};
	private String ansList[] = {"Yes",
			"Yes", "No"};
	
	private static int HINT_ACTIVITY = 0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		//restore saved state if we got killed earlier
		if (savedInstanceState != null) {
			//0 is the default value incase index key was not defined before
			qIndex = savedInstanceState.getInt("index", 0);
			
		}
		mHintNumUseful = (TextView) findViewById(R.id.hintUsedf);
		mHintNumText = (TextView) findViewById(R.id.hintUsed);
		
		mYesButton = (Button) findViewById(R.id.button);
		mNoButton = (Button) findViewById(R.id.button2);
		mHintButton = (Button) findViewById(R.id.button4);
		
		mTextDisplay = (TextView) findViewById(R.id.mytext);
		mTextDisplay.setText(questList[qIndex]);
		mTextDisplay.setTextColor(Color.BLUE);
		
		mYesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if ("Yes".equals(ansList[qIndex])) {
					Toast.makeText(MainActivity.this, "You are correct !", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "Incorrect !", Toast.LENGTH_SHORT).show();
				}
				
				mYesButton.setVisibility(View.INVISIBLE);
				mNoButton.setVisibility(View.INVISIBLE);
			}
			
		});
		
		mNoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if ("No".equals(ansList[qIndex])) {
					Toast.makeText(MainActivity.this, "You are correct !", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "Incorrect !", Toast.LENGTH_SHORT).show();
				}
				mYesButton.setVisibility(View.INVISIBLE);
				mNoButton.setVisibility(View.INVISIBLE);
			}
			
		});
		
		//next button listner
		mNextButton = (Button) findViewById(R.id.button3);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				qIndex = (qIndex + 1) % MaxQ;
				HINT_ACTIVITY = qIndex;
				mTextDisplay.setText(questList[qIndex]);
				mYesButton.setVisibility(View.VISIBLE);
				mNoButton.setVisibility(View.VISIBLE);
			}
		});
		
		
		//hint button listner
		mHintButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, HintActivity.class);
				i.putExtra("QUEST_INDEX", qIndex);
				i.putExtra("HINTNUM", mHintUsed);
				startActivityForResult(i, HINT_ACTIVITY);
			}
			
		});
		
		updateHintUsed();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("index", qIndex);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			//			usefulness = data.getIntExtra("USEFULNESS", 0);
			int dataReturn = data.getIntExtra("HINTNUM", 0);
			int useful = data.getIntExtra("USEFULNESS", 0);
			if (requestCode == 0) {
				mHintUsed[0] = mHintUsed[0] < dataReturn ? dataReturn : mHintUsed[0];
				usefulness[0] = useful;
				updateHintUsed();
			} else if (requestCode == 1) {
				mHintUsed[1] = mHintUsed[1] < dataReturn ? dataReturn : mHintUsed[1];
				usefulness[1] = useful;
				updateHintUsed();
			} else if (requestCode == 2) {
				mHintUsed[2] = mHintUsed[2] < dataReturn ? dataReturn : mHintUsed[2];
				usefulness[2] = useful;
				updateHintUsed();
			}
			
		} else {
			//handle this situation
		}
	}
	
	private void updateHintUsed() {
		int count = 0;
		for (int i : mHintUsed) {
			count += i;
		}
		String hintused = "Hint used: " + count;
		mHintNumText.setText(hintused);
		
		int usef = 0;
		for (int i : usefulness) {
			usef += i;
		}
		String usefn = "Useful: " + usef + "/" + count;
		mHintNumUseful.setText(usefn);
	}
}
