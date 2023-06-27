package com.example.busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Loginpage extends AppCompatActivity {

    EditText ed_logname,ed_lgopass;
    Button bt_loginbtn;
    TextView textView;

    ProgressDialog progressDoalog;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


        ed_logname=findViewById(R.id.loginname);
        ed_lgopass=findViewById(R.id.loginpass);
        textView=findViewById(R.id.createnew);
        bt_loginbtn=findViewById(R.id.logsubbtn);


        bt_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1=ed_logname.getText().toString();
                String s2=ed_lgopass.getText().toString();


                if ((s1.equals("")) && (s2.equals(""))){

                    Toast.makeText(Loginpage.this, "Please fill all the field", Toast.LENGTH_SHORT).show();

                }

                else{


                    try {
                        db = openOrCreateDatabase ("Login",MODE_PRIVATE,null);

                        Cursor c = db.rawQuery("select * from Userlogin where Name = '" + s1 + "' and Password = '" + s2 + "'", null);
                        if (c.moveToNext())
                        {

                            SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
                            SharedPreferences.Editor editor=loginshared.edit();
                            editor.putString("Username",s1);
                            editor.commit();

                            //Toast.makeText(getApplicationContext(), "Successs",Toast.LENGTH_SHORT).show();
                            progressDoalog = new ProgressDialog(Loginpage.this);
                            progressDoalog.setMax(100);
                            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDoalog.setMessage("Please wait....");

                            progressDoalog.show();
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub

                                    Intent ilogin=new Intent(Loginpage.this,TicketchoosePage.class);
                                    startActivity(ilogin);
                                    finish();

                                }
                            }, 3000);

                            //Toast.makeText(getApplicationContext(),"Successfully Login" +k1+""+k2+"",Toast.LENGTH_LONG).show();


                        }

                        else
                        {

                            //Toast.makeText(getApplicationContext(), "No Data",Toast.LENGTH_SHORT).show();
                            View v = findViewById(R.id.logsubbtn);
                            String message = "Incorrect Password or Username";
                            int duration = Snackbar.LENGTH_SHORT;

                            showSnackbar(v, message, duration);
                        }

                    } catch (Exception e) {
                    }
                }

            }

            public void showSnackbar(View v, String message, int duration) {
                Snackbar.make(v, message, duration).show();
            }


        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(Loginpage.this,Registrationpage.class);
                startActivity(in);
            }
        });

    }
}