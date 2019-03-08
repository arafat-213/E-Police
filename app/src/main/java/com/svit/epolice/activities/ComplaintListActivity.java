package com.svit.epolice.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.svit.epolice.Models.Complaint;
import com.svit.epolice.R;
import com.svit.epolice.adapters.ComplaintsAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ComplaintListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ComplaintsAdapter complaintsAdapter;
    ProgressBar mProgressBar;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);

        init();


//        for (int i=0; i<10; i++)
//            complaintArrayList.add(new Complaint("TYPE: ANONYMOUS OR KNOWN IDENTITY" , " Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
//        No policemanAdapter = new PolicemanAdapter(policemanArrayList);
//        policeRecyclerView.setAdapter(policemanAdapter);
//        ComplaintAdapter complaintAdapter = new ComplaintAdapter(complaintArrayList);
//        mRecyclerView.setAdapter(complaintAdapter);


    }

    public void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mRecyclerView = findViewById(R.id.complaintRV);
        mProgressBar = findViewById(R.id.complaintPB);
        mProgressBar.setVisibility(View.VISIBLE);
        FirebaseApp.initializeApp(this);

        Query complainstRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("complaints");


        FirebaseRecyclerOptions<Complaint> options =
                new FirebaseRecyclerOptions.Builder<Complaint>()
                        .setQuery(complainstRef, Complaint.class)
                        .build();

        complaintsAdapter = new ComplaintsAdapter(options,getApplicationContext()) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
        layoutManager = new LinearLayoutManager(this);


        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(complaintsAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        complaintsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        complaintsAdapter.stopListening();
    }
}

