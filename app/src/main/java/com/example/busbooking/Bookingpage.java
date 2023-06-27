package com.example.busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Bookingpage extends AppCompatActivity {


    EditText ed_username,ed_phno,ed_pcount;
    TextView tv_showdate,tv_clickdate,tv_peramount;
    Spinner sp_busno,sp_fromplace,sp_toplace;
    Button bt_confirm;

    String [] busnumber={"","570","102","21G","29C","91","47D","515","570s","70","102S"};
    String [] fromplaces={"","T.Nager","Bharathi Nagar","Trustpuram school","Vadapalani bus stand","Koyambedu"};
    String [] toplaces={"","T.Nager","Bharathi Nagar","Trustpuram school","Vadapalani bus stand","Koyambedu"};

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingpage);


        ed_username=findViewById(R.id.bookname);
        ed_phno=findViewById(R.id.bookphone);
        ed_pcount=findViewById(R.id.passengercount);

        tv_clickdate=findViewById(R.id.clickdatemenu);
        tv_showdate=findViewById(R.id.showdatemenu);
        tv_peramount=findViewById(R.id.busamount);

        sp_busno=findViewById(R.id.busno);
        sp_fromplace=findViewById(R.id.fromplace);
        sp_toplace=findViewById(R.id.toplace);

        bt_confirm=findViewById(R.id.bookbtn);


        ArrayAdapter a1=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,busnumber);
        sp_busno.setAdapter(a1);

        ArrayAdapter a2=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,fromplaces);
        sp_fromplace.setAdapter(a2);

        ArrayAdapter a3=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,toplaces);
        sp_toplace.setAdapter(a3);


        tv_clickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Bookingpage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tv_showdate.setText(dayOfMonth + "/ " + (monthOfYear + 1) + "/ " + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        sp_busno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String gbusno=sp_busno.getSelectedItem().toString();

                if (gbusno.equals("570")){
                    tv_peramount.setText("30");
                }
                else if(gbusno.equals("102")){
                    tv_peramount.setText("20");
                }
                else if(gbusno.equals("21G")){
                    tv_peramount.setText("25");
                }
                else if(gbusno.equals("29C")){
                    tv_peramount.setText("23");
                }
                else if(gbusno.equals("91")){
                    tv_peramount.setText("28");
                }
                else if(gbusno.equals("47D")){
                    tv_peramount.setText("18");
                }
                else if(gbusno.equals("515")){
                    tv_peramount.setText("20");
                }
                else if(gbusno.equals("570s")){
                    tv_peramount.setText("25");
                }
                else if(gbusno.equals("70")){
                    tv_peramount.setText("26");
                }
                else if(gbusno.equals("102S")){
                    tv_peramount.setText("30");
                }
                else {

                        Toast.makeText(Bookingpage.this, "Please choose the bus", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=ed_username.getText().toString();
                String phno=ed_phno.getText().toString();
                String showdate=tv_showdate.getText().toString();
                String busnum=sp_busno.getSelectedItem().toString();
                String fromplace=sp_fromplace.getSelectedItem().toString();
                String toplace=sp_toplace.getSelectedItem().toString();
                String counts=ed_pcount.getText().toString();

                String amt=tv_peramount.getText().toString();
                String count=ed_pcount.getText().toString();


                int amts=Integer.parseInt(amt);
                int pcount=Integer.parseInt(count);


                int mul=amts * pcount;

                String muls=String.valueOf(mul);


                SharedPreferences sp=getSharedPreferences("koil",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();

                editor.putString("username",username);
                editor.putString("phno",phno);
                editor.putString("showdate",showdate);
                editor.putString("busnum",busnum);
                editor.putString("fromplace",fromplace);
                editor.putString("toplace",toplace);
                editor.putString("counts",counts);
                editor.putString("amt",amt);
                editor.putString("Total",muls);

                editor.commit();

                Intent in3=new Intent(Bookingpage.this,Paymount.class);
                startActivity(in3);
                finish();

            }
        });

    }
}