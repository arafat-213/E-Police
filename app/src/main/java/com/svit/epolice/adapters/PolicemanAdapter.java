package com.svit.epolice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.svit.epolice.Models.Policeman;
import com.svit.epolice.R;

public class PolicemanAdapter extends FirebaseRecyclerAdapter<Policeman, PolicemanAdapter.PolicemanViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PolicemanAdapter(@NonNull FirebaseRecyclerOptions<Policeman> options) {
        super(options);
    }

    @NonNull
    @Override
    public PolicemanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_policemen_list, viewGroup, false);
        return new PolicemanViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull PolicemanViewHolder policemanViewHolder, int position, @NonNull Policeman model) {
        policemanViewHolder.policemanImage.setImageResource(model.getImage_id());
        policemanViewHolder.policemanName.setText(model.getName());
        policemanViewHolder.policemanRank.setText(model.getRank());
        policemanViewHolder.policemanArea.setText(model.getArea());
        policemanViewHolder.policemanPhone.setText(model.getMobile_no());
        policemanViewHolder.policemanEmail.setText(model.getEmail());
        policemanViewHolder.policemanRatingTV.setText(model.getRating() + " ★");
//        policemanViewHolder.policemanRating.setRating(model.getRating());

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
