package com.example.mentalcareapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CursorAdapter extends android.widget.CursorAdapter {
    public CursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @SuppressLint("ResourceType")
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.id.listviewcart, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView medicineName = (TextView) view.findViewById(R.id.medName);
        TextView medicinePrice = (TextView) view.findViewById(R.id.medPrice);
        String med_name = cursor.getString(cursor.getColumnIndexOrThrow("MED_NAME"));
        int med_price = cursor.getInt(cursor.getColumnIndexOrThrow("MED_PRICE"));
        medicineName.setText(med_name);
        medicinePrice.setText(String.valueOf(med_price));
    }
}
