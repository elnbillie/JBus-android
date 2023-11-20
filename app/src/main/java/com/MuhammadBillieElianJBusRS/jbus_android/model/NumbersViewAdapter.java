package com.MuhammadBillieElianJBusRS.jbus_android.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.MuhammadBillieElianJBusRS.jbus_android.R;

import java.util.ArrayList;

public class NumbersViewAdapter extends ArrayAdapter<NumbersView> {
    public NumbersViewAdapter(@NonNull Context context, ArrayList<NumbersView> arrayList) {
        super(context, 0, arrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.bus_view, parent, false);
        }
        NumbersView currentNumberPosition = getItem(position);
        TextView textView2 = currentItemView.findViewById(R.id.namaBus);
        textView2.setText(currentNumberPosition.getBusName());
        // then return the recyclable view
        return currentItemView;
    }
}

