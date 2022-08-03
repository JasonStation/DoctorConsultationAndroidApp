package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class AboutUs extends AppCompatActivity {
    Button backToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        backToHome = findViewById(R.id.backToHome);

        backToHome.setOnClickListener(v -> {
          Intent i = new Intent(getApplicationContext(), Homepage.class);
          startActivity(i);
        });
    }
}