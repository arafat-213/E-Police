package com.svit.epolice.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.svit.epolice.Models.User;
import com.svit.epolice.R;
import com.svit.epolice.utilities.DataValidation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ProfileDetailsActivity.class.getName();
    private static final int PICK_IMAGE_REQUEST = 1;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    EditText emailET, nameET, phoneNoET, aadharNoET, dobET;
    ImageView profilePicIV;
    Button btnSubmit;
    FirebaseDatabase mDatabase;
    DatabaseReference mUserRef;
    ProgressBar mProgressBar;
    StorageReference mStorageRef;
    StorageTask mUploadTask;
    private Uri mImageUri;
    String oldImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        findViewById();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUserRef = mDatabase.getReference("users");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("users/");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        loadUserProfile();

    }

    private void findViewById() {
        emailET = findViewById(R.id.emailET);
        emailET.setEnabled(false);
        nameET = findViewById(R.id.nameET);
        phoneNoET = findViewById(R.id.phoneNoET);
        btnSubmit = findViewById(R.id.submitButton);
        aadharNoET = findViewById(R.id.aadharNoET);
        dobET = findViewById(R.id.dobET);
        mProgressBar = findViewById(R.id.profileDetailsPB);
        mProgressBar.setVisibility(View.INVISIBLE);
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
                mProgressBar.setVisibility(View.VISIBLE);
                String email = mFirebaseUser.getEmail();
                String name = "N.A.";
                String phone = "N.A.";

                if (!DataValidation.isEmpty(nameET.getText().toString()))
                    name = nameET.getText().toString();
                else
                    nameET.setError("Inavalid name");
                Log.d("ProfileDetailsActivity", name);

                if (DataValidation.isValidPhone(phoneNoET.getText().toString()))
                    phone = phoneNoET.getText().toString();
                else
                    phoneNoET.setError("Inavalid phone");
                Log.d("ProfileDetailsActivity", phone);


                String aadharNo = aadharNoET.getText().toString();
                String dob = dobET.getText().toString();
                // String profilePic = mImageUri.toString();
                User user = new User(name, phone, email, oldImage, aadharNo, dob);
                uploadImage(user);
                break;


            case R.id.profilePicIV:
                openFileChooser();
                break;
        }
    }

    public void addUser(User user) {
        mUserRef.child(mFirebaseUser.getUid()).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(
                                    ProfileDetailsActivity.this,
                                    DashboardActivity.class
                            );
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Something went wrong",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Glide.with(getApplicationContext())
                    .load(mImageUri)
                    .thumbnail(0.25f)
                    .circleCrop()
                    .into(profilePicIV);
            //profilePicIV.setImageURI(mImageUri);
        }
    }

    public void loadUserProfile() {
        /*
        ----------Firebase Query --------
        orderByKey() is used when when we want to search user by their unique "Key"
         */
        mProgressBar.setVisibility(View.VISIBLE);
        Query query1 = mUserRef
                .orderByKey()
                .equalTo(mFirebaseUser.getUid());

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    User user = singleSnapshot.getValue(User.class);
                    Log.d(TAG, "onDataChange: Query method - found user: " + user.toString());
                    //user is here. Now show name and phone no data into widgets
                    nameET.setText(user.getName());
                    emailET.setText(user.getEmail());
                    phoneNoET.setText(user.getMobileNumber());
                    dobET.setText(user.getDob());
                    aadharNoET.setText(user.getAadhar_no());
                    oldImage = user.getProfile_pic_url();
                    // mImageUri = Uri.parse(user.getProfile_pic_url());
                    Glide.with(getApplicationContext())
                            .load(user.getProfile_pic_url())
                            .circleCrop()
                            .into(profilePicIV);
                }
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void uploadImage(final User user) {
        if (mImageUri != null) {
            final StorageReference fileRef = mStorageRef.child(System.currentTimeMillis() + "");
            mUploadTask = fileRef.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    user.setProfile_pic_url(uri.toString());
                                    addUser(user);
                                }
                            });
                        }
                    });
        } else {
            addUser(user);
        }
    }
}
