package com.svit.epolice.activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.svit.epolice.dialogs.PasswordResetDialog;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseUser mCurrentUser;
    ProgressBar progressBar;
    private FirebaseAuth mFirebaseAuth;
    private Button signInButton;
    private EditText emailET, passwordET;
    TextView signUpTV;
    TextView forgotPasswordTV;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "UserRegistration";

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        checkFirebaseAuth();
        mFirebaseAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        progressBar = findViewById(R.id.requestPB);

        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        signUpTV = findViewById(R.id.signUpTV);
        signUpTV.setOnClickListener(this);

        forgotPasswordTV = findViewById(R.id.forgotPasswordTV);
        forgotPasswordTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.signInButton:

                progressBar.setVisibility(View.VISIBLE);

                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    if (isEmailValid(email)) {
                        signInWithEmailAndPassword(email, password);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Field Can Not Be Empty.", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.signUpTV:
                Intent intent = new Intent(SignInActivity.this, UserRegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.forgotPasswordTV:
                PasswordResetDialog passwordResetDialog = new PasswordResetDialog();
                passwordResetDialog.show(getSupportFragmentManager(), "dialog_password_reset");
                break;
        }

    }

    private void signInWithEmailAndPassword(String email, String password) {

        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Intent intent = new Intent(SignInActivity.this, ProfileDetailsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //intent.putExtra("firebase_user", user);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    public void checkFirebaseAuth() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is already signed in.. Redirecting to dashboard activity
                    Toast.makeText(getApplicationContext(), "Authenticate with: " + user.getEmail(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null)
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
    }
}
