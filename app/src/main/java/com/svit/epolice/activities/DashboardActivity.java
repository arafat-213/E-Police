package com.svit.epolice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.svit.epolice.Models.DashboardModule;
import com.svit.epolice.R;
import com.svit.epolice.adapters.DashboardModuleAdapter;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DashboardModule> arrayList;
    private DashboardModule dashboardModule;
    FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        arrayList.add(new DashboardModule("Know policemen", R.drawable.icon_policeman2));
        arrayList.add(new DashboardModule("File a complaint", R.drawable.icon_complaint));
        arrayList.add(new DashboardModule("Nearby stations", R.drawable.icon_police_station));
        arrayList.add(new DashboardModule("Request patrolling", R.drawable.icon_request));
        arrayList.add(new DashboardModule("Notifications", R.drawable.icon_notification));
        arrayList.add(new DashboardModule("Give activity_feedback", R.drawable.feedback));
        arrayList.add(new DashboardModule("Connect with us", R.drawable.icon_connect));
//        for (int i = 0; i < 4; i++) {
//            dashboardModule = new DashboardModule();
//            dashboardModule.setIcon(R.drawable.myicon);
//            dashboardModule.setName("Name " + (i + 1));
//            arrayList.add(dashboardModule);
//        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        DashboardModuleAdapter dashboardModuleAdapter = new DashboardModuleAdapter(arrayList);
        recyclerView.setAdapter(dashboardModuleAdapter);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mCurrentUser == null) {
            Toast.makeText(
                    DashboardActivity.this,
                    "Ye kaisan hua",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.optionSignout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(DashboardActivity.this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (id == R.id.optionEditProfile) {
            Intent intent = new Intent(DashboardActivity.this, ProfileDetailsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}