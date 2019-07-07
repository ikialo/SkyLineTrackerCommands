package com.skyline.app.skylinetrackercommands;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int SEND_PERMISSION_REQUEST_CODE =720 ;
    EditText phone, pass;
    Button pos, stopElec, startelec, listContact;
    Switch aSwitch;
    String carName, phoneNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        if (checkPermission(Manifest.permission.SEND_SMS)){
            Toast.makeText(MainActivity.this, "You have Permission to send Messages", Toast.LENGTH_LONG).show();
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this, new String [] {Manifest.permission.SEND_SMS}, SEND_PERMISSION_REQUEST_CODE);
        }

        aSwitch = findViewById(R.id.switchs);
        phone = findViewById(R.id.phNum);
        pass = findViewById(R.id.pass);
        pos = findViewById(R.id.smsLink);
        startelec = findViewById(R.id.startElec);
        stopElec = findViewById(R.id.stopElec);
        listContact = findViewById(R.id.listNumbers);


        phoneNum = getIntent().getStringExtra("CarNumber");
        carName = getIntent().getStringExtra("CarName");

        if (phoneNum!= null){
            phone.setText(phoneNum);
        }
        if (carName != null){

            TextView carNameDisplay = findViewById(R.id.carNameMain);

            carNameDisplay.setText(carName);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if ( aSwitch.isChecked()){

                    Toast.makeText(MainActivity.this, "All Commands On", Toast.LENGTH_SHORT).show();

                    startelec.setEnabled(true);
                    stopElec.setEnabled(true);

                }
                else {
                    Toast.makeText(MainActivity.this, "Start/Stop Commands Off", Toast.LENGTH_SHORT).show();


                    startelec.setEnabled(false);
                    stopElec.setEnabled(false);

                }


            }
        });

        listContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListNumbersActivity.class));
            }
        });




//7198 8554



        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!phone.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {

                    String ph = "+675" + phone.getText().toString();
                    Log.d("phoneNumber", "onDataChange: " + ph);


                    String words = "#smslink#" + pass.getText().toString() + "#";// checkBox1a.getText().toString()+"  vs  "+checkBox1b.getText().toString();

                    SmsManager smsManager = SmsManager.getDefault();

                    smsManager.sendTextMessage(ph, null, words, null, null);

                    Intent intent = new Intent("my.action.string");
                    intent.putExtra("extra", ph); // phoneNo is the sent Number
                    sendBroadcast(intent);


                    Toast.makeText(MainActivity.this, "Message has been sent", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Please Enter a Phone number or Password", Toast.LENGTH_LONG).show();
                }


            }
        });


        startelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!phone.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                String ph = "+675"+phone.getText().toString();
                Log.d("phoneNumber", "onDataChange: " +ph);


                String words = "#supplyelec#"+pass.getText().toString()+"#";// checkBox1a.getText().toString()+"  vs  "+checkBox1b.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();

                smsManager.sendTextMessage(ph, null, words, null,null);
                    Toast.makeText(MainActivity.this, "Message has been sent", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Please Enter a Phone number or Password", Toast.LENGTH_LONG).show();
                }


            }
        });
        stopElec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!phone.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {

                    String ph = "+675"+phone.getText().toString();
                Log.d("phoneNumber", "onDataChange: " +ph);


                String words = "#stopelec#"+pass.getText().toString()+"#";// checkBox1a.getText().toString()+"  vs  "+checkBox1b.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();

                smsManager.sendTextMessage(ph, null, words, null,null);

                Toast.makeText(MainActivity.this, "Message has been sent", Toast.LENGTH_LONG).show();
            }
                else {
                Toast.makeText(MainActivity.this, "Please Enter a Phone number or Password", Toast.LENGTH_LONG).show();
            }



        }
        });


    }

    private boolean checkPermission(String permission) {

        int checkPermission = ContextCompat.checkSelfPermission(MainActivity.this, permission);

        return  checkPermission == PackageManager.PERMISSION_GRANTED;
    }
}
