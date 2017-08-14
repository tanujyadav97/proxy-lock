package com.tanujyadav.proxy_lock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class setting extends AppCompatActivity {

    RadioButton yes,no;
    Spinner delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if(getSupportActionBar()!=null)
        getSupportActionBar().setTitle("Settings");

        yes=(RadioButton)findViewById(R.id.yes);
        no=(RadioButton)findViewById(R.id.no);
        delay=(Spinner)findViewById(R.id.type_list);

        if(MainActivity.ps.getString("blkland","n/a").equals("1")) {
            yes.setChecked(true);
            no.setChecked(false);
        }
        if(MainActivity.ps.getInt("delay",0)==0)
            delay.setSelection(0);
        else if(MainActivity.ps.getInt("delay",0)==300)
            delay.setSelection(1);
        else if(MainActivity.ps.getInt("delay",0)==500)
            delay.setSelection(2);
        else if(MainActivity.ps.getInt("delay",0)==800)
            delay.setSelection(3);
        else if(MainActivity.ps.getInt("delay",0)==1000)
            delay.setSelection(4);
        else if(MainActivity.ps.getInt("delay",0)==1500)
            delay.setSelection(5);
        else if(MainActivity.ps.getInt("delay",0)==2000)
            delay.setSelection(6);
        else if(MainActivity.ps.getInt("delay",0)==3000)
            delay.setSelection(7);

        delay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    MainActivity.pe.putInt("delay",0);
                }
                else if(position==1)
                {
                    MainActivity.pe.putInt("delay",300);
                }
                else if(position==2)
                {
                    MainActivity.pe.putInt("delay",500);
                }
                else if(position==3)
                {
                    MainActivity.pe.putInt("delay",800);
                }
                else if(position==4)
                {
                    MainActivity.pe.putInt("delay",1000);
                }
                else if(position==5)
                {
                    MainActivity.pe.putInt("delay",1500);
                }
                else if(position==6)
                {
                    MainActivity.pe.putInt("delay",2000);
                }
                else if(position==7)
                {
                    MainActivity.pe.putInt("delay",3000);
                }

                MainActivity.pe.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.pe.putString("blkland","1");
                no.setChecked(false);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.pe.putString("blkland","0");
                yes.setChecked(false);
            }
        });

    }
}
