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

public class ListsViewAdapter extends ArrayAdapter<ListsView>{
    public ListsViewAdapter(@NonNull Context context, ArrayList<ListsView> arrayList) {
        super(context, 0, arrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        ListsView currentNumberPosition = getItem(position);
        TextView textView2 = currentItemView.findViewById(R.id.busList);
        textView2.setText(currentNumberPosition.getBusList());
        // then return the recyclable view
        return currentItemView;
    }
}
