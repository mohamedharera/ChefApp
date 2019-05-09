package com.elchefapp.elchefapp.adapters.chef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages.ClassChefRestaurantListsImages;

import java.util.List;

public class RecyclerAdapterSliderImageProfile extends RecyclerView.Adapter<RecyclerAdapterSliderImageProfile.ViewHolder>{
    List<ClassChefRestaurantListsImages> mData;
    Context mContext;

    public RecyclerAdapterSliderImageProfile(List<ClassChefRestaurantListsImages> Data, Context context) {
        this.mData = Data;
        this.mContext = context;
    }
    @Override
    public RecyclerAdapterSliderImageProfile.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image, parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final RecyclerAdapterSliderImageProfile.ViewHolder holder, final int position) {

        ClassChefRestaurantListsImages data = mData.get(position);
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

            slider_image_view = v.findViewById(R.id.slider_image_view);

        }
    }
}
