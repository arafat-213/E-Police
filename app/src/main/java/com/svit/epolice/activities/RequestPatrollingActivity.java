package com.svit.epolice.activities;

import android.app.DatePickerDialog;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.svit.epolice.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RequestPatrollingActivity extends AppCompatActivity {

    DatePickerDialog fromDatePickerDialog, toDatePickerDialog;
    TextView fromDateTV, toDateTV;
    Calendar date = Calendar.getInstance();
    private Spinner areaSpinner;
    private ArrayList<String> policeStationsArrayList;
    private String TAG = "RequestPatrolling";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_patrolling);
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

    }
}
