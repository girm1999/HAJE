package com.cookandroid.haje;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Item> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rideDate, rideTime, ridePoint, arriveTime, arrivePoint;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            rideDate = (TextView) itemView.findViewById(R.id.tv_rideDate);
            rideTime = (TextView) itemView.findViewById(R.id.tv_rideTime);
            ridePoint = (TextView) itemView.findViewById(R.id.tv_ridePlace);
            arriveTime = (TextView) itemView.findViewById(R.id.tv_arriveTime);
            arrivePoint = (TextView) itemView.findViewById(R.id.tv_destination);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}