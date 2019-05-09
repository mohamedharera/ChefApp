package com.elchefapp.elchefapp.adapters.customer;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsFood;
import com.elchefapp.elchefapp.models.models_client.favourites.ClassCustomerFavouriteDatum;

import java.util.List;

public class RecyclerAdapterFavourites extends RecyclerView.Adapter<RecyclerAdapterFavourites.ViewHolder>{
    List<ClassCustomerFavouriteDatum> mData;
    Context mContext;


    public RecyclerAdapterFavourites(List<ClassCustomerFavouriteDatum> Data, Context context) {
        this.mData = Data;
        this.mContext = context;
    }

    @Override
    public RecyclerAdapterFavourites.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_favourite_chef, null,false);
        RecyclerAdapterFavourites.ViewHolder viewHolder = new RecyclerAdapterFavourites.ViewHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterFavourites.ViewHolder holder, final int position) {

        final ClassCustomerFavouriteDatum data = mData.get(position);
        holder.food_name.setText(data.getName());
        holder.favourite_rating.setRating(data.getRate());
        Glide.with(mContext).load(data.getImage()).into(holder.food_image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView food_image;
        TextView food_name;
        RatingBar favourite_rating;

        public ViewHolder(View v) {
            super(v);

            food_image = v.findViewById(R.id.food_image_favourite);
            food_name = v.findViewById(R.id.food_name_favourite);
            favourite_rating = v.findViewById(R.id.favourite_rating);

        }
    }

}
