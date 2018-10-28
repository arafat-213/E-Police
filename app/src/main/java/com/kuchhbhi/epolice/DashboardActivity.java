package com.kuchhbhi.epolice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Option> arrayList;
    private Option option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        for (int i = 0; i < 8; i++) {
            option = new Option();
            option.setIcon(R.drawable.myicon);
            option.setName("Name " + (i + 1));
            arrayList.add(option);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        OptionAdapter optionAdapter = new OptionAdapter(arrayList);
        recyclerView.setAdapter(optionAdapter);
    }
}