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

    private static final String LABEL_SEEN = "SEEN";
    private static final String LABEL_PENDING = "PENDING";
    private static final String LABEL_COMPLETED = "COMPLETED";

    private static final int PENDING = 0;
    private static final int SEEN = 1;
    private static final int COMPLETED = 2;

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

        switch (model.getStatus()) {
            case PENDING:
                requestsViewHolder.requestStatusTV.setText(LABEL_PENDING);
                break;
            case SEEN:
                requestsViewHolder.requestStatusTV.setText(LABEL_SEEN);
                requestsViewHolder.requestStatusTV.setBackgroundResource(R.color.black);
                break;
            case COMPLETED:
                requestsViewHolder.requestStatusTV.setText(LABEL_COMPLETED);
                requestsViewHolder.requestStatusTV.setBackgroundResource(R.color.colorAccent);
                break;
        }
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
        TextView requestStatusTV;

        public RequestsViewHolder(@NonNull View itemView) {
            super(itemView);
            //requestNameTV = itemView.findViewById(R.id.requestNameTV);
            requestPhoneTV = itemView.findViewById(R.id.requestPhoneTV);
            requestPhoneTV.setPaintFlags(requestPhoneTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            requestAddressTV = itemView.findViewById(R.id.requestAddressTV);
            requestFromDateTV = itemView.findViewById(R.id.requestFromDateTV);
            requestToDateTV = itemView.findViewById(R.id.requestToDateTV);
            requestAreaTV = itemView.findViewById(R.id.requestAreaTV);
            requestStatusTV = itemView.findViewById(R.id.requestStatusTV);
        }
    }
}
