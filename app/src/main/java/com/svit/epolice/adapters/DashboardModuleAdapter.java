package com.svit.epolice.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.svit.epolice.Models.DashboardModule;
import com.svit.epolice.R;
import com.svit.epolice.activities.ComplaintActivity;
import com.svit.epolice.activities.FeedbackActivity;
import com.svit.epolice.activities.NotificationActivity;
import com.svit.epolice.activities.PolicemenListActivity;
import com.svit.epolice.activities.RequestPatrollingActivity;
import com.svit.epolice.activities.TwitterActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardModuleAdapter extends RecyclerView.Adapter<DashboardModuleAdapter.DashboardModuleViewHolder> {

    private ArrayList<DashboardModule> dashboardModuleArrayList;
    private Context mContext;
    public DashboardModuleAdapter(ArrayList<DashboardModule> dashboardModuleArrayList) {
        this.dashboardModuleArrayList = dashboardModuleArrayList;
    }


    @NonNull
    @Override
    public DashboardModuleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_option, viewGroup, false);
        return new DashboardModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DashboardModuleViewHolder dashboardModuleViewHolder, int i) {
        dashboardModuleViewHolder.icon.setImageResource(dashboardModuleArrayList.get(i).getIcon());
        dashboardModuleViewHolder.name.setText(dashboardModuleArrayList.get(i).getName());

        dashboardModuleViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = dashboardModuleViewHolder.getAdapterPosition();
                mContext = view.getContext();
                Intent intent;
                switch (id) {
                    case 0:
                        intent = new Intent(view.getContext(), PolicemenListActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), ComplaintActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(mContext, dashboardModuleArrayList.get(id).getName() + " feature not added yet", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(view.getContext(), PolicemenListActivity.class);
//                        view.getContext().startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), RequestPatrollingActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(view.getContext(), NotificationActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(mContext, FeedbackActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(view.getContext(), TwitterActivity.class);
                        view.getContext().startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardModuleArrayList.size();
    }

    public class DashboardModuleViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView name;
        public RelativeLayout parentLayout;


        public DashboardModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }

    }
}
