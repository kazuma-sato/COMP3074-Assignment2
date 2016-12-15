package com.comp3074.kazumasato.assignment2;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentActivityFragment extends Fragment {

    Context context;
    SensorManager manager;
    Sensor sensor;
    AccelerometerListener listener;
    TextView xTextView,yTextView,zTextView;
    int lastUpdate = 0;
    float x,y,z, lastX,lastY,lastZ;
    private static final int SHAKE_THRESHOLD = 800;


    public FragmentActivityFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        xTextView = (TextView) view.findViewById(R.id.xTextView);
        yTextView = (TextView) view.findViewById(R.id.yTextView);
        zTextView = (TextView) view.findViewById(R.id.zTextView);
        lastX = 0;
        lastY = 0;
        lastZ = 0;
        context = getActivity();
        manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new AccelerometerListener();
        manager.registerListener((SensorEventListener) listener,
                sensor, SensorManager.SENSOR_DELAY_GAME);
    }
    public class AccelerometerListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                int curTime = (int) System.currentTimeMillis();
                // only allow one update every 100ms.
                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[1];

                    float speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {
                        Intent openShake = new Intent("com.comp3074.kazumasato.assignment2.SHAKE");
                        startActivity(openShake);
                    } else {
                        xTextView.setText(Float.toString(x));
                        yTextView.setText(Float.toString(y));
                        zTextView.setText(Float.toString(z));
                    }
                    lastX = x;
                    lastY = y;
                    lastZ = z;
                    return;
                }
            }
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy){

        }
    }
}
