package com.svit.epolice.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.svit.epolice.Models.PatrollingRequest;
import com.svit.epolice.R;
import com.svit.epolice.utilities.SpinnerData;

import java.util.ArrayList;

public class RequestPatrollingActivity extends AppCompatActivity implements View.OnClickListener {

    DatePickerDialog fromDatePickerDialog, toDatePickerDialog;
    TextView fromDateTV, toDateTV;
    private Spinner areaSpinner;
    private ArrayList<String> policeStationsArrayList;
    private String TAG = "RequestPatrolling";
    ProgressBar mProgressBar;
    Button submitBTN;
    EditText nameET;
    EditText phoneET;
    EditText addressET;
    DatabaseReference mRequestsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_patrolling);
        init();
        setupSpinner();
    }

    public void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        submitBTN = findViewById(R.id.requestSubmitBTN);
        submitBTN.setOnClickListener(this);
        fromDateTV = findViewById(R.id.requestFromDateTV);
        toDateTV = findViewById(R.id.requestToDateTV);
        nameET = findViewById(R.id.requestNameET);
        phoneET = findViewById(R.id.requestPhoneET);
        addressET = findViewById(R.id.requestAddressET);
        mProgressBar = findViewById(R.id.requestPB);
        mRequestsRef = FirebaseDatabase.getInstance().getReference().child("requests");
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.requestSubmitBTN:
                mProgressBar.setVisibility(View.VISIBLE);
                String fromDate = "13/02/2018";
                String toDate = "16/02/2018";
                String name;
                String phone;
                String area = areaSpinner.getSelectedItem().toString();
                String address;

                name = nameET.getText().toString();
                phone = phoneET.getText().toString();
                address = phoneET.getText().toString();

                PatrollingRequest request = new PatrollingRequest(
                        fromDate, toDate, name, address, phone, area
                );
                submitRequest(request);
                break;
        }
    }

    public void submitRequest(PatrollingRequest request) {

        String key = mRequestsRef.push().getKey();
        mRequestsRef.child(key)
                .setValue(request)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mProgressBar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext()
                            , "Request sent"
                            , Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext()
                            , "Failed to send request"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setupSpinner() {
        areaSpinner = findViewById(R.id.requestAreaSpinner);
        SpinnerData spinnerData = new SpinnerData();
        policeStationsArrayList = spinnerData.getStationList();
        ArrayAdapter stationsAdapter = new ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                policeStationsArrayList
        );
        areaSpinner.setAdapter(stationsAdapter);
    }
}
