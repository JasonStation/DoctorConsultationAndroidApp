package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DocList extends AppCompatActivity {

    public static ArrayList<Integer> doctorID = new ArrayList<Integer>();
    public static ArrayList<String> doctorName = new ArrayList<String>();
    public static ArrayList<String> doctorDesc = new ArrayList<String>();
    public static ArrayList<Integer> doctorImg = new ArrayList<Integer>();
    public static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_list);

        recyclerView = findViewById(R.id.doctorList);

        String jsonDoctors = "{\n" +
                "  \"doctors\": [\n" +
                "    {\n" +
                "      \"name\": \"Dr Markus Bleu\",\n" +
                "      \"desc\": \"Hello I am a doctor, and I specialize in all kinds of health problems.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dr Samus Mike\",\n" +
                "      \"desc\": \"I am a doctor who specializes in mental care. Contact me if needed!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dr Bobby Bobbins\",\n" +
                "      \"desc\": \"Hello I am Bobby from Telemedicine! Hope you come accross me in the app and enjoy my consultations!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dr Walter Felix\",\n" +
                "      \"desc\": \"Another day another Walter's day! Hi guys! I am a doctor and I love to consult my customers!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dr Jeff Hu\",\n" +
                "      \"desc\": \"I am Dr Jeff. A professional mental care specialist for 12 years.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dr John Steve\",\n" +
                "      \"desc\": \"An apple a day keeps the doctor away, consult me through Telemedicine for more info. Sent from my iPhone\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dr Frizzy Bobkins\",\n" +
                "      \"desc\": \"Hello I am a doctor that specializes in health.\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        if(doctorName.isEmpty()){
            doctorImg.add(R.drawable.doctorwithstet);
            doctorImg.add(R.drawable.doc3);
            doctorImg.add(R.drawable.doc4);
            doctorImg.add(R.drawable.doc5);
            doctorImg.add(R.drawable.doc6);
            doctorImg.add(R.drawable.doc7);
            doctorImg.add(R.drawable.docgirl);

            try {
                JSONObject jsonObject = new JSONObject(jsonDoctors);
                JSONArray jsonArray = jsonObject.getJSONArray("doctors");
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String jName = object.getString("name");
                    String jDesc = object.getString("desc");

                    doctorID.add(i + 1);
                    doctorName.add(jName);
                    doctorDesc.add(jDesc);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        DoctorAdapter ra = new DoctorAdapter(this, doctorID, doctorName, doctorDesc, doctorImg);
        recyclerView.setAdapter(ra);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

    }
}