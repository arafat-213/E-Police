package com.svit.epolice.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.svit.epolice.Models.Notification;
import com.svit.epolice.R;
import com.svit.epolice.adapters.NotificationAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    NotificationAdapter notificationAdapter;
    ProgressBar notificationPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mRecyclerView = findViewById(R.id.notificationRV);
        notificationPB = findViewById(R.id.notificationPB);
        notificationPB.setVisibility(View.VISIBLE);
        FirebaseApp.initializeApp(this);

        Query notificationRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("notifications");


        FirebaseRecyclerOptions<Notification> options =
                new FirebaseRecyclerOptions.Builder<Notification>()
                        .setQuery(notificationRef, Notification.class)
                        .build();
        notificationAdapter = new NotificationAdapter(options, getApplicationContext()) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                notificationPB.setVisibility(View.INVISIBLE);
            }
        };
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(notificationAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        notificationAdapter.startListening();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
