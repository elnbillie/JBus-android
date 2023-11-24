package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MuhammadBillieElianJBusRS.jbus_android.model.Account;
import com.MuhammadBillieElianJBusRS.jbus_android.model.BaseResponse;
import com.MuhammadBillieElianJBusRS.jbus_android.request.BaseApiService;
import com.MuhammadBillieElianJBusRS.jbus_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private TextView Daftar = null;
    private BaseApiService mApiService;
    private Context mContext;
    private EditText email, password;
    private Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Daftar = findViewById(R.id.Daftar);
        button = findViewById(R.id.button);
        mContext = this;
        mApiService = UtilsApi.getApiService();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Daftar.setOnClickListener(v->{
            moveActivity(this, RegisterActivity.class);
        });

        button.setOnClickListener(v->{
            handleLogin();
        });

    }
    private void moveActivity(Context ctx, Class<?> cls ){
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    private void viewToast(Context ctx, String message){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    protected void handleLogin(){
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();
        if (emailS.isEmpty() || passwordS.isEmpty()) {
            Toast.makeText(mContext, "Field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mApiService.login(emailS, passwordS).enqueue(new Callback<BaseResponse<Account>>() {
            public void onResponse(Call<BaseResponse< Account >> call, Response<BaseResponse<Account>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Application error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                BaseResponse<Account> res = response.body();
                if (res.success) {
                    MainActivity.loggedAccount = res.payload;
                    moveActivity(LoginActivity.this, MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Account>> call, Throwable t) {
                System.out.println("On Failure");
                Toast.makeText(mContext, "Email/Password anda salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}