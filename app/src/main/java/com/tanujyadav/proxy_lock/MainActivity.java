package com.tanujyadav.proxy_lock;

import android.app.ActivityManager;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.SystemClock.uptimeMillis;


    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        Button b,a;
        ComponentName mDeviceAdminSample;
        protected static final int REQUEST_ENABLE = 0;
        public static SharedPreferences ps;
        public static SharedPreferences.Editor pe;
        private static final int TIME_DELAY = 2000;
        private static long back_pressed;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            a = (Button) findViewById(R.id.a);
            b = (Button) findViewById(R.id.b);
            a.setOnClickListener(this);
            b.setOnClickListener(this);
            ps=getPreferences(MODE_PRIVATE);
            pe=ps.edit();

            if (ps.getString("status","n/a").equals("on"))
            {
                b.setVisibility(View.GONE);
                a.setVisibility(View.VISIBLE);
            }

            mDeviceAdminSample = new ComponentName(MainActivity.this,
                    Darclass.class);
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
            startActivityForResult(intent, REQUEST_ENABLE);


    }

        @Override
        public void onBackPressed() {

            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit!",
                        Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();
        }

        public void onClick(View src) {
            switch (src.getId()) {
                case R.id.b:
                    b.setVisibility(View.GONE);
                    a.setVisibility(View.VISIBLE);
                    pe.putString("status","on");
                    pe.commit();
                    startService(new Intent(this, MyService.class));
                    break;
                case R.id.a:
                    a.setVisibility(View.GONE);
                    b.setVisibility(View.VISIBLE);
                    pe.putString("status","off");
                    pe.commit();
                    stopService(new Intent(this, MyService.class));
                    break;
            }
        }
    }
