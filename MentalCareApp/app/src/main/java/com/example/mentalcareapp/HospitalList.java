package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HospitalList extends AppCompatActivity {

    public static ArrayList<Integer> hospitalID = new ArrayList<Integer>();
    public static ArrayList<String> hospitalName = new ArrayList<String>();
    public static ArrayList<String> hospitalDesc = new ArrayList<String>();
    public static ArrayList<Integer> hospitalImg = new ArrayList<Integer>();
    public static ArrayList<Double> hospitalLat = new ArrayList<Double>();
    public static ArrayList<Double> hospitalLong = new ArrayList<Double>();
    public static RecyclerView recyclerViewHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        recyclerViewHospital = findViewById(R.id.hosList);

        if(hospitalImg.isEmpty()){
            hospitalID.add(1);
            hospitalName.add("Rumah Sakit Gading Pluit");
            hospitalDesc.add("Jl. Boulevard Timur, RT.5/RW.2, Pegangsaan Dua, Kelapa Gading, North Jakarta City, Jakarta 14250");
            hospitalImg.add(R.drawable.hospitalgadingpluit);
            hospitalLat.add(-6.165810992175974);
            hospitalLong.add(106.91581162402507);

            hospitalID.add(2);
            hospitalName.add("Rumah Sakit Mitra Jaya");
            hospitalDesc.add("Jl. Raya Gading Kirana No.Kav.2, RT.18/RW.8, Klp. Gading Bar., Kec. Klp. Gading, Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14240\n");
            hospitalImg.add(R.drawable.rumahsakitgeneric);
            hospitalLat.add(-6.150050732137486);
            hospitalLong.add(106.89756249362281);

            hospitalID.add(3);
            hospitalName.add("Rumah Sakit Firdaus");
            hospitalDesc.add("Indomaret Komplek Bea Cukai, Jl. Siak No.14, RT.10/RW.7, Sukapura, Kec. Cilincing, Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14140\n");
            hospitalImg.add(R.drawable.hospital3);
            hospitalLat.add(-6.156138421256537);
            hospitalLong.add(106.92306505363149);

            hospitalID.add(4);
            hospitalName.add("RSU Pekerja");
            hospitalDesc.add("Jl. Tipar Cakung No.46, RT.2/RW.1, Sukapura, Kec. Cilincing, Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14140\n");
            hospitalImg.add(R.drawable.hospital4);
            hospitalLat.add(-6.145374912253198);
            hospitalLong.add(106.92330887786201);

            hospitalID.add(5);
            hospitalName.add("Mitra Keluarga Kelapa Gading");
            hospitalDesc.add("Jl. Raya Gading Kirana No.Kav.2, RT.18/RW.8, Klp. Gading Bar., Kec. Klp. Gading, Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14240\n");
            hospitalImg.add(R.drawable.hospital5);
            hospitalLat.add(-6.150050732137486);
            hospitalLong.add(106.89756249362281);

        }

        HospitalAdapter ra = new HospitalAdapter(this, hospitalID, hospitalName, hospitalDesc, hospitalImg, hospitalLat, hospitalLong);
        recyclerViewHospital.setAdapter(ra);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerViewHospital.setLayoutManager(llm);

    }
}