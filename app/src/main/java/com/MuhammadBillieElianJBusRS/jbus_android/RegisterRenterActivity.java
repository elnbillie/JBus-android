package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MuhammadBillieElianJBusRS.jbus_android.request.UtilsApi;
import com.MuhammadBillieElianJBusRS.jbus_android.model.Account;
import com.MuhammadBillieElianJBusRS.jbus_android.model.BaseResponse;
import com.MuhammadBillieElianJBusRS.jbus_android.request.BaseApiService;
import com.MuhammadBillieElianJBusRS.jbus_android.model.Renter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRenterActivity extends AppCompatActivity {

    private BaseApiService mApiService;
    private Context mContext;
    private EditText companyName, Address, PhoneNumber;
    private Button registerButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_renter);

        mContext = this;
        mApiService = UtilsApi.getApiService();
        companyName = findViewById(R.id.companyName);
        Address = findViewById(R.id.Address);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        registerButton = findViewById(R.id.button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegister();
            }
        });
    }

    protected void handleRegister() {
        int userId = MainActivity.loggedAccount.id;
        String companyNameS = companyName.getText().toString();
        String AddressS = Address.getText().toString();
        String PhoneS = PhoneNumber.getText().toString();
        if (companyNameS.isEmpty() || AddressS.isEmpty() || PhoneS.isEmpty()) {
            Toast.makeText(mContext, "Field cannot be empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mApiService.registerRenter(userId, companyNameS, AddressS, PhoneS).enqueue(new Callback<BaseResponse<Renter>>() {
            @Override
            public void onResponse(Call<BaseResponse<Renter>> call, Response<BaseResponse<Renter>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Application error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                BaseResponse<Renter> res = response.body();

                if (res.success) finish();
                MainActivity.loggedAccount.company = res.payload;
                Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, AboutMeActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<BaseResponse<Renter>> call, Throwable t) {
                Toast.makeText(mContext, "Problem with the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}