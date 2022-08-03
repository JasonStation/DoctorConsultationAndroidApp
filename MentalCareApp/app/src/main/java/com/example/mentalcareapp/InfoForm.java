package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InfoForm extends AppCompatActivity {

    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_form);

        Button orderNow = (Button) findViewById(R.id.orderNow);
        String totalPrice = String.valueOf(ShoppingCartList.subtotal);
        orderNow.setText("Buy Now ($" + totalPrice + ")");

        database = new DatabaseHelper(getApplicationContext());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("Order", "Order", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }

        orderNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                NotificationCompat.Builder notification = new NotificationCompat.Builder(InfoForm.this, "Order");
                notification.setContentTitle("Telemedicine: Order Received");
                notification.setContentText("Your order of " + String.valueOf(ShoppingCartList.numberOfItems) + " items have been ordered.");
                notification.setSmallIcon(R.drawable.ic_baseline_medication_24);
                notification.setAutoCancel(true);

                NotificationManagerCompat manager = NotificationManagerCompat.from(InfoForm.this);
                manager.notify(1, notification.build());

                Toast.makeText(getApplicationContext(), "Items have been bought!", Toast.LENGTH_SHORT).show();
                ShoppingCartList.cartName.clear();
                ShoppingCartList.cartPrice.clear();
                ShoppingCartList.subtotal = 0;
                ShoppingCartList.numberOfItems = 0;
                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                database.deleteAllData();
                editor.putInt("subtotal", ShoppingCartList.subtotal);
                editor.putInt("totalItems", ShoppingCartList.numberOfItems);
                editor.commit();

                Intent i = new Intent(getApplicationContext(), Homepage.class);
                startActivity(i);

            }
        });

    }
}