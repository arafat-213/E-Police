package com.svit.epolice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

    private ArrayList<Option> optionArrayList;

    public OptionAdapter(ArrayList<Option> optionArrayList) {
        this.optionArrayList = optionArrayList;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_option, viewGroup, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionAdapter.OptionViewHolder optionViewHolder, int i) {
        optionViewHolder.icon.setImageResource(optionArrayList.get(i).getIcon());
        optionViewHolder.name.setText(optionArrayList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return optionArrayList.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView name;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
        }
    }
}
