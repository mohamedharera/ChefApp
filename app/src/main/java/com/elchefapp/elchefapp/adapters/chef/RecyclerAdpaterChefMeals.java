package com.elchefapp.elchefapp.adapters.chef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages.ClassChefRestaurantListsFood;

import java.util.List;

public class RecyclerAdpaterChefMeals  extends RecyclerView.Adapter<RecyclerAdpaterChefMeals.ViewHolder> {

    List<ClassChefRestaurantListsFood> mData;
    Context mContext;


    public RecyclerAdpaterChefMeals(List<ClassChefRestaurantListsFood> Data, Context context) {
        this.mData = Data;
        this.mContext = context;
    }

    @Override
    public RecyclerAdpaterChefMeals.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_list_of_meals, parent,false);
        RecyclerAdpaterChefMeals.ViewHolder viewHolder = new RecyclerAdpaterChefMeals.ViewHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdpaterChefMeals.ViewHolder holder, final int position) {

        final ClassChefRestaurantListsFood data = mData.get(position);
        holder.foodPrice.setText(data.getPrice());
        holder.food_name.setText(data.getName());
        Glide.with(mContext).load(data.getImage()).into(holder.food_image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem(ClassChefRestaurantListsFood item, int position) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView food_image;
        TextView food_name,foodPrice;

        public ViewHolder(View v) {
            super(v);

            food_image = v.findViewById(R.id.food_image_res);
            food_name = v.findViewById(R.id.food_name_res);
            foodPrice = v.findViewById(R.id.food_price_res);


        }
    }
    public List<ClassChefRestaurantListsFood> getData() {
        return mData;
    }
}
