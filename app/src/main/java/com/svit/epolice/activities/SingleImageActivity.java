package com.svit.epolice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.svit.epolice.R;

import androidx.appcompat.app.AppCompatActivity;

public class SingleImageActivity extends AppCompatActivity {

    ImageView singleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_arrow_back_black_24dp);

        singleImage = findViewById(R.id.singleImageIV);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("image-url");
        Glide.with(getApplicationContext())
                .load(imageUrl)
                //.fitCenter()
                .into(singleImage);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
