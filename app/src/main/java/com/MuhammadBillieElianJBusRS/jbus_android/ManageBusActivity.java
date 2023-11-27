package com.MuhammadBillieElianJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.MuhammadBillieElianJBusRS.jbus_android.model.ListsView;
import com.MuhammadBillieElianJBusRS.jbus_android.model.ListsViewAdapter;

import java.util.ArrayList;

public class ManageBusActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_bus);

        final ArrayList<ListsView> arrayList = new ArrayList<ListsView>();

        arrayList.add(new ListsView("FBus1"));
        arrayList.add(new ListsView("FBRus2"));
        ListsViewAdapter listsArrayAdapter = new ListsViewAdapter(this, arrayList);
        ListView numbersListView = findViewById(R.id.listView);
        numbersListView.setAdapter(listsArrayAdapter);


    }

    private void moveActivity(Context ctx, Class<?> cls ){
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_bus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_button) {
            moveActivity(this, AddBusActivity.class);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}