package com.svit.epolice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svit.epolice.Models.Policeman;
import com.svit.epolice.R;
import com.svit.epolice.adapters.PolicemanAdapter;

import java.util.ArrayList;

public class PolicemenListActivity extends AppCompatActivity {

    private RecyclerView policeRecyclerView;
    private ArrayList<Policeman> policemanArrayList;
    private Policeman policeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policemen_list);
        policeRecyclerView = findViewById(R.id.policeRecyclerView);
        policemanArrayList = new ArrayList<Policeman>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        for (int i = 0; i < 20; i++) {
            policeman = new Policeman();
            policeman.setImage_id(R.drawable.jack);
            policeman.setName("Jack Ryan");
            policeman.setRank("Commissioner of police");
            policeman.setArea("Vadodara city");
            policeman.setEmail("jack@amazon.in");
            policeman.setMobile_no("9876543210");
            policeman.setRating(3.7f);
            policemanArrayList.add(policeman);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        policeRecyclerView.setLayoutManager(layoutManager);

        PolicemanAdapter policemanAdapter = new PolicemanAdapter(policemanArrayList);
        policeRecyclerView.setAdapter(policemanAdapter);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}


