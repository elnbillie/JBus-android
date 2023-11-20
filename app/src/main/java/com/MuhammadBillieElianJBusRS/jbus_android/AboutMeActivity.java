package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;


public class AboutMeActivity extends AppCompatActivity {

    private TextView initial = null;
    private TextView username = null;
    private TextView email = null;
    private TextView balance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        initial = findViewById(R.id.initial);
        username  = findViewById(R.id.username);
        email = findViewById(R.id.email);
        balance  = findViewById(R.id.balance);

        initial.setText("T");
        username.setText("Tyler Durden");
        email.setText("Imdellusional@example.com");
        balance.setText("69420666");
    }

}