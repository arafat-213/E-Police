package com.svit.epolice.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.svit.epolice.R;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {

    ArrayList<String> policeStationsArrayList;
    ArrayList<String> policemenArrayList;
    Spinner policeStationsSpinner, policeMenSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        policeStationsSpinner = findViewById(R.id.policeStationsSpinner);
        policeMenSpinner = findViewById(R.id.policeMenSpinner);
        policeStationsArrayList = new ArrayList<String>();
        policemenArrayList = new ArrayList<String>();

        policeStationsArrayList.add("Old City");
        policeStationsArrayList.add("Karelibag");
        policeStationsArrayList.add("Manjalpur");
        policeStationsArrayList.add("Gotri");
        policeStationsArrayList.add("Sayajigunj");

        policemenArrayList.add("Bajirao Singham");
        policemenArrayList.add("Jaykant sikhre");
        policemenArrayList.add("Constable Patil");
        policemenArrayList.add("Hawaldar Gokhle");


        final ArrayList<String> ManjalpurPoliceMen = new ArrayList<>();
        ManjalpurPoliceMen.add("Policewala kaka");

        final ArrayList<String> oldCityPoliceMen = new ArrayList<>();
        oldCityPoliceMen.add("S.P.Shinchan");
        oldCityPoliceMen.add("Officer Jenny");

        ArrayAdapter policeStationsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, policeStationsArrayList);
        policeStationsSpinner.setAdapter(policeStationsAdapter);

        final ArrayAdapter policeMenArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, policemenArrayList);
        policeMenSpinner.setAdapter(policeMenArrayAdapter);

        /*policeStationsSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        */
        policeStationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        policeMenArrayAdapter.clear();
                        policeMenArrayAdapter.addAll(oldCityPoliceMen);
                        policeMenArrayAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        policeMenArrayAdapter.clear();
                        policeMenArrayAdapter.addAll(ManjalpurPoliceMen);
                        policeMenArrayAdapter.notifyDataSetChanged();
                        break;
                    default:
                        policeMenArrayAdapter.clear();
                        policeMenArrayAdapter.addAll(policemenArrayList);
                        policeMenArrayAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
