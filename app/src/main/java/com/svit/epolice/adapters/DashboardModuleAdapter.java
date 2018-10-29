package com.svit.epolice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svit.epolice.Models.DashboardModule;
import com.svit.epolice.R;

import java.util.ArrayList;

public class DashboardModuleAdapter extends RecyclerView.Adapter<DashboardModuleAdapter.DashboardModuleViewHolder> {

    private ArrayList<DashboardModule> dashboardModuleArrayList;

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
    public void onBindViewHolder(@NonNull DashboardModuleViewHolder dashboardModuleViewHolder, int i) {
        dashboardModuleViewHolder.icon.setImageResource(dashboardModuleArrayList.get(i).getIcon());
        dashboardModuleViewHolder.name.setText(dashboardModuleArrayList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return dashboardModuleArrayList.size();
    }

    public class DashboardModuleViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView name;


        public DashboardModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
        }

    }
}
