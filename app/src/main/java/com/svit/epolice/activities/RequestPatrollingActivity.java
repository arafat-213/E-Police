package com.svit.epolice.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import java.util.ArrayList;
import java.util.Calendar;

public class RequestPatrollingActivity extends AppCompatActivity implements View.OnClickListener {

    DatePickerDialog fromDatePickerDialog, toDatePickerDialog;
    TextView fromDateTV, toDateTV;
    Calendar date = Calendar.getInstance();
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



        /*setContentView(R.layout.activity_request_patrolling);
        areaSpinner = findViewById(R.id.requestPatrolSpinnerArea);
        fromDateTV = findViewById(R.id.fromDateTV);
        toDateTV = findViewById(R.id.toDateTV);
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        displayTodaysDate(day, month, year);
        Log.e(TAG, "Year: " + year);
        DatePickerDialog.OnDateSetListener fromDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                Toast.makeText(this, "Year: "+year+" Month: "+month+" ", Toast.LENGTH_SHORT).show();
                fromDateTV.setText(day + "/" + (month + 1) + "/" + year);
                Calendar fromDate = Calendar.getInstance();
                fromDate.set(year, month, day);
                toDatePickerDialog.getDatePicker().setMinDate(fromDate.getTimeInMillis());
            }
        };


        DatePickerDialog.OnDateSetListener toDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                Toast.makeText(this, "Year: "+year+" Month: "+month+" ", Toast.LENGTH_SHORT).show();
                Calendar toDate = Calendar.getInstance();
                toDate.set(year, month, day);
                fromDatePickerDialog.getDatePicker().setMaxDate(toDate.getTimeInMillis());
                toDateTV.setText(day + "/" + (month + 1) + "/" + year);
            }
        };


        fromDatePickerDialog = new DatePickerDialog(this, fromDateSetListener, year, month, day);
        // Set today's date as Min FROM-date
        fromDatePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

        toDatePickerDialog = new DatePickerDialog(this, toDateSetListener, year, month, day + 1);
        // Set today's date as Min TO-date
        toDatePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());


        policeStationsArrayList = new ArrayList<>();
        policeStationsArrayList.add("Old City");
        policeStationsArrayList.add("Karelibaug");
        policeStationsArrayList.add("Manjalpur");
        policeStationsArrayList.add("Gowtree");
        policeStationsArrayList.add("Sayajigunj");

        ArrayAdapter policeStationsAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                policeStationsArrayList);
        areaSpinner.setAdapter(policeStationsAdapter);

        fromDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDatePickerDialog.show();
            }
        });

        toDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDatePickerDialog.show();
            }
        });
    }

    private void displayTodaysDate(int day, int month, int year) {
        fromDateTV.setText(day + "/" + (month + 1) + "/" + year);
        toDateTV.setText(day + 1 + "/" + (month + 1) + "/" + year);
    }
    protected void pickADate(View view) {

    }*/
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
        areaSpinner = findViewById(R.id.requestAreaSpinner);
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
                String name = "user";
                String phone = "000";
                String area = "vadodara";
                String address = "N.A.";

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
}
