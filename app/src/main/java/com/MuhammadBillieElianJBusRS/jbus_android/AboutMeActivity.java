package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.MuhammadBillieElianJBusRS.jbus_android.request.UtilsApi;
import com.MuhammadBillieElianJBusRS.jbus_android.request.BaseApiService;
import com.MuhammadBillieElianJBusRS.jbus_android.model.Account;
import com.MuhammadBillieElianJBusRS.jbus_android.model.BaseResponse;



public class AboutMeActivity extends AppCompatActivity {
    private Context mContext;
    private BaseApiService mApiService;
    private EditText amount = null;
    private Button TopUpButton = null;
    private TextView initial;
    private TextView username;
    private TextView email;
    private TextView balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        getSupportActionBar().hide();

        mContext = this;
        mApiService = UtilsApi.getApiService();

        initial = findViewById(R.id.initial);
        username  = findViewById(R.id.username);
        email = findViewById(R.id.email);
        balance  = findViewById(R.id.balance);
        TopUpButton = findViewById(R.id.button);
        amount = findViewById(R.id.balanceInput);

        String balances = String.format(Locale.getDefault(), "%.2f", MainActivity.loggedAccount.balance);

        initial.setText(getInitials(MainActivity.loggedAccount.name));
        username.setText(MainActivity.loggedAccount.name);
        email.setText(MainActivity.loggedAccount.email);
        balance.setText("IDR " + balances);

        TopUpButton.setOnClickListener(v->{
            topUp();
        });

    }

    private String getInitials(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        String[] parts = name.split(" ");
        String initials = "";
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                initials += part.substring(0, 1).toUpperCase();
            }
        }
        return initials;
    }

    protected void topUp(){
        int userId = MainActivity.loggedAccount.id;
        String amountS = amount.getText().toString();
        if(amountS.isEmpty()){
            Toast.makeText(mContext,"Field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        double amountD = Double.parseDouble(amountS);
        mApiService.topUp(userId, amountD).enqueue(new Callback<BaseResponse<Account>>(){
            public void onResponse(Call<BaseResponse< Account >> call, Response<BaseResponse<Account>> response){
                if(!response.isSuccessful()){
                    Toast.makeText(mContext, "Application error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                BaseResponse<Account> res = response.body();
                if(res.success){
                    MainActivity.loggedAccount.balance = res.payload.balance;
                    String balances = String.format(Locale.getDefault(), "%.2f", MainActivity.loggedAccount.balance);
                    balance.setText("IDR " + balances);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Account>> call, Throwable t) {
                System.out.println("On Failure");
                Toast.makeText(mContext, "Invalid Input", Toast.LENGTH_SHORT).show();
            }
        });
    }
}