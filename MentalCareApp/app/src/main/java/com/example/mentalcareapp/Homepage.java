package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {

    CardView doctorButton;
    CardView hospitalButton;
    CardView appointmentButton;
    CardView aboutUsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        doctorButton = findViewById(R.id.docButton);
        hospitalButton = findViewById(R.id.hosButton);
        appointmentButton = findViewById(R.id.appButton);
        aboutUsButton = findViewById(R.id.aboutButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        doctorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DocList.class);
                startActivity(intent);
            }

        });

        hospitalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), HospitalList.class);
                startActivity(intent);
            }

        });

        appointmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), AppointmentList.class);
                startActivity(intent);
            }

        });

        aboutUsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MedicineList.class);
                startActivity(intent);
            }

        });

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });

    }
}