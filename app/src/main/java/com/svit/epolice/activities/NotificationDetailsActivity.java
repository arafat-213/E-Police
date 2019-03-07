package com.svit.epolice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.svit.epolice.Models.Notification;
import com.svit.epolice.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationDetailsActivity extends AppCompatActivity {

    private static final String TAG = "NotificationDetails";

    ImageView notificationIV;
    TextView notificationTypeTV, notificationContentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        Intent intent = getIntent();
        final Notification notification = (Notification) intent.getSerializableExtra("Notification");
        init();
        Log.e(TAG, "onCreate: " + notification);
        loadNotification(notification);

        notificationIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageIntent = new Intent(getApplicationContext(), SingleImageActivity.class);
                imageIntent.putExtra("image-url", notification.getNotificationImage());
                startActivity(imageIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    public void init() {
        notificationIV = findViewById(R.id.notificationImage);
        notificationContentTV = findViewById(R.id.notificationContentTV);
        notificationTypeTV = findViewById(R.id.notficationTypeTV);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void loadNotification(Notification notification) {
        notificationTypeTV.setText(notification.getNotificationType());
        notificationContentTV.setText(notification.getNotificationContent());
        String url = notification.getNotificationImage();
        // if(!url.equals(""))
        Glide.with(
                getApplicationContext())
                .load(notification.getNotificationImage())
                .into(notificationIV)
        ;
        // Glide.with(getApplicationContext()).load(notification.getNotificationImage()).circleCrop().into(notificationIV);
        Log.d(TAG, "loadNotification: " + url);
    }

}
