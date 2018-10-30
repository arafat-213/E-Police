package com.svit.epolice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.svit.epolice.Models.DashboardModule;
import com.svit.epolice.adapters.DashboardModuleAdapter;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DashboardModule> arrayList;
    private DashboardModule dashboardModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        arrayList.add(new DashboardModule("Know policemen", R.drawable.icon_policeman2));
        arrayList.add(new DashboardModule("File a complaint", R.drawable.icon_complaint));
        arrayList.add(new DashboardModule("Nearby stations", R.drawable.icon_police_station));
        arrayList.add(new DashboardModule("Request patrolling", R.drawable.icon_request));
        arrayList.add(new DashboardModule("Notifications", R.drawable.icon_notification));
        arrayList.add(new DashboardModule("Give feedback", R.drawable.feedback));
        arrayList.add(new DashboardModule("Connect with us", R.drawable.icon_connect));
//        for (int i = 0; i < 4; i++) {
//            dashboardModule = new DashboardModule();
//            dashboardModule.setIcon(R.drawable.myicon);
//            dashboardModule.setName("Name " + (i + 1));
//            arrayList.add(dashboardModule);
//        }


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        DashboardModuleAdapter dashboardModuleAdapter = new DashboardModuleAdapter(arrayList);
        recyclerView.setAdapter(dashboardModuleAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.optionSignout) {

        }
        return super.onOptionsItemSelected(item);
    }
}