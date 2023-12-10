package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MuhammadBillieElianJBusRS.jbus_android.model.Account;
import com.MuhammadBillieElianJBusRS.jbus_android.model.BaseResponse;
import com.MuhammadBillieElianJBusRS.jbus_android.request.BaseApiService;
import com.MuhammadBillieElianJBusRS.jbus_android.request.UtilsApi;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutMeActivity extends AppCompatActivity {
    private BaseApiService mApiService;
    private Context mContext;
    TextView initial = null;
    TextView username = null;
    TextView email = null;
    TextView balance = null;
    TextView statusRenter = null;
    TextView registerCompany = null;
    EditText amount = null;
    Button topUpButton = null;
    Button manageBus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        initial = this.findViewById(R.id.initial_name);
        username = this.findViewById(R.id.username);
        email = this.findViewById(R.id.email);
        balance = this.findViewById(R.id.balance);
        amount = this.findViewById(R.id.top_up_amount);
        statusRenter = this.findViewById(R.id.status_renter);
        registerCompany = this.findViewById(R.id.register_company);
        topUpButton = this.findViewById(R.id.top_up_button);
        manageBus = this.findViewById(R.id.button_manage_bus);

        mContext = this;
        mApiService = UtilsApi.getApiService();
        handleRefreshAccount();

        topUpButton.setOnClickListener(v->{
            handleTopUp();
        });

        registerCompany.setOnClickListener(v->{
            startActivity(new Intent(mContext, RegisterRenterActivity.class));
        });

        manageBus.setOnClickListener(v->{
            startActivity(new Intent(mContext, ManageBusActivity.class));
        });
    }

    private void loadData(Account a) {
        initial.setText(""+a.name.toUpperCase().charAt(0));
        username.setText(a.name);
        email.setText(a.email);
        balance.setText("IDR "+a.balance);
        if (a.company == null) {
            statusRenter.setText("You're not registered as a renter");
            manageBus.setVisibility(View.GONE);
            registerCompany.setVisibility(View.VISIBLE);
        } else {
            statusRenter.setText("You're already registered as a renter");
            manageBus.setVisibility(View.VISIBLE);
            registerCompany.setVisibility(View.GONE);
        }
    }

    protected void handleTopUp() {
        String amountS = amount.getText().toString();
        Double amountD = amountS.isEmpty() ? 0d : Double.parseDouble(amountS);
        mApiService.topUp(LoginActivity.loggedAcccount.id, amountD).enqueue(new Callback<BaseResponse<Double>>() {
            @Override
            public void onResponse(Call<BaseResponse<Double>> call, Response<BaseResponse<Double>> response) {
                BaseResponse<Double> res = response.body();
                if(res.success) {
                    LoginActivity.loggedAcccount.balance += res.payload;
                    String balances = String.format(Locale.getDefault(), "%.2f", LoginActivity.loggedAcccount.balance);
                    balance.setText("IDR " + balances);
                }
                Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse<Double>> call, Throwable t) {

            }
        });
    }
    protected void handleRefreshAccount() {
        BaseApiService mApiService = UtilsApi.getApiService();
        mApiService.getAccountbyId(LoginActivity.loggedAcccount.id).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "App error", Toast.LENGTH_SHORT).show();
                    return;
                }

                // if success, get the response body
                Account responseAccount = response.body();
                loadData(responseAccount);
            }

            // method for handling error talking to the server
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                // do something
            }
        });
    }
}