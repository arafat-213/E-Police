package com.svit.epolice.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.svit.epolice.Models.Complaint;
import com.svit.epolice.R;
import com.svit.epolice.utilities.SpinnerData;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ComplaintActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int PICK_IMAGE_REQUEST=1;

    private static final String TAG = "ComplaintActivity";
    DatabaseReference mComplaintRef;
    StorageReference mStorageRef,fileRef;
    Uri mImageUri;
    StorageTask mUploadTask;
    String username;

    EditText addressET, descriptionET;
    Spinner areaSpinner;
    CheckBox isAnonymousCB;
    Button submitBTN;
    ProgressBar mProgressBar;
    ImageView complaintIV;
    FirebaseUser mCurrentUser;
    String mKey;
    private ArrayList<String> stationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        init();
        setupSpinner();
    }


    private void init() {
        addressET = findViewById(R.id.addressET);
        descriptionET = findViewById(R.id.descriptionET);
        isAnonymousCB = findViewById(R.id.isAnonymousCB);

        mProgressBar = findViewById(R.id.complaintPB);
        submitBTN = findViewById(R.id.submitBTN);
        complaintIV=findViewById(R.id.complaintIV);

        complaintIV.setOnClickListener(this);
        submitBTN.setOnClickListener(this);
        isAnonymousCB.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        mStorageRef= FirebaseStorage.getInstance().getReference().child("complaints/");
        mComplaintRef = FirebaseDatabase.getInstance().getReference().child("complaints/");
        fileRef = mStorageRef.child(System.currentTimeMillis()+"");
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mKey = mCurrentUser.getUid();
        username = mCurrentUser.getDisplayName();
        Log.e(TAG, "init: " + username);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.complaintIV:
                openFileChooser();
                break;
            case R.id.submitBTN:
                mProgressBar.setVisibility(View.VISIBLE);
                username = "Anonymous";
                if(!isAnonymousCB.isChecked()){
                    username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                }
                Complaint complaint = new Complaint("", areaSpinner.getSelectedItem().toString(),
                        addressET.getText().toString(), descriptionET.getText().toString(),
                        username, mKey);
                uploadImage(complaint);
                break;
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void setupSpinner()
    {
        areaSpinner = findViewById(R.id.areaSpinner);
        SpinnerData spinnerData = new SpinnerData();
        stationArrayList = spinnerData.getStationList();
        ArrayAdapter areaAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,stationArrayList);
        areaSpinner.setAdapter(areaAdapter);
    }

    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();
            // complaintIV.setImageURI(mImageUri);
            Glide.with(this)
                    .load(mImageUri)
                    .thumbnail(0.25f)
                    .circleCrop()
                    .into(complaintIV);
        }
    }

    public void sendComplaint(Complaint complaint) {
        String key = mComplaintRef.push().getKey();
        mComplaintRef.child(key).setValue(complaint)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(ComplaintActivity.this, "Complaint Uploaded Successfully",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    public void uploadImage(final Complaint complaint) {
        if(mImageUri!= null){
            // Image is selected
            final StorageReference fileRef = mStorageRef.child(System.currentTimeMillis()
                    +"");//+getFileExtension(mImageUri));
            mUploadTask = fileRef.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // var = fileRef.getDownloadUrl().getResult().toString();
                            Task<Uri> result= taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    complaint.setMedia(uri.toString());
                                    sendComplaint(complaint);
                                }
                            });

                        }
                    });
        }
        else{
            // No image file selected
            complaint.setMedia("");
            sendComplaint(complaint);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                Intent intent = new Intent(ComplaintActivity.this, ComplaintListActivity.class);
                intent.putExtra("key", mKey);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
