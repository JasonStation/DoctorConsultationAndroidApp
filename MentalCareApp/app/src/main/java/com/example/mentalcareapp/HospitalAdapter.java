package com.example.mentalcareapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.RecycleHolder> {

    private static ArrayList<Integer> hosID = new ArrayList<Integer>();
    private static ArrayList<String> hosName = new ArrayList<String>();
    private static ArrayList<String> hosDesc = new ArrayList<String>();
    private static ArrayList<Integer> hosImg = new ArrayList<Integer>();
    private static ArrayList<Double> hosLat = new ArrayList<Double>();
    private static ArrayList<Double> hosLong = new ArrayList<Double>();
    Context dataContext;

    public HospitalAdapter(Context context, ArrayList<Integer> id, ArrayList<String> name, ArrayList<String> desc,
                         ArrayList<Integer> img, ArrayList<Double> lat, ArrayList<Double> longi){
        dataContext = context;
        hosName = name;
        hosDesc = desc;
        //docCat = cat;
        hosID = id;
        hosImg = img;
        hosLat = lat;
        hosLong = longi;

    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(dataContext);
        View rv = inf.inflate(R.layout.hospital_row, parent, false);
        return new RecycleHolder(rv);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, int position) {
        holder.name.setText(hosName.get(position));
        holder.desc.setText(hosDesc.get(position));
        holder.img.setImageResource(hosImg.get(position));

        holder.layoutCon.setOnClickListener(v -> {
            Intent i = new Intent(dataContext, HospitalDetail.class);
            i.putExtra("hospitalName", hosName.get(position));
            i.putExtra("hospitalDesc", hosDesc.get(position));
            i.putExtra("hospitalImage", hosImg.get(position));
            i.putExtra("hospitalLatitude", hosLat.get(position));
            i.putExtra("hospitalLongitude", hosLong.get(position));
            dataContext.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return hosName.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder{
        TextView name, desc;
        ImageView img;
        ConstraintLayout layoutCon;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.hosName);
            desc = itemView.findViewById(R.id.hosDesc);
            img = itemView.findViewById(R.id.hosImage);
            layoutCon = itemView.findViewById(R.id.hosView);
        }
    }


}
