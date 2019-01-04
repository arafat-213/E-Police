package com.svit.epolice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.svit.epolice.Models.Policeman;
import com.svit.epolice.R;

import java.util.ArrayList;

public class PolicemanAdapter extends RecyclerView.Adapter<PolicemanAdapter.PolicemanViewHolder> {

    private ArrayList<Policeman> policemanArrayList;
    private Context context;

    public PolicemanAdapter(ArrayList<Policeman> policemanArrayList) {
        this.policemanArrayList = policemanArrayList;
    }

    @NonNull
    @Override
    public PolicemanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_policemen_list, viewGroup, false);
        return new PolicemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PolicemanViewHolder policemanViewHolder, int i) {
        policemanViewHolder.policemanImage.setImageResource(policemanArrayList.get(i).getImage_id());
        policemanViewHolder.policemanName.setText(policemanArrayList.get(i).getName());
        policemanViewHolder.policemanRank.setText(policemanArrayList.get(i).getRank());
        policemanViewHolder.policemanArea.setText(policemanArrayList.get(i).getArea());
        policemanViewHolder.policemanPhone.setText(policemanArrayList.get(i).getMobile_no());
        policemanViewHolder.policemanEmail.setText(policemanArrayList.get(i).getEmail());
        policemanViewHolder.policemanRatingTV.setText(policemanArrayList.get(i).getRating() + " ★");
//        policemanViewHolder.policemanRating.setRating(policemanArrayList.get(i).getRating());
    }

    @Override
    public int getItemCount() {
        return policemanArrayList.size();
    }

    public class PolicemanViewHolder extends RecyclerView.ViewHolder {

        public ImageView policemanImage;
        public TextView policemanName, policemanRank, policemanArea, policemanPhone, policemanEmail;
        public RatingBar policemanRating;
        public TextView policemanRatingTV;
        public PolicemanViewHolder(@NonNull View itemView) {
            super(itemView);
            /*policemanImage = itemView.findViewById(R.id.policeman_image);
            policemanName = itemView.findViewById(R.id.policeman_name);
            policemanRank = itemView.findViewById(R.id.policeman_rank);
            policemanArea = itemView.findViewById(R.id.policeman_area);
            policemanPhone = itemView.findViewById(R.id.policeman_phone);
            policemanEmail = itemView.findViewById(R.id.policeman_email);
            policemanRating = itemView.findViewById(R.id.policeman_rating);*/

            policemanImage = itemView.findViewById(R.id.policemanIV);
            policemanName = itemView.findViewById(R.id.policemanNameTV);
            policemanRank = itemView.findViewById(R.id.policemanDesignationTV);
            policemanArea = itemView.findViewById(R.id.policemanAreaTV);
            policemanPhone = itemView.findViewById(R.id.policemanPhoneTV);
            policemanEmail = itemView.findViewById(R.id.policemanEmailTV);
            policemanRatingTV = itemView.findViewById(R.id.policemanRatingTV);
        }

    }
}
