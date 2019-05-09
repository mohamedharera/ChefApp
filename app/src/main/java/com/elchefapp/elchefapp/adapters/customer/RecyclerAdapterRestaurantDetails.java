package com.elchefapp.elchefapp.adapters.customer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsFood;

import java.util.List;

public class RecyclerAdapterRestaurantDetails  extends RecyclerView.Adapter<RecyclerAdapterRestaurantDetails.ViewHolder> {

    List<ClassCustomerRestaurantDetailsFood> mData;
    Context mContext;


    public RecyclerAdapterRestaurantDetails(List<ClassCustomerRestaurantDetailsFood> Data, Context context) {
        this.mData = Data;
        this.mContext = context;
    }

    @Override
    public RecyclerAdapterRestaurantDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_listof_food, null,false);
        RecyclerAdapterRestaurantDetails.ViewHolder viewHolder = new RecyclerAdapterRestaurantDetails.ViewHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterRestaurantDetails.ViewHolder holder, final int position) {

        final ClassCustomerRestaurantDetailsFood data = mData.get(position);
        holder.foodPrice.setText(data.getPrice());
        holder.food_name.setText(data.getName());
        Glide.with(mContext).load(data.getImage()).into(holder.food_image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView food_image;
        TextView food_name,foodPrice;

        public ViewHolder(View v) {
            super(v);

            food_image = v.findViewById(R.id.food_image);
            food_name = v.findViewById(R.id.food_name);
            foodPrice = v.findViewById(R.id.price);

        }
    }



}
