package com.svit.epolice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.svit.epolice.Models.Complaint;
import com.svit.epolice.R;

public class ComplaintActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ComplaintActivity";
    FirebaseDatabase mDatabase;
    DatabaseReference mComplaintRef;
    EditText addressET, descriptionET;
    Spinner areaSpinner;
    CheckBox isAnonymousCB;
    Button submitBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        findViewByID();

        mDatabase = FirebaseDatabase.getInstance();
        mComplaintRef = mDatabase.getReference("complaints");
    }


    private void findViewByID() {
        addressET = findViewById(R.id.addressET);
        descriptionET = findViewById(R.id.descriptionET);
        areaSpinner = findViewById(R.id.areaSpinner);
        isAnonymousCB = findViewById(R.id.isAnonymousCB);
        submitBTN = findViewById(R.id.submitBTN);

        submitBTN.setOnClickListener(this);
        isAnonymousCB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitBTN:
                String address = addressET.getText().toString();
                String desciption = descriptionET.getText().toString();
                Boolean isAnonymous = isAnonymousCB.isChecked();
                String area = "Vadodara";

                String key = mComplaintRef.push().getKey();
                mComplaintRef.child(key).setValue(new Complaint(null, area, address, desciption, isAnonymous));
                Log.d(TAG, "Data added");
        }
    }
}
