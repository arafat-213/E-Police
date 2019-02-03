package com.svit.epolice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svit.epolice.Models.Notification;
import com.svit.epolice.R;
import com.svit.epolice.adapters.NotificationAdapter;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Notification> notificationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mRecyclerView = findViewById(R.id.notificationRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        notificationArrayList = new ArrayList<Notification>();

        for (int i = 0; i < 10; i++)
            notificationArrayList.add(new Notification("Whaat aibsvj asd vs", " Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
//        No policemanAdapter = new PolicemanAdapter(policemanArrayList);
//        policeRecyclerView.setAdapter(policemanAdapter);
        NotificationAdapter notificationAdapter = new NotificationAdapter(notificationArrayList);
        mRecyclerView.setAdapter(notificationAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
