package com.example.swamy.geoquiz;

import android.graphics.Color;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Modified GeoQuiz
//Update Sept 1 - included landscape layout for rotated view
//Update Sept 1 - Fixed logic to handle rotations using onSaveInstance Bundle
//author - swamy d ponpandi
//date - aug 25 2017
//class - cpre 388
//demo activity, buttons, textview, attributes, listeners, toast

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
	
	private static final String TAG = "GeoQuizAct";
	
	
	private Button button_option[] = new Button[5];
	private Button button_option2;
	private Button button_option3;
	private Button button_option4;
	private Button button_option5;
	private TextView mTextDisplay;
	
	private Button mNextButton;
	
	private int qIndex = 0;
	private int MaxQ = 7;
	
	private static final String questList[] = {
			"Capital of UK is ",
			"Capital of USA is ",
			"Capital of France is ",
			"Capital of Italy is ",
			"Capital of Japan is ",
			"Capital of Germany is ",
			"Capital of China is "};
	
	private static final String choiceList[][] = {
			{"London", "Cambridge", "Manchester", "Oxford"},
			{"New York", "Chicago", "Washington DC", "San francisco"},
			{"Lyon", "Paris", "Saint-Paul", "Reims"},
			{"Milan", "Florence", "Venice", "Naples", "Rome"},
			{"Nagoya", "Osaka", "Saitama", "Tokyo", "Kyoto"},
			{"Munich", "Berlin", "Cologne", "Frankfurt am Main", "Stuttgart"},
			{"Beijing", "Chongqing", "Shanghai", "Hongkong", "Guangzhou"}
	};
	
	private static final int ansList[] = {0, 2, 1, 4, 3, 1, 0};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		//restore saved state if we got killed earlier
		if (savedInstanceState != null) {
			Log.d(TAG, "?????" + qIndex);
			//0 is the default value incase index key was not defined before
			qIndex = savedInstanceState.getInt("index", 0);
		} else {
			Log.d(TAG, "NONONO" + qIndex);
		}
			
		button_option[0] = (Button) findViewById(R.id.button_option1);
		button_option[1] = (Button) findViewById(R.id.button_option2);
		button_option[2] = (Button) findViewById(R.id.button_option3);
		button_option[3] = (Button) findViewById(R.id.button_option4);
		button_option[4] = (Button) findViewById(R.id.button_option5);
		for(int i = 0; i < 5; i++)
		{
			button_option[i].setOnClickListener(this);
		}
		
		mNextButton = (Button) findViewById(R.id.button3);
		mNextButton.setOnClickListener(this);
		
		mTextDisplay = (TextView) findViewById(R.id.mytext);
		mTextDisplay.setTextColor(Color.BLUE);
		setOptions();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		//android framework will do its share of the job
		super.onSaveInstanceState(savedInstanceState);
		
		//save the current question index
		savedInstanceState.putInt("index", qIndex);
		
		Log.v(TAG, "Saved qindex: " + Integer.toString(qIndex));
	}
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
			case R.id.button_option1: {
				if (0 == (ansList[qIndex])) {
					makeSnack("You are correct !");
				} else {
					makeSnack("Incorrect !");
				}
				setOptionsInvisible();
				break;
			}
			case R.id.button_option2: {
				if (1 == (ansList[qIndex])) {
					makeSnack("You are correct !");
				} else {
					makeSnack("Incorrect !");
				}
				setOptionsInvisible();
				break;
			}
			case R.id.button_option3: {
				if (2 == (ansList[qIndex])) {
					makeSnack("You are correct !");
				} else {
					makeSnack("Incorrect !");
				}
				setOptionsInvisible();
				break;
			}
			case R.id.button_option4: {
				if (3 == (ansList[qIndex])) {
					makeSnack("You are correct !");
				} else {
					makeSnack("Incorrect !");
				}
				setOptionsInvisible();
				break;
			}
			case R.id.button_option5: {
				if (4 == (ansList[qIndex])) {
					makeSnack("You are correct !");
				} else {
					makeSnack("Incorrect !");
				}
				setOptionsInvisible();
				break;
			}
			case R.id.button3: {
				qIndex = (qIndex + 1) % MaxQ;
				setOptions();
			}
		}
	}
	
	private void makeSnack(String msg) {
		Snackbar mySnackbar = Snackbar.make(findViewById(R.id.layoutR),
				msg, Snackbar.LENGTH_LONG);
		if (msg.toLowerCase().contains("incorrect")) {
			mySnackbar.setAction(R.string.try_again, new MyUndoListener(qIndex));
		}
		mySnackbar.show();
	}
	
	public class MyUndoListener implements View.OnClickListener {
		int index;
		
		public MyUndoListener(int i) {
			index = i;
		}
		
		@Override
		public void onClick(View v) {
			qIndex = index;
			setOptions();
		}
	}
	
	private void setOptionsInvisible()
	{
		for(int i = 0; i < 5; i++)
		{
			button_option[i].setVisibility(View.GONE);
		}
	}
	
	private void setOptions()
	{
		mTextDisplay.setText(questList[qIndex]);
		int num = choiceList[qIndex].length;
		for(int i = 0; i < num; i++)
		{
			button_option[i].setText(choiceList[qIndex][i]);
			button_option[i].setVisibility(View.VISIBLE);
		}
	}
}
