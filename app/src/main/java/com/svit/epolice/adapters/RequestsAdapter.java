package com.svit.epolice.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.svit.epolice.Models.PatrollingRequest;
import com.svit.epolice.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequestsAdapter extends FirebaseRecyclerAdapter<PatrollingRequest, RequestsAdapter.RequestsViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RequestsAdapter(@NonNull FirebaseRecyclerOptions<PatrollingRequest> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RequestsViewHolder requestsViewHolder, int position, @NonNull PatrollingRequest model) {
       // requestsViewHolder.requestNameTV.setText(model.getFullName());
        requestsViewHolder.requestFromDateTV.setText(model.getFromDate());
        requestsViewHolder.requestToDateTV.setText(model.getToDate());
        requestsViewHolder.requestAreaTV.setText(model.getArea());
        requestsViewHolder.requestPhoneTV.setText(model.getPhoneNo());
        requestsViewHolder.requestAddressTV.setText(model.getAddress());

    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_request, viewGroup, false);
        return new RequestsViewHolder(view);
    }

    class RequestsViewHolder extends RecyclerView.ViewHolder {

        //TextView requestNameTV;
        TextView requestPhoneTV;
        TextView requestAddressTV;
        TextView requestFromDateTV;
        TextView requestToDateTV;
        TextView requestAreaTV;

        public RequestsViewHolder(@NonNull View itemView) {
            super(itemView);
            //requestNameTV = itemView.findViewById(R.id.requestNameTV);
            requestPhoneTV = itemView.findViewById(R.id.requestPhoneTV);
            requestPhoneTV.setPaintFlags(requestPhoneTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            requestAddressTV = itemView.findViewById(R.id.requestAddressTV);
            requestFromDateTV = itemView.findViewById(R.id.requestFromDateTV);
            requestToDateTV = itemView.findViewById(R.id.requestToDateTV);
            requestAreaTV = itemView.findViewById(R.id.requestAreaTV);
        }
    }
}
