package lab4p1.yangxiao.cpre388.newactapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
	EditText mEditText1;
	Button mButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mEditText1 = (EditText) findViewById(R.id.editText);
		mButton = (Button) findViewById(R.id.button);
		
		mButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				newClass(mEditText1.getText().toString());
			}
		});
		
	}
	
	private void newClass(String msg)
	{
		Intent i = new Intent(this, SecondActivity.class);
		i.putExtra("msg", msg);
		
		startActivity(i);
	}
	
}
