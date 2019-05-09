package com.elchefapp.elchefapp.adapters.customer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsImages;

import java.util.List;

public class RecyclerAdapterSliderCheDetails extends RecyclerView.Adapter<RecyclerAdapterSliderCheDetails.ViewHolder>{
    List<ClassCustomerRestaurantDetailsImages> mData;
    Context mContext;

    public RecyclerAdapterSliderCheDetails(List<ClassCustomerRestaurantDetailsImages> Data, Context context) {
        this.mData = Data;
        this.mContext = context;
    }
    @Override
    public RecyclerAdapterSliderCheDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_chefdetails, parent,false);
        return new RecyclerAdapterSliderCheDetails.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final RecyclerAdapterSliderCheDetails.ViewHolder holder, final int position) {

        ClassCustomerRestaurantDetailsImages data = mData.get(position);
        String url_image = "https://i-smart.app/demos/chef/public/";
//        Glide.with(mContext).load(url_image+data.getLink()).into(holder.slider_image_view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView slider_image_view;

        public ViewHolder(View v) {
            super(v);

            slider_image_view = v.findViewById(R.id.slider_image_view_chefdetails);

        }
    }
}
