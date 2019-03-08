package com.svit.epolice.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.svit.epolice.Models.Complaint;
import com.svit.epolice.R;
import com.svit.epolice.activities.ComplaintDetailsActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComplaintsAdapter extends FirebaseRecyclerAdapter<Complaint, ComplaintsAdapter.ComplaintsViewHolder> {

    Context mContext;



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public ComplaintsAdapter(@NonNull FirebaseRecyclerOptions<Complaint> options, Context context) {
        super(options);
        this.mContext = context;
    }
    /*public ComplaintsAdapter(Class<Complaint> modelClass, int modelLayout, Class<ComplaintsHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;

    }*/

    @Override
    protected void onBindViewHolder(@NonNull ComplaintsViewHolder complaintsViewHolder, int position, @NonNull final Complaint model) {
        complaintsViewHolder.complaintName.setText(""+model.getUsername());
        complaintsViewHolder.complaintContent.setText(model.getDescription());
        Glide.with(mContext).load(model.getMedia()).circleCrop().into(complaintsViewHolder.complaintImage);

        complaintsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ComplaintDetailsActivity.class);
                intent.putExtra("complaint", model);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ComplaintsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_complaint, viewGroup, false);
        return new ComplaintsViewHolder(view);
    }

    class ComplaintsViewHolder extends RecyclerView.ViewHolder {

        public ImageView complaintImage;
        public TextView complaintName, complaintContent;

        public ComplaintsViewHolder(@NonNull View itemView) {
            super(itemView);
            complaintImage = itemView.findViewById(R.id.complaintIV);
            complaintName = itemView.findViewById(R.id.complaintNameTV);
            complaintContent = itemView.findViewById(R.id.complaintContentET);
        }
    }
}

