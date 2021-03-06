package com.svit.epolice.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.svit.epolice.Models.Policeman;
import com.svit.epolice.R;
import com.svit.epolice.adapters.PolicemanAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PolicemenListActivity extends AppCompatActivity {

    private RecyclerView policeRecyclerView;
    private PolicemanAdapter mPolicemanAdapter;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policemen_list);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        init();
    }

    public void init() {
        policeRecyclerView = findViewById(R.id.policeRecyclerView);
        mProgressBar = findViewById(R.id.policemenListPB);
        mProgressBar.setVisibility(View.VISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        FirebaseApp.initializeApp(this);

        Query policeListQuery = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("policemen");

        FirebaseRecyclerOptions<Policeman> options =
                new FirebaseRecyclerOptions.Builder<Policeman>()
                        .setQuery(policeListQuery, Policeman.class)
                        .build();

        mPolicemanAdapter = new PolicemanAdapter(options,getApplicationContext()) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // To display the data in reverse order ie. Latest entries first
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        policeRecyclerView.setLayoutManager(layoutManager);
        policeRecyclerView.setAdapter(mPolicemanAdapter);
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

    @Override
    protected void onStart() {
        super.onStart();
        mPolicemanAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPolicemanAdapter.stopListening();
    }
}


