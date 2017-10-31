package com.hw3.geoquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements QuestionFragment.OnListFragmentInteractionListener,
		ChoicesFragment.OnChoiceSelectListener {
	
	private static final String TAG = "GeoQuizList";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (findViewById(R.id.content1) != null) {
			Log.e(TAG, "not null");
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content1, QuestionFragment.newInstance(1), QuestionFragment.TAG)
					.commit();
		}
		
		ChoicesFragment fragment2 = (ChoicesFragment) getSupportFragmentManager().findFragmentByTag(ChoicesFragment.TAG);
		if(fragment2 != null)
		{
			Log.e(TAG, "fragment2 != null");
			getSupportFragmentManager().beginTransaction().remove(fragment2).commitAllowingStateLoss();
		}
		
		if (findViewById(R.id.content2) != null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content2, ChoicesFragment.newInstance(0), ChoicesFragment.TAG)
					.commit();
			Log.e(TAG, "content");
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
	}
	
	
	@Override
	public void onListFragmentInteraction(Questions.Question item) {
		ChoicesFragment choicesFrag = (ChoicesFragment) getSupportFragmentManager().findFragmentByTag(ChoicesFragment.TAG);
		if (choicesFrag != null) {
			choicesFrag.updateView(item.id);
			Log.e(TAG, "choicesFrag != null");
		} else {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content1, ChoicesFragment.newInstance(item.id))
					.addToBackStack(null)
					.commit();
		}
	}
	
	@Override
	public void onChoiceSelectListener(int pos, int ans) {
		Log.e(TAG, "POS: " + pos + ", ANS" + ans);
		boolean correct = Questions.ITEMS.get(pos).ans == ans;
		Toast.makeText(this, String.valueOf(correct), Toast.LENGTH_SHORT).show();
	}
}
