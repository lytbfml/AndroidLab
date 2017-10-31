package lab4p1.yangxiao.cpre388.newactapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Yangxiao Wang on 9/21/2017.
 */

public class SecondActivity extends AppCompatActivity
{
	TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		mTextView = (TextView) findViewById(R.id.textV);
		String msg = getIntent().getStringExtra("msg");
		mTextView.setText(msg);
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		mTextView.setText(x + " , " + y);
		if(event.getActionMasked() == 0)
		{
			mTextView.setTextColor(Color.BLUE);
		}
		else if (event.getActionMasked() == 1) {
			mTextView.setTextColor(Color.RED);
		}
		else if (event.getActionMasked() == 2) {
			mTextView.setTextColor(Color.BLACK);
		}
		Log.d("Testx", event.getActionMasked()+"");
		return false;
	}
}
