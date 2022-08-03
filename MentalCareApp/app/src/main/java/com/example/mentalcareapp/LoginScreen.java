package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button loginButton = findViewById(R.id.loginButton);
        EditText emailField = findViewById(R.id.logEmail);
        EditText passField = findViewById(R.id.logPass);

        database = new DatabaseHelper(getApplicationContext());

        Cursor cur = database.viewData();

        if(cur.getCount() == 0) Toast.makeText(getApplicationContext(), "Nothing here.", Toast.LENGTH_SHORT).show();
        else{
            while(cur.moveToNext()){
                Signup.userId.add(cur.getInt(0));
                Signup.userName.add(cur.getString(1));
                Signup.userEmail.add(cur.getString(2));
                Signup.userPassword.add(cur.getString(3));
            }
        }


        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String sEmail = emailField.getText().toString();
                String sPassword = passField.getText().toString();
                if(!Signup.userEmail.contains(sEmail) || !Signup.userPassword.contains(sPassword)){
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Signup.loggedInId = Signup.userId.get(Signup.userEmail.indexOf(sEmail));
                    Intent i = new Intent(getApplicationContext(), Homepage.class);
                    startActivity(i);
                }
            }

        });

    }
}