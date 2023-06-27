package com.example.busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class TicketchoosePage extends AppCompatActivity {

    TextView tv_bookicon,tv_logout;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketchoose_page);

        tv_bookicon=findViewById(R.id.bookicon);
        tv_logout=findViewById(R.id.logout);

        tv_bookicon.getText().toString();


        tv_bookicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in1=new Intent(TicketchoosePage.this,Bookingpage.class);
                startActivity(in1);
            }
        });

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
                SharedPreferences.Editor editor=loginshared.edit();
                editor.putString("Username","");
                editor.commit();

                progressDoalog = new ProgressDialog(TicketchoosePage.this);
                progressDoalog.setMax(100);
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.setMessage("Please wait....");
                progressDoalog.setMessage("Logged out your account....");

                progressDoalog.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        Intent in1=new Intent(TicketchoosePage.this,MainActivity.class);
                        startActivity(in1);
                        finish();

                    }
                }, 5000);



            }
        });
    }
}