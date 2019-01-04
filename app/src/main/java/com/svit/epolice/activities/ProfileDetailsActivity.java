package com.svit.epolice.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.svit.epolice.Models.User;
import com.svit.epolice.R;

public class ProfileDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ProfileDetailsActivity.class.getName();
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    EditText emailET, nameET, phoneNoET, aadharNoET, dobET;
    Button btnSubmit;
    FirebaseDatabase database;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        findViewById();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

        firebaseUser = auth.getCurrentUser()/*(FirebaseUser) getIntent().getSerializableExtra("firebase_user")*/;
        emailET.setText(firebaseUser.getEmail());


    }

    private void findViewById() {
        emailET = findViewById(R.id.emailET);
        nameET = findViewById(R.id.nameET);
        phoneNoET = findViewById(R.id.phoneNoET);
        btnSubmit = findViewById(R.id.submitButton);
        aadharNoET = findViewById(R.id.aadharNoET);
        dobET = findViewById(R.id.dobET);
        btnSubmit.setOnClickListener(this);
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

                userRef.child(firebaseUser.getUid()).setValue(new User(name, phone, email, profilePic, aadharNo, dob));

                Intent intent = new Intent(
                        ProfileDetailsActivity.this,
                        DashboardActivity.class
                );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }


    }
}
