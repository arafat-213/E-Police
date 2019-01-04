package com.svit.epolice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.svit.epolice.R;

import java.util.regex.Pattern;

public class UserRegistrationActivity extends AppCompatActivity {


    TextView signIntv;
    FirebaseUser mCurrentUser;
    ProgressBar progressBar;
    private FirebaseAuth mFirebaseAuth;
    private Button submitButton;
    private EditText emailET, passwordET;
    private String TAG = "UserRegistration";

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();

        submitButton = findViewById(R.id.submitButton);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        progressBar = findViewById(R.id.progressbar);
        signIntv = findViewById(R.id.signInTV);
        signIntv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRegistrationActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    if (isEmailValid(email)) {
                        Log.e(TAG, "Valid email");
                        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    if (progressBar.getVisibility() == View.VISIBLE) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }

                                    Log.e(TAG, "Task successful");
                                    Intent intent = new Intent(UserRegistrationActivity.this, SignInActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {

                                    if (progressBar.getVisibility() == View.VISIBLE) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }

                                    Log.e(TAG, "Task unsuccessful");
                                    Toast.makeText(getApplicationContext(), "Failed to sign up", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Field Can Not Be Empty.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mCurrentUser = mFirebaseAuth.getCurrentUser();
    }
}
