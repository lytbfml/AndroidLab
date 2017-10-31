package edu.iastate.qmurphy.compasslab.models;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import edu.iastate.qmurphy.compasslab.interfaces.SensorUpdateCallback;

/**
 * Created by Quinn on 10/23/2016.
 */

public class BetterCompass implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mMagField;
	private Sensor mAcc;
	private SensorUpdateCallback mCallback;
	
	private float[] mAccelerometerReading = new float[3];
	private float[] mMagnetometerReading = new float[3];
	
	public BetterCompass(Context context, SensorUpdateCallback callback) {
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mCallback = callback;
	}
	
	public void start() {
		mSensorManager.registerListener(this, mMagField, SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void stop() {
		mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
			System.arraycopy(event.values, 0, mMagnetometerReading, 0, event.values.length);
		} else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			System.arraycopy(event.values, 0, mAccelerometerReading, 0, event.values.length);
		}
		
		float orientation = 0.0f;
		float[] mRotationMatrix = new float[9];
		float[] mOrientationAngles = new float[3];
		SensorManager.getRotationMatrix(mRotationMatrix, null, mAccelerometerReading, mMagnetometerReading);
		SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);
		orientation = (float) (-mOrientationAngles[0] * 180 / Math.PI);
		
		mCallback.update(orientation);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do nothing
	}
}
