package com.svit.epolice.activities;

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
import com.svit.epolice.utilities.DateRangePickerDialog;
import com.svit.epolice.utilities.SpinnerData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RequestPatrollingActivity extends AppCompatActivity implements View.OnClickListener, DateRangePickerDialog.OnInputListener {

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
    private static final String PATTERN = "dd-MM-yyyy";
    Calendar mStartDate, mEndDate;
    String fromDate;
    String toDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_patrolling);
        init();
        setupSpinner();
    }

    public void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);
        submitBTN = findViewById(R.id.requestSubmitBTN);
        submitBTN.setOnClickListener(this);
        fromDateTV = findViewById(R.id.requestFromDateTV);
        fromDateTV.setText(new SimpleDateFormat(PATTERN).format(Calendar.getInstance().getTime()));
        fromDateTV.setOnClickListener(this);
        toDateTV = findViewById(R.id.requestToDateTV);
        toDateTV.setOnClickListener(this);
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
            case R.id.requestFromDateTV:
                showDateRangePicker();
                break;
            case R.id.requestToDateTV:
                showDateRangePicker();
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

    public void showDateRangePicker() {
        DateRangePickerDialog dialog = new DateRangePickerDialog();
        dialog.show(getSupportFragmentManager(), TAG);
    }


    @Override
    public void sendInput(Calendar startDate, Calendar endDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        mStartDate = startDate;
        mEndDate = endDate;
        fromDateTV.setText(simpleDateFormat.format(startDate.getTime()));
        toDateTV.setText(simpleDateFormat.format(endDate.getTime()));
    }
}
