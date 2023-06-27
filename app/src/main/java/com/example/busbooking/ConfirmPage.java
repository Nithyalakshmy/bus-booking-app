package com.example.busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmPage extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    
    Button bt_confirm;
    String username,phno,showdate,busnum,fromplace,toplace,counts,amt,Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_page);

        t1=findViewById(R.id.tdy1);
        t2=findViewById(R.id.tdy2);
        t3=findViewById(R.id.tdy3);
        t4=findViewById(R.id.tdy4);
        t5=findViewById(R.id.tdy5);
        t6=findViewById(R.id.tdy6);
        t7=findViewById(R.id.tdy7);
        t8=findViewById(R.id.tdy8);
        t9=findViewById(R.id.tdy9);
        
        bt_confirm=findViewById(R.id.confirm);

        SharedPreferences sp=getSharedPreferences("koil",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        username=sp.getString("username","");
        phno=sp.getString("phno","");
        showdate=sp.getString("showdate","");
        busnum=sp.getString("busnum","");
        fromplace=sp.getString("fromplace","");
        toplace=sp.getString("toplace","");
        counts=sp.getString("counts","");
        amt=sp.getString("amt","");
        Total=sp.getString("Total","");

        t1.setText(username);
        t2.setText(phno);
        t3.setText(showdate);
        t4.setText(busnum);
        t5.setText(fromplace);
        t6.setText(toplace);
        t7.setText(counts);
        t8.setText(amt);
        t9.setText(Total);


        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(phno,null,"Name :"+username+"\n"+
                        "Date :"+showdate+"\n"+
                        "Bus No :"+busnum+"\n"
                        +"Route :"+fromplace+" to " +toplace+"\n"+
                        "Count :"+counts+"\n"+
                        "Total Amount :"+Total,null,null);


                Toast.makeText(ConfirmPage.this, "Ticket Booked...", Toast.LENGTH_SHORT).show();

                Intent in=new Intent(ConfirmPage.this,TicketchoosePage.class);
                startActivity(in);
                finish();
            }
        });
    }
}