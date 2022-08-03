package com.example.mentalcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapView extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMaps;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.bookMapView);
        supportMapFragment.getMapAsync(this);

        latitude = getIntent().getDoubleExtra("hospitalLatitude", 1);
        longitude = getIntent().getDoubleExtra("hospitalLongitude", 1);

        Button mapBtn = findViewById(R.id.backMap);

        mapBtn.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HospitalList.class);
            startActivity(i);
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap gMap) {
        googleMaps = gMap;
        MarkerOptions mo = new MarkerOptions();
        LatLng ll = new LatLng(latitude, longitude);
        mo.position(ll);
        googleMaps.clear();
        googleMaps.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
        googleMaps.addMarker(mo);
        //Toast.makeText(getApplicationContext(), Double.toString(latitude) + " " + Double.toString(longitude), Toast.LENGTH_SHORT).show();

    }
}