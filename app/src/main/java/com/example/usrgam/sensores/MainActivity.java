package com.example.usrgam.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor acelerometro;

    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         txt = (TextView) findViewById(R.id.tdatos);
      sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
     sensorManager.getSensorList(Sensor.TYPE_ALL);
       txt.setText("");
        List<Sensor> sensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
     /*  for (int i = 0; i < sensores.size(); i++) {
           Sensor s = sensores.get(i);
          txt.append(s.getName() + "" + s.getVendor() + "\n");
        }*/
       acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x,y,z;
        x=event.values[0];
        y=event.values[1];
       z=event.values[2];
        txt.setText(x+" "+" "+y+" "+z);
       // txt.setText(x+"");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
