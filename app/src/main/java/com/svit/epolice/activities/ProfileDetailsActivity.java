package com.svit.epolice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.svit.epolice.Models.User;
import com.svit.epolice.R;

public class ProfileDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ProfileDetailsActivity.class.getName();
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    EditText emailET, nameET, phoneNoET, aadharNoET, dobET;
    ImageView profilePicIV;
    Button btnSubmit;
    FirebaseDatabase mDatabase;
    DatabaseReference mUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        findViewById();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUserRef = mDatabase.getReference("users");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mFirebaseUser = mFirebaseAuth.getCurrentUser();/*(FirebaseUser) getIntent().getSerializableExtra("firebase_user")*/
        emailET.setText(mFirebaseUser.getEmail());


    }

    private void findViewById() {
        emailET = findViewById(R.id.emailET);
        nameET = findViewById(R.id.nameET);
        phoneNoET = findViewById(R.id.phoneNoET);
        btnSubmit = findViewById(R.id.submitButton);
        aadharNoET = findViewById(R.id.aadharNoET);
        dobET = findViewById(R.id.dobET);
        profilePicIV = findViewById(R.id.profilePicIV);

        btnSubmit.setOnClickListener(this);
        profilePicIV.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.submitButton:

                Log.d(TAG, "onClick: SUBMIT CALLED");

                String email = emailET.getText().toString();
                String name = nameET.getText().toString();
                String phone = phoneNoET.getText().toString();
                String aadharNo = aadharNoET.getText().toString();
                String dob = dobET.getText().toString();
                String profilePic = "Not implemented yet";

                mUserRef.child(mFirebaseUser.getUid()).setValue(new User(name, phone, email, profilePic, aadharNo, dob));

                Intent intent = new Intent(
                        ProfileDetailsActivity.this,
                        DashboardActivity.class
                );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.profilePicIV:
                break;
        }
    }
}
