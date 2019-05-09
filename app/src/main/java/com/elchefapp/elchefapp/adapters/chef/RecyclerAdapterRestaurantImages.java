package com.elchefapp.elchefapp.adapters.chef;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages.ClassChefRestaurantListsImages;

import java.util.List;

public class RecyclerAdapterRestaurantImages extends RecyclerView.Adapter<RecyclerAdapterRestaurantImages.ViewHolder>{

    public interface OnItemClickListener {
        void onItemClickAddImage(ClassChefRestaurantListsImages item);
        void onItemClickDeleteImage(ClassChefRestaurantListsImages item,final int position);
    }

    private static final int LAST_ITEM = 1;
    private static final int NORMAL_ITEM = 2;
    final List<ClassChefRestaurantListsImages> mData;
    Context mContext;
    OnItemClickListener itemClickCallback;

    public RecyclerAdapterRestaurantImages(Context mContext,List<ClassChefRestaurantListsImages> mData, OnItemClickListener itemClickCallback) {
        this.mData = mData;
        this.mContext = mContext;
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == NORMAL_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_restaurant_image, parent, false);
            return new ViewHolder(view);
        }
        else if(viewType == LAST_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_last_restaurant_img, parent, false);
            return new ViewHolder(view);
        }
        else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case LAST_ITEM:
                holder.bindData(mData.get(position),itemClickCallback);
                break;
            case NORMAL_ITEM:
                ClassChefRestaurantListsImages data = mData.get(position);
                String url_image = "https://i-smart.app/demos/chef/public/";
//        Glide.with(mContext).load(url_image+data.getLink()+"").into(holder.restaurant_image_view);
                holder.bindDataItem(data,position,itemClickCallback);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mData.size()-1){
            return LAST_ITEM;
        }else{
            return NORMAL_ITEM;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView restaurant_image_view;
        ConstraintLayout constrainAddImage;


        public ViewHolder(View v) {
            super(v);

            restaurant_image_view = v.findViewById(R.id.restaurant_image_view);
            constrainAddImage = v.findViewById(R.id.constrainAddImage);
        }

        // on click for last item after recyclerview ended (add image)
        public void bindData(final ClassChefRestaurantListsImages data, final OnItemClickListener itemClickCallback) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickCallback.onItemClickAddImage(data);
                }
            });
        }
        // long click for recyclerview item to delete image
        public void bindDataItem (final ClassChefRestaurantListsImages data,int position,final OnItemClickListener itemClickCallback) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickCallback.onItemClickDeleteImage(data,position);
                }
            });
        }
    }

    public List<ClassChefRestaurantListsImages> getData() {
        return mData;
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem(ClassChefRestaurantListsImages item, int position) {
        mData.add(position, item);
        notifyItemInserted(position);
    }


}
