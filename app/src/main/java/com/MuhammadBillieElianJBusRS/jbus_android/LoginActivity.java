package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView Daftar = null;
    private Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Daftar = findViewById(R.id.Daftar);
        button = findViewById(R.id.button);

        Daftar.setOnClickListener(v->{
            moveActivity(this, RegisterActivity.class);
        });

        button.setOnClickListener(v->
                moveActivity(this, MainActivity.class));

        getSupportActionBar().hide();

    }
    private void moveActivity(Context ctx, Class<?> cls ){
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    private void viewToast(Context ctx, String message){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

}