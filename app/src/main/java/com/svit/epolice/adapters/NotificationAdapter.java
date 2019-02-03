package com.svit.epolice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svit.epolice.Models.Notification;
import com.svit.epolice.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private ArrayList<Notification> notificationArrayList;
    private Context context;

    public NotificationAdapter(ArrayList<Notification> policemanArrayList) {
        this.notificationArrayList = policemanArrayList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_notification, viewGroup, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int i) {
        notificationViewHolder.notifyType.setText(notificationArrayList.get(i).getNotificationType());
        notificationViewHolder.notifyConent.setText(notificationArrayList.get(i).getNotificationContent());
        // notificationViewHolder.notifyImage.setImageResource(notificationArrayList.get(i).getNotificationImage());
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        public ImageView notifyImage;
        public TextView notifyType, notifyConent;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notifyImage = itemView.findViewById(R.id.notifyIV);
            notifyType = itemView.findViewById(R.id.notifyTypeTV);
            notifyConent = itemView.findViewById(R.id.notifyContentTV);

        }
    }
}
