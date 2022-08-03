package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AppointmentList extends AppCompatActivity {

    public static ArrayList<Integer> apId = new ArrayList<Integer>();
    public static ArrayList<String> apName = new ArrayList<String>();
    public static ArrayList<String> apDesc = new ArrayList<String>();
    public static ArrayList<String> apDate = new ArrayList<String>();
    public static ArrayList<String> apTime = new ArrayList<String>();

    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        ListView appointmentList = findViewById(R.id.apList);

        database = new DatabaseHelper(getApplicationContext());

        apId.clear();
        apName.clear();
        apDesc.clear();
        apDate.clear();
        apTime.clear();

        Cursor cur = database.viewDataApUser(Signup.loggedInId);
        if(cur.getCount() == 0) Toast.makeText(getApplicationContext(), "Nothing here.", Toast.LENGTH_SHORT).show();
        else{
            while(cur.moveToNext()){
                apId.add(cur.getInt(0));
                apName.add(cur.getString(1));
                apDesc.add(cur.getString(2));
                apDate.add(cur.getString(3));
                apTime.add(cur.getString(4));
            }
        }

        CustomAdapter ca = new CustomAdapter();
        appointmentList.setAdapter(ca);


    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return apId.size();
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
            view = getLayoutInflater().inflate(R.layout.appointment_listview, null);

            TextView title = (TextView) view.findViewById(R.id.apName);
            TextView date = (TextView) view.findViewById(R.id.apDate);
            TextView time = (TextView) view.findViewById(R.id.apTime);
            TextView desc = (TextView) view.findViewById(R.id.apDesc);

            title.setText(apName.get(i));
            date.setText(apDate.get(i));
            time.setText(apTime.get(i));
            desc.setText(apDesc.get(i));

            return view;
        }
    }
}