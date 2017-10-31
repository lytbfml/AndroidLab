package com.example.swamy.geoquiz;

import android.graphics.Color;
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

public class QuizActivity extends AppCompatActivity {

    //android naming convention for member instance variables
    //demo import android.widget.Button;, try Alt+Enter

    private Button mYesButton;
    private Button mNoButton;
    private TextView mTextDisplay;
    private Button mNextButton;

    private int qIndex = 0;
    private int MaxQ = 3;

    private String questList[] = {"Capital of USA is Washington DC",
            "Capital of India is New Delhi", "Capital of Greece is olympia"};
    private String ansList[] = {"Yes",
            "Yes", "No"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        //restore saved state if we got killed earlier
        if(savedInstanceState != null)
        {
            //0 is the default value incase index key was not defined before
            qIndex = savedInstanceState.getInt("index",0);

        }

        mYesButton = (Button) findViewById(R.id.button);
        mNoButton = (Button) findViewById(R.id.button2);

        mTextDisplay = (TextView) findViewById(R.id.mytext);
        mTextDisplay.setText(questList[qIndex]);
        mTextDisplay.setTextColor(Color.BLUE);

        mYesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ("Yes".equals(ansList[qIndex])) {
                    Toast.makeText(QuizActivity.this, "You are correct !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "Incorrect !", Toast.LENGTH_SHORT).show();
                }

                mYesButton.setVisibility(View.INVISIBLE);
                mNoButton.setVisibility(View.INVISIBLE);
            }

        });

        mNoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ("No".equals(ansList[qIndex])) {
                    Toast.makeText(QuizActivity.this, "You are correct !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "Incorrect !", Toast.LENGTH_SHORT).show();
                }
                mYesButton.setVisibility(View.INVISIBLE);
                mNoButton.setVisibility(View.INVISIBLE);
            }

        });

        mNextButton = (Button) findViewById(R.id.button3);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                qIndex = (qIndex + 1) % MaxQ;
                mTextDisplay.setText(questList[qIndex]);
                mYesButton.setVisibility(View.VISIBLE);
                mNoButton.setVisibility(View.VISIBLE);
            }

        });


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        //android framework will do its share of the job
        super.onSaveInstanceState(savedInstanceState);

//save the current question index
        savedInstanceState.putInt("index",qIndex);

        Log.v("GeoActivity","qindex" + Integer.toString(qIndex));
    }

}
