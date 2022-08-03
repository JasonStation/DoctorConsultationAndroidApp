package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DoctorDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static String apDate;
    private static String sName;
    private static String sApDesc;
    DatabaseHelper database;
    private static int apHour, apMinute;
    private static String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        ImageView docImage = findViewById(R.id.doctorImage);
        TextView docName = findViewById(R.id.doctorName);
        TextView docDesc = findViewById(R.id.docDescription);
        EditText apDesc = findViewById(R.id.docAppointment);

        sName = getIntent().getStringExtra("doctorName");
        String sDesc = getIntent().getStringExtra("doctorDesc");
        //String sSynopsis = getIntent().getStringExtra("dataSynopsis");
        int iImage = getIntent().getIntExtra("doctorImage", 1);
        sApDesc = apDesc.getText().toString();

        docName.setText(sName);
        docDesc.setText(sDesc);
        docImage.setImageResource(iImage);

        database = new DatabaseHelper(getApplicationContext());

        Button apButton = findViewById(R.id.requestButton);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("Appointment", "Appointment", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }

        apButton.setOnClickListener(v -> {
            DialogFragment df = new DatePickerFragment();
            df.show(getSupportFragmentManager(), "appointment date");

            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(DoctorDetail.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                    time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, min);
                    sApDesc = apDesc.getText().toString();
                    if (sApDesc.matches("")) {
                        sApDesc = "No description.";
                    }
                    database.insertDataAp(sName, sApDesc, apDate, time, Signup.loggedInId);
                    Toast.makeText(getApplicationContext(), "Appointment booked on " + apDate + " at " + time, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), DocList.class);
                    startActivity(i);
                    NotificationCompat.Builder notification = new NotificationCompat.Builder(DoctorDetail.this, "Appointment");
                    notification.setContentTitle("Appointment Reminder");
                    notification.setContentText("You have an appointment with " + sName + " on " + apDate + " at " + time);
                    notification.setSmallIcon(R.drawable.ic_baseline_local_hospital_24);
                    notification.setAutoCancel(true);

                    NotificationManagerCompat manager = NotificationManagerCompat.from(DoctorDetail.this);
                    manager.notify(1, notification.build());
                }
            }, hour, minute, true);
            timePickerDialog.show();

        });

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        String currDate = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());

        apDate = currDate;
    }

}