package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i  = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(i);
            }

        });

        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i  = new Intent(getApplicationContext(), Signup.class);
                startActivity(i);
            }

        });

    }
}