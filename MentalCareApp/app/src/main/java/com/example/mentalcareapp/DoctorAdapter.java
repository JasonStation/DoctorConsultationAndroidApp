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

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.RecycleHolder> {

    private static ArrayList<Integer> docID = new ArrayList<Integer>();
    private static ArrayList<String> docName = new ArrayList<String>();
    private static ArrayList<String> docDesc = new ArrayList<String>();
    private static ArrayList<Integer> docImg = new ArrayList<Integer>();
    Context dataContext;

    public DoctorAdapter(Context context, ArrayList<Integer> id, ArrayList<String> name, ArrayList<String> desc,
                          ArrayList<Integer> img){
        dataContext = context;
        docName = name;
        docDesc = desc;
        //docCat = cat;
        docID = id;
        docImg = img;

    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(dataContext);
        View rv = inf.inflate(R.layout.recycler_row, parent, false);
        return new RecycleHolder(rv);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, int position) {
        holder.name.setText(docName.get(position));
        holder.desc.setText(docDesc.get(position));
        holder.img.setImageResource(docImg.get(position));

        holder.layoutCon.setOnClickListener(v -> {
            Intent i = new Intent(dataContext, DoctorDetail.class);
            i.putExtra("doctorName", docName.get(position));
            i.putExtra("doctorDesc", docDesc.get(position));
            i.putExtra("doctorImage", docImg.get(position));
            dataContext.startActivity(i);

        });
    }


    @Override
    public int getItemCount() {
        return docName.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder{
        TextView name, desc;
        ImageView img;
        ConstraintLayout layoutCon;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.docName);
            desc = itemView.findViewById(R.id.docDesc);
            img = itemView.findViewById(R.id.docImage);
            layoutCon = itemView.findViewById(R.id.docView);
        }
    }


}
