package com.example.mentalcareapp;//2440051574 - Jason Leonardo Sutioso - Nomor 3

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicineList extends AppCompatActivity {

    public static ArrayList<Integer> proId = new ArrayList<Integer>();
    public static ArrayList<String> proName = new ArrayList<String>();
    public static ArrayList<Integer> proPrice = new ArrayList<>();
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        Button shoppingCart = (Button) findViewById(R.id.shoppingButton);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        int keepNumberOfItems = sh.getInt("totalItems", ShoppingCartList.numberOfItems);
        shoppingCart.setText("View Shopping Cart (" + String.valueOf(keepNumberOfItems) + ")");

        shoppingCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ShoppingCartList.cartId.clear();
                ShoppingCartList.cartName.clear();
                ShoppingCartList.cartPrice.clear();
                Intent i = new Intent(getApplicationContext(), ShoppingCartList.class);
                startActivity(i);
            }
        });

        if(proId.isEmpty()){
            proId.add(1);
            proName.add("(ETHANOL70%) DR KEEPS ONE GEL (ETHANOL)");
            proPrice.add(15);

            proId.add(2);
            proName.add("(ETHANOL72%) DR KEEPS 72 GEL (ETHANOL)");
            proPrice.add(30);

            proId.add(3);
            proName.add("(FRULI) 75% ALCOHOL DISINFECTANT");
            proPrice.add(20);

            proId.add(4);
            proName.add("(FRULI) HAND ANTISEPTIC RINSE FREE GEL");
            proPrice.add(20);

            proId.add(5);
            proName.add("(FRULI) QUICK-DRY DISPOSABLE HAND SANITIZER");
            proPrice.add(25);

        }

        ListView items = (ListView) findViewById(R.id.mainView);

        CustomAdapter ca = new CustomAdapter();

        items.setAdapter(ca);

        database = new DatabaseHelper(getApplicationContext());

        items.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database.insertDataMed(ShoppingCartList.itemIdIncrement + 1, proName.get(position), proPrice.get(position));
                Toast.makeText(getApplicationContext(), "Item added to your shopping cart. " + proPrice.get(position).toString(), Toast.LENGTH_SHORT).show();
                ShoppingCartList.cartId.clear();
                ShoppingCartList.cartName.clear();
                ShoppingCartList.cartPrice.clear();
                ShoppingCartList.subtotal += proPrice.get(position);
                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                ShoppingCartList.itemIdIncrement++;
                ShoppingCartList.numberOfItems++;
                editor.putInt("itemIdIncrement", ShoppingCartList.itemIdIncrement);
                editor.putInt("totalItems", ShoppingCartList.numberOfItems);
                editor.putInt("subtotal", ShoppingCartList.subtotal);
                editor.commit();

                Intent i = new Intent(getApplicationContext(), MedicineList.class);
                startActivity(i);
            }
        });
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return proId.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.listviewpro, null);

            TextView title = (TextView) view.findViewById(R.id.medName);
            TextView price = (TextView) view.findViewById(R.id.medPrice);

            title.setText(proName.get(i));
            price.setText("$" + proPrice.get(i).toString());

            return view;
        }
    }
}