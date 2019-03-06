package com.svit.epolice.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.svit.epolice.Models.PatrollingRequest;
import com.svit.epolice.R;
import com.svit.epolice.adapters.RequestsAdapter;

public class RequestListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RequestsAdapter mRequestsAdapter;
    ProgressBar mProgressBar;
    FirebaseUser mCurrentUser;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        init();

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRequestsAdapter);
    }

    public void init() {
        mRecyclerView = findViewById(R.id.requestRecyclerView);
        mProgressBar = findViewById(R.id.requestPB);
        mProgressBar.setVisibility(View.VISIBLE);
        FirebaseApp.initializeApp(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = mCurrentUser.getUid();

        Query requestsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("requests").orderByChild("uid").equalTo(userID);


        FirebaseRecyclerOptions<PatrollingRequest> options =
                new FirebaseRecyclerOptions.Builder<PatrollingRequest>()
                        .setQuery(requestsRef, PatrollingRequest.class)
                        .build();

        layoutManager = new LinearLayoutManager(this);
        mRequestsAdapter = new RequestsAdapter(options) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRequestsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRequestsAdapter.stopListening();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
