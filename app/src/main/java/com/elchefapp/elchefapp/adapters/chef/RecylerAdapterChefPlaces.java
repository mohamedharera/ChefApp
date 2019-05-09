package com.elchefapp.elchefapp.adapters.chef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.models.models_client.places.ClassCustomerPlacesDatum;

import java.util.ArrayList;
import java.util.List;

public class RecylerAdapterChefPlaces extends RecyclerView.Adapter<RecylerAdapterChefPlaces.ViewHolder> implements Filterable {
    List<ClassCustomerPlacesDatum> mData;
    Context mContext;
    List<ClassCustomerPlacesDatum> mDataFull;


    public RecylerAdapterChefPlaces(List<ClassCustomerPlacesDatum> Data, Context context) {
        this.mData = Data;
        this.mContext = context;
        mDataFull = new ArrayList<>(mData);
    }

    @Override
    public RecylerAdapterChefPlaces.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_customer_places, null,false);
        RecylerAdapterChefPlaces.ViewHolder viewHolder = new RecylerAdapterChefPlaces.ViewHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecylerAdapterChefPlaces.ViewHolder holder, final int position) {

        final ClassCustomerPlacesDatum data = mData.get(position);
        Glide.with(mContext).load(data.getImage()).into(holder.restaurantImage);
        holder.restaurantName.setText(data.getName());
        holder.restaurantAddress.setText(data.getAddress());

        int isopen = data.getIsOpen();
        if (isopen ==1){
            holder.customer_open.setVisibility(View.VISIBLE);
            holder.customer_closed.setVisibility(View.INVISIBLE);
        }else {
            holder.customer_open.setVisibility(View.INVISIBLE);
            holder.customer_closed.setVisibility(View.VISIBLE);
        }

        holder.restaurantRate.setText(data.getRate()+"");
        holder.rateBar.setRating(data.getRate());
//        holder.restaurantContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
////                FragmentTransaction transaction = fragmentManager.beginTransaction();
////                Bundle bundle = new Bundle();
////                bundle.putInt("restaurant_id", data.getId());
////                RestaurantDetailsFragment restaurantDetails_fragment = new RestaurantDetailsFragment ();
////                restaurantDetails_fragment.setArguments(bundle);
////                transaction.replace(R.id.home_content, restaurantDetails_fragment);
////                transaction.addToBackStack(null);
////                transaction.commit();
//
//                Intent intent = new Intent(mContext, ChefDetailsActivity.class);
//                int id = data.getId();
//                intent.putExtra("restaurant_id", id);
//                mContext.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView restaurantImage;
        TextView restaurantName,restaurantAddress,customer_open,customer_closed,restaurantRate;
        RatingBar rateBar;
//        ConstraintLayout restaurantContent;

        public ViewHolder(View v) {
            super(v);


            restaurantImage = v.findViewById(R.id.cusomer_restaurant_image);
            restaurantName = v.findViewById(R.id.customer_restaurant_name);
            restaurantAddress = v.findViewById(R.id.customer_restaurant_location);
            customer_open = v.findViewById(R.id.customer_open);
            customer_closed = v.findViewById(R.id.customer_closed);
            restaurantRate = v.findViewById(R.id.customer_rating);
            rateBar = v.findViewById(R.id.customer_go_rating);
//            restaurantContent = v.findViewById(R.id.constrain_restaurantInformationContent);

        }


    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ClassCustomerPlacesDatum>filteredList = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filteredList.addAll(mDataFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ClassCustomerPlacesDatum item :mDataFull){
                    if (item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values =filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mData.clear();
            mData.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
