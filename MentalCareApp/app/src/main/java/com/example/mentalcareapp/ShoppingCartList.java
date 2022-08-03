package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingCartList extends AppCompatActivity {

    public static ArrayList<Integer> cartId = new ArrayList<Integer>();
    public static ArrayList<String> cartName = new ArrayList<String>();
    public static ArrayList<Integer> cartPrice = new ArrayList<Integer>();
    DatabaseHelper database;
    public static int subtotal = 0;
    public static int itemIdIncrement = 0;
    public static int numberOfItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_list);

        Button buyNow = (Button) findViewById(R.id.buyButton);

        ListView shoppingCart = (ListView) findViewById(R.id.shopList);
        database = new DatabaseHelper(getApplicationContext());

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        numberOfItems = sh.getInt("totalItems", 0);
        subtotal = sh.getInt("subtotal", 0);
        String totalPriceStr = String.valueOf(subtotal);

        TextView subView = (TextView) findViewById(R.id.total);

        subView.setText("Subtotal: $" + totalPriceStr);

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Medicine_Table", null);
        CursorAdapter cursorAdapter = new CursorAdapter(this, cursor);

        Cursor cur = database.viewDataMed();
        if(cur.getCount() == 0) Toast.makeText(getApplicationContext(), "Nothing here.", Toast.LENGTH_SHORT).show();
        else{
            while(cur.moveToNext()){
                cartId.add(cur.getInt(0));
                cartName.add(cur.getString(1));
                cartPrice.add(cur.getInt(2));
            }
        }

        buyNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!cartName.isEmpty()) {
                    cartId.clear();
                    cartName.clear();
                    cartPrice.clear();
                    Intent i = new Intent(getApplicationContext(), InfoForm.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please add an item to your shopping cart.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        shoppingCart.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subtotal -= cartPrice.get(position);
                numberOfItems--;
                SharedPreferences sp = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("subtotal", subtotal);
                editor.putInt("totalItems", numberOfItems);
                editor.commit();
                editor.apply();
                database.deleteDataMed(cartId.get(position));
                //Toast.makeText(getApplicationContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                cartId.clear();
                cartName.clear();
                cartPrice.clear();
                Intent i = new Intent(getApplicationContext(), ShoppingCartList.class);
                startActivity(i);
            }
        });

        shoppingCart.setAdapter(cursorAdapter);
        cursorAdapter.changeCursor(cursor);

        CustomAdapter2 ca = new CustomAdapter2();

        shoppingCart.setAdapter(ca);

    }

    class CustomAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return cartId.size();
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
            view = getLayoutInflater().inflate(R.layout.listviewcart, null);

            TextView title = (TextView) view.findViewById(R.id.cartName);
            TextView price = (TextView) view.findViewById(R.id.cartPrice);

            title.setText(cartName.get(i));
            price.setText("$" + cartPrice.get(i).toString());

            return view;
        }
    }
}