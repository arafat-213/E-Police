package com.svit.epolice.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.svit.epolice.Models.Feedback;
import com.svit.epolice.Models.Policeman;
import com.svit.epolice.R;
import com.svit.epolice.utilities.SpinnerData;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> policeStationsArrayList;
    ArrayList<String> policemenArrayList;
    Spinner policeStationsSpinner, policeMenSpinner;
    ArrayAdapter policeStationsAdapter;
    ArrayAdapter policeMenArrayAdapter;
    ArrayList<String> ManjalpurPoliceMen;
    ArrayList<String> oldCityPoliceMen;
    String policemanID;
    CheckBox anonymousCB;
    FirebaseDatabase mDatabase;
    DatabaseReference mFeedbackRef, mPolicemenRef;
    RatingBar policeRatingBar;
    EditText descriptionET;
    Button submitBTN;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        init();
        setupSpinners();
    }

    public void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        anonymousCB = findViewById(R.id.anonymousCB);
        policeRatingBar = findViewById(R.id.policeRatingBar);
        descriptionET = findViewById(R.id.descriptionET);
        submitBTN = findViewById(R.id.submitBTN);
        submitBTN.setOnClickListener(this);
        mProgressBar = findViewById(R.id.feedbackPB);

        mDatabase = FirebaseDatabase.getInstance();
        mFeedbackRef = mDatabase.getReference("feedback");
        mPolicemenRef = mDatabase.getReference("policemen");
        policemenArrayList = new ArrayList<>();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitBTN:
                mProgressBar.setVisibility(View.VISIBLE);
                Feedback feedback;
                String username = "anonymous";

                if (!anonymousCB.isChecked()) {
                    username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                }

                feedback = new Feedback(
                        policeStationsSpinner.getSelectedItem().toString(),
                        policeMenSpinner.getSelectedItem().toString(),
                        policeRatingBar.getRating() + "",
                        descriptionET.getText().toString(),
                        username,
                        "ID HUN ME"
                );
                sendFeedback(feedback);
                break;
        }
    }

    public void sendFeedback(Feedback feedback) {
        String key = mFeedbackRef.push().getKey();
        mFeedbackRef.child(key).setValue(feedback)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Feedback sent",
                                    Toast.LENGTH_SHORT
                            ).show();
                            finish();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Failed to send feedback",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void setupSpinners() {
        SpinnerData spinnerData = new SpinnerData();

        policeStationsSpinner = findViewById(R.id.policeStationsSpinner);
        policeStationsArrayList = spinnerData.getStationList();
        policeStationsAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, policeStationsArrayList);
        policeStationsSpinner.setAdapter(policeStationsAdapter);
        policeMenArrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, policemenArrayList);
        policeStationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Query query = mPolicemenRef.orderByChild("area").equalTo(policeStationsSpinner.getSelectedItem().toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        policemenArrayList.clear();
                        for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                            Policeman policemanobj = singleSnapshot.getValue(Policeman.class);
                            policemanID = singleSnapshot.getKey();
                            policemenArrayList.add(policemanobj.getName());
                        }
                            policeMenArrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        policeMenSpinner = findViewById(R.id.policeMenSpinner);
        policeMenSpinner.setAdapter(policeMenArrayAdapter);


    }
}
