package com.example.busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent in=new Intent(MainActivity.this, Loginpage.class);
                startActivity(in);
            }
        })*/

        SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
        SharedPreferences.Editor editor=loginshared.edit();

        String splash=loginshared.getString("Username","");

        if(splash.equals("")){

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext() , Loginpage.class));
                    finish();
                }
            },3000);
        }
        else {
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext() , TicketchoosePage.class));
                    finish();
                }
            },3000);
        }
    }
}