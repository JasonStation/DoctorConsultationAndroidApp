package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Signup extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    DatabaseHelper database;
    public static ArrayList<Integer> userId = new ArrayList<Integer>();
    public static ArrayList<String> userName = new ArrayList<>();
    public static ArrayList<String> userEmail = new ArrayList<String>();
    public static ArrayList<String> userPassword = new ArrayList<String>();
    public static int incrementId = 1;
    public static int loggedInId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button regButton = findViewById(R.id.regButton);
        EditText fullName = findViewById(R.id.regName);
        EditText email = findViewById(R.id.regEmail);
        EditText password = findViewById(R.id.regPass);
        TextView warning = findViewById(R.id.regWarning);
        CheckBox agree = findViewById(R.id.agreeCheckbox);
        TextView dateText = findViewById(R.id.dateText);

        database = new DatabaseHelper(getApplicationContext());

        Button datePicker = findViewById(R.id.regDatePicker);
        datePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment df = new DatePickerFragment();
                df.show(getSupportFragmentManager(), "date picker");
            }

        });

        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String sFullName = fullName.getText().toString();
                String sEmail = email.getText().toString();
                String sPassword = password.getText().toString();
                String sdateText = dateText.getText().toString();
                if(sFullName.isEmpty() || sEmail.isEmpty() || sPassword.isEmpty()){
                    warning.setText("Please fill in the fields.");
                }else if(!sEmail.contains("@") || !sEmail.contains(".")){
                    warning.setText("Please enter a valid email address");
                }
                else if(sPassword.length() < 8) {
                    warning.setText("Password must at least have 8 characters");
                }
                else if(sdateText.equals("Choose date")){
                    warning.setText("Please choose birth date");
                }
                else if(!agree.isChecked()){
                    warning.setText("Please accept the license agreement");
                }
                else if(userName.contains(sEmail)){
                    warning.setText("That email address has already been registered");
                }

                else {
                    database.insertData(sFullName, sEmail, sPassword);
                    Cursor cur = database.viewData();
                    if (cur.getCount() == 0)
                        Toast.makeText(getApplicationContext(), "Nothing here.", Toast.LENGTH_SHORT).show();
                    else {
                        while (cur.moveToNext()) {
                            userId.add(cur.getInt(0));
                            userName.add(cur.getString(1));
                            userEmail.add(cur.getString(2));
                            userPassword.add(cur.getString(3));
                        }
                    }
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Account successfully registered.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        String currDate = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());

        TextView dateChosen = (TextView) findViewById(R.id.dateText);
        dateChosen.setText(currDate);
    }
}