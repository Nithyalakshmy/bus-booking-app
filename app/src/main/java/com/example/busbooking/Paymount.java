package com.example.busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Paymount extends AppCompatActivity {
    EditText pay_ed,pay_ed1,pay_ed2,pay_ed3;
    TextView pay_tx,pay_tx1,tv_showamt;
    Button pay_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymount);

        pay_ed=findViewById(R.id.pay1);
        pay_ed1=findViewById(R.id.pay2);
        pay_ed2=findViewById(R.id.pay3);
        pay_ed3=findViewById(R.id.pay4);

        pay_tx=findViewById(R.id.paytx);
        pay_tx1=findViewById(R.id.paytx1);
        pay_bt=findViewById(R.id.paybt);
        tv_showamt=findViewById(R.id.showtotalamt);



        SharedPreferences sp=getSharedPreferences("koil",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        String user=sp.getString("username","");
        String paymount1=sp.getString("Total","");
        String dated=sp.getString("showdate","");
        String busnn=sp.getString("busnum","");
        String route=sp.getString("fromplace","");
        String po=sp.getString("toplace","");
        String cnt=sp.getString("counts","");
        String nmu=sp.getString("phno","");

        tv_showamt.setText(paymount1);

        pay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ed=pay_ed3.getText().toString();
                String ed1=pay_ed2.getText().toString();


                if (paymount1.equals(ed)){

                    Intent intent9=new Intent(Paymount.this,ConfirmPage.class);
                    startActivity(intent9);
                    finish();

                }
                else
                {
                    Toast.makeText(Paymount.this, "PLEASE ENTER THE VALID AMOUNT", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}