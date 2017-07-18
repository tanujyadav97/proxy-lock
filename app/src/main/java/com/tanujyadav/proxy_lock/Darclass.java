package com.tanujyadav.proxy_lock;

import android.app.admin.DeviceAdminReceiver;

/**
 * Created by 15121 on 4/8/2017.
 */
public class Darclass extends DeviceAdminReceiver{
}

/*
package com.tanujyadav.proxy_lock;

import android.app.ActivityManager;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.SystemClock.uptimeMillis;


    public class MainActivity extends AppCompatActivity implements SensorEventListener {
        private SensorManager mSensorManager;
        private Sensor mSensor;
        TextView iv;
        long a;
        DevicePolicyManager mDPM;
        ComponentName mDeviceAdminSample;
        protected static final int REQUEST_ENABLE = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            iv = (TextView) findViewById(R.id.text);


            mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
            mDeviceAdminSample = new ComponentName(MainActivity.this,
                    Darclass.class);

        }

        protected void onResume() {
            super.onResume();
            mSensorManager.registerListener(this, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.values[0] == 0) {
                iv.setText("Near");
                a = uptimeMillis();
            } else {
                iv.setText("Far");
                if (uptimeMillis() - a < 1000) {
                    Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
                    if (!mDPM.isAdminActive(mDeviceAdminSample)) {

                        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
                        startActivityForResult(intent, REQUEST_ENABLE);
                    } else {
                        mDPM.lockNow();
                    }
                }
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (REQUEST_ENABLE == requestCode) {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

 */