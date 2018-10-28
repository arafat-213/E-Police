package com.svit.epolice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svit.epolice.Models.Policeman;

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

        for (int i = 0; i < 20; i++) {
            policeman = new Policeman();
            policeman.setImage_id(R.drawable.jack);
            policeman.setName("Jack Ryan");
            policeman.setRank("Commissioner of police");
            policeman.setArea("Vadodara city");
            policeman.setEmail("jack.ryan@amazon.prime");
            policeman.setMobile_no("9876543210");
            policeman.setRating(4.2f);
            policemanArrayList.add(policeman);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        policeRecyclerView.setLayoutManager(layoutManager);

        PolicemanAdapter policemanAdapter = new PolicemanAdapter(policemanArrayList);
        policeRecyclerView.setAdapter(policemanAdapter);
    }
}
