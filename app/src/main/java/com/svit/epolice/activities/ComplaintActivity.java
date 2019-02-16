package com.svit.epolice.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        init();
    }


    private void init() {
        addressET = findViewById(R.id.addressET);
        descriptionET = findViewById(R.id.descriptionET);
        areaSpinner = findViewById(R.id.areaSpinner);
        isAnonymousCB = findViewById(R.id.isAnonymousCB);
        mProgressBar = findViewById(R.id.complaintPB);
        submitBTN = findViewById(R.id.submitBTN);

        submitBTN.setOnClickListener(this);
        isAnonymousCB.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mDatabase = FirebaseDatabase.getInstance();
        mComplaintRef = mDatabase.getReference("complaints");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitBTN:
                mProgressBar.setVisibility(View.VISIBLE);
                String address = addressET.getText().toString();
                String desciption = descriptionET.getText().toString();
                Boolean isAnonymous = isAnonymousCB.isChecked();
                String area = "Vadodara";
                Complaint complaint = new Complaint(null, area, address, desciption, isAnonymous);
                sendComplaint(complaint);
                break;
        }
    }

    public void sendComplaint(Complaint complaint) {
        String key = mComplaintRef.push().getKey();
        mComplaintRef.child(key).setValue(complaint)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext()
                                    , "Complaint sent"
                                    , Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext()
                                    , "Failed to send complaint"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
