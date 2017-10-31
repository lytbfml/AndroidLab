package edu.iastate.qmurphy.compasslab.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import edu.iastate.qmurphy.compasslab.R;
import edu.iastate.qmurphy.compasslab.interfaces.SensorUpdateCallback;
import edu.iastate.qmurphy.compasslab.models.BetterCompass;

public class BetterCompassActivity extends AppCompatActivity implements SensorUpdateCallback, View.OnClickListener {
    private BetterCompass mCompass;
    private ImageView mArrow;
    
    private float oldOr = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        mCompass = new BetterCompass(getApplicationContext(), this);

        mArrow = (ImageView) findViewById(R.id.image);
        mArrow.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.compass));

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setText("Flat Compass");
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setText("Tilt");
        btn2.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCompass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCompass.stop();
    }

    @Override
    public void update(float orientation) {
        if(oldOr == 0 || (oldOr - orientation) > 1)
        {
            mArrow.setRotation(orientation);
        }
        oldOr = orientation;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            startActivity(new Intent(this, FlatCompassActivity.class));
        }
        else if (v.getId() == R.id.btn2) {
            startActivity(new Intent(this, TiltActivity.class));
        }
    }
}

