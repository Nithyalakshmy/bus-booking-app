package com.example.busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registrationpage extends AppCompatActivity {

    EditText ed_regname,ed_regpass,ed_regphno;
    Spinner sp_gen;
    Button bt_create;

    String [] gender={"","Male","Female","Other"};

    SQLiteDatabase db;

    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        ed_regname=findViewById(R.id.regname);
        ed_regpass=findViewById(R.id.regpass);
        ed_regphno=findViewById(R.id.regphno);
        sp_gen=findViewById(R.id.genspin);

        bt_create=findViewById(R.id.regsubbtn);

        ArrayAdapter a4=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,gender);
        sp_gen.setAdapter(a4);



        db = openOrCreateDatabase ("Login",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Userlogin(Name TEXT,Password TEXT,Number TEXT,Gender TEXT);");
        db.close();

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=ed_regname.getText().toString();
                String pass=ed_regpass.getText().toString();
                String number=ed_regphno.getText().toString();
                String gender=sp_gen.getSelectedItem().toString();

                if ((name.equals("")) && (pass.equals("")) && (number.equals("")) && (gender.equals(""))){

                    Toast.makeText(Registrationpage.this, "Please fill all the field", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.isEmpty()){
                    try {
                        db = openOrCreateDatabase ("Login",MODE_PRIVATE,null);

                        Cursor c = db.rawQuery("select * from Userlogin where Password = '" + pass + "'", null);
                        if (c.moveToNext())
                        {

                            Toast.makeText(getApplicationContext(), "Password already exist!",Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            // Toast.makeText(getApplicationContext(), "No Data",Toast.LENGTH_SHORT).show();
                            db=openOrCreateDatabase("Login",MODE_PRIVATE,null);

                            db.execSQL("insert into Userlogin values('" + name + "','" + pass + "','" + number + "','" + gender + "');");
                            db.close();

                            progressDoalog = new ProgressDialog(Registrationpage.this);
                            progressDoalog.setMax(100);
                            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDoalog.setMessage("Please wait....");
                            progressDoalog.setMessage("Creating new account....");
                            progressDoalog.setMessage("Successfully Created....");

                            progressDoalog.show();
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub

                                    Intent in=new Intent(Registrationpage.this,Loginpage.class);
                                    startActivity(in);
                                    finish();

                                }
                            }, 5000);

                            //Toast.makeText(getApplicationContext(),"Successfully created",Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();

                        Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}