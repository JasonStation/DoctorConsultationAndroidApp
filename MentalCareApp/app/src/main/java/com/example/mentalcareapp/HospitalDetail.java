package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HospitalDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        ImageView hosImage = findViewById(R.id.hospitalImage);
        TextView hosName = findViewById(R.id.hospitalName);
        TextView hosDesc = findViewById(R.id.hospitalDescription);
        Button mapBtn = findViewById(R.id.hospitalButton);

        String sName = getIntent().getStringExtra("hospitalName");
        String sDesc = getIntent().getStringExtra("hospitalDesc");
        //String sSynopsis = getIntent().getStringExtra("dataSynopsis");
        int iImage = getIntent().getIntExtra("hospitalImage", 1);
        double lat = getIntent().getDoubleExtra("hospitalLatitude", 1);
        double longi = getIntent().getDoubleExtra("hospitalLongitude", 1);

        hosName.setText(sName);
        hosDesc.setText(sDesc);
        hosImage.setImageResource(iImage);

        mapBtn.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MapView.class);
            i.putExtra("hospitalLatitude", lat);
            i.putExtra("hospitalLongitude", longi);
            startActivity(i);
        });


    }
}