package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.MuhammadBillieElianJBusRS.jbus_android.model.Bus;
import com.MuhammadBillieElianJBusRS.jbus_android.request.BaseApiService;
import com.MuhammadBillieElianJBusRS.jbus_android.request.UtilsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageBusActivity extends AppCompatActivity {
    private BaseApiService mApiService;
    private Context mContext;
    private ListView myBusListView;
    ImageView addSched;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_bus);

        getSupportActionBar().setTitle("Manage Bus");

        mContext = this;
        mApiService = UtilsApi.getApiService();
        myBusListView = this.findViewById(R.id.my_bus_list_view);

        loadMyBus();
    }

    protected void loadMyBus() {
        mApiService.getMyBus(LoginActivity.loggedAcccount.id).enqueue(new Callback<List<Bus>>() {
            @Override
            public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                if (!response.isSuccessful()) return;

                List<Bus> myBusList = response.body();
                MyArrayAdapter adapter = new MyArrayAdapter(mContext, myBusList);
                myBusListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Bus>> call, Throwable t) {

            }
        });
    }

    private class MyArrayAdapter extends ArrayAdapter<Bus> {

        public MyArrayAdapter(@NonNull Context context, @NonNull List<Bus> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View currentItemView = convertView;

            // of the recyclable view is null then inflate the custom layout for the same
            if (currentItemView == null) {
                currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.my_bus_view, parent, false);
            }

            // get the position of the view from the ArrayAdapter
            Bus currentBus = getItem(position);

            // then according to the position of the view assign the desired TextView 1 for the same
            TextView busName = currentItemView.findViewById(R.id.bus_name);
            busName.setText(currentBus.name);

            // then according to the position of the view assign the desired TextView 2 for the same
            ImageView addSched = currentItemView.findViewById(R.id.manage_schedule);
            addSched.setOnClickListener(v->{
                Intent i = new Intent(mContext, ManageBusSchedule.class);
                i.putExtra("busId", currentBus.id);
                mContext.startActivity(i);
            });

            // then return the recyclable view
            return currentItemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manage_bus_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.add_bus) {
            Intent intent = new Intent(mContext, AddEditBusActivity.class);
            intent.putExtra("type", "addBus");
            startActivity(intent);
            return true;
        } else return super.onOptionsItemSelected(item);
    }
}