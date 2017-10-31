package yangxiao.cpre388.a388lab1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
	private String[] messages;
	private Random random;
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.button1);
		btn.setTextColor(Color.parseColor("white"));
		
		random = new Random();
		messages = new String[10];
		messages[0] = "Hello Dave!";
		messages[1] = "These are not the Droids you're looking for.";
		messages[2] = "All your Androids are belong to us!";
		messages[3] = "Thank you! \nBut our Android is in another castle.";
		messages[4] = "It's a Me! \nAndroid!";
		messages[5] = "Would you kindly press the button again?";
		messages[6] = "It's dangerous to go alone, take this. \nThe screen doubles as a " +
				"flashlight!";
		messages[7] = "Hey! Look! Listen! \nPress the button again!";
		messages[8] = "Fus Ro Droid! \nunrelenting Android";
		messages[9] = "The apple is a lie!";
		
	}
	
	
	public void changeText(View view)
	{
		TextView text = (TextView) findViewById(R.id.textview1);
		text.setText(messages[random.nextInt(10)]);
		int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
		text.setTextColor(color);
	}
}
