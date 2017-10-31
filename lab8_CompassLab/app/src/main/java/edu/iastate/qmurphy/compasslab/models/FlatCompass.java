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

public class FlatCompass implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mMagField;
    private SensorUpdateCallback mCallback;

    public FlatCompass(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mCallback = callback;
    }

    public void start() {
        mSensorManager.registerListener(this, mMagField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation = 0.0f;
        orientation = (float) (Math.atan2(event.values[0], event.values[1]) * 180 / Math.PI);
        mCallback.update(orientation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
