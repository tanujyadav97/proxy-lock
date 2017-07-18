package com.tanujyadav.proxy_lock;


import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.Toast;

import static android.os.SystemClock.uptimeMillis;



public class MyService extends Service implements SensorEventListener {
    Sensor proxSensor;
    SensorManager sm;
    long a;
    DevicePolicyManager mDPM;



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
       // Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        proxSensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);

        mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] == 0) {
         //   Toast.makeText(this, "near", Toast.LENGTH_LONG).show();
            a = uptimeMillis();
        } else {

            if (uptimeMillis() - a < 1000) {
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                boolean f=false;

                if (Build.VERSION.SDK_INT >= 20) {
                    if (pm.isInteractive()) {
                        f=true;
                    }
                }
                else if(Build.VERSION.SDK_INT < 20){
                    if(pm.isScreenOn()){
                        f=true;
                    }
                }

                if(f)
                {
                    mDPM.lockNow();
                }

                else
                {
                    PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                            | PowerManager.ACQUIRE_CAUSES_WAKEUP, "CHESS");
                    wl.acquire();
                    try {
                        Thread.sleep(1000); // 30 seconds
                    } catch (Exception e) {
                    } finally {
                        wl.release();
                    }
                }


                //mDPM.lockNow();

            }
        }
    }
    @Override
    public void onDestroy() {
         sm.unregisterListener(this);
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
    }

}
