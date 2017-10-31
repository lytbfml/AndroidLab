package edu.iastate.gestures;

import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * This is a base class for handling swipes in the application.
 */
public class CustomGestureListener extends Activity implements OnGestureListener
{
	/*
	 * These variables store activity specific values.
	 */
	private GestureDetector gesture = null;
	private Class<?> leftActivity = null;
	private Class<?> rightActivity = null;
	
	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		if (gesture != null)
			return gesture.onTouchEvent(me);
		else
			return false;
	}
	
	@Override
	public boolean onDown(MotionEvent e)
	{
		//insert code here
		return false;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	                       float velocityY)
	{
		float x1, x2, y1, y2;
		x1 = e1.getX();
		x2 = e2.getX();
		y1 = e1.getY();
		y2 = e2.getY();
		
		boolean rightSwipe = (x1 - x2) > 0;
		if (rightSwipe) {
			Intent newI = new Intent(this, rightActivity);
			startActivity(newI);
			Toast.makeText(this, "Right Swipe", Toast.LENGTH_SHORT).show();
			
		} else {
			Intent newI = new Intent(this, leftActivity);
			startActivity(newI);
			Toast.makeText(this, "Left Swipe", Toast.LENGTH_SHORT).show();
		}
		
		return false;
	}
	
	@Override
	public void onLongPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	                        float distanceY)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onShowPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Sets the gesture detector for the activity
	 *
	 * @param gesture the gesture detector specific to the activity
	 */
	public void setGestureDetector(GestureDetector gesture)
	{
		this.gesture = gesture;
	}
	
	/**
	 * Sets the left and right activity classes which are swiped to
	 *
	 * @param leftActivity  The class for the left Activity
	 * @param rightActivity The class for the right Activity
	 */
	public void setLeftRight(Class<?> leftActivity, Class<?> rightActivity)
	{
		this.leftActivity = leftActivity;
		this.rightActivity = rightActivity;
	}
	
}
