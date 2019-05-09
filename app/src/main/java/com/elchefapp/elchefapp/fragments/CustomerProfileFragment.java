package com.elchefapp.elchefapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.InterfaceService;
import com.elchefapp.elchefapp.Services.RetrofitInstanceClient;
import com.elchefapp.elchefapp.activities.ChangePasswordActivity;
import com.elchefapp.elchefapp.adapters.customer.RecyclerAdapterFavourites;
import com.elchefapp.elchefapp.models.models_client.favourites.ClassCustomerFavouriteDatum;
import com.elchefapp.elchefapp.models.models_client.favourites.ClassCustomerFavouriteResponse;
import com.elchefapp.elchefapp.models.models_client.login.ClassCustomerLoginData;
import com.elchefapp.elchefapp.sharedPrefrences.SharedprefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerProfileFragment extends Fragment {

    String ApiToken = "fP5MExFbHNx0Vk5IlWpgI5EUtp4GmeZejn9Qs1BXGzaXwyZBeqUsNL10iTCy";
    TextView userName,userEmail,userPhone,userLocation;
    ImageView userImage;
    Button changePassword;
    ClassCustomerLoginData userData;
    RecyclerAdapterFavourites recyclerAdapterFavourites;
    RecyclerView recyclerView_favourite_list;
    List<ClassCustomerFavouriteDatum> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_customer, container, false);

        userName = view.findViewById(R.id.profile_name);
        userImage = view.findViewById(R.id.profile_user_image);
        userEmail = view.findViewById(R.id.profile_email);
        userPhone = view.findViewById(R.id.profile_phone);
        userLocation = view.findViewById(R.id.profile_location);
        changePassword = view.findViewById(R.id.changePassBtn);
        recyclerView_favourite_list = view.findViewById(R.id.recyclerView_favourite_list);

        recyclerView_favourite_list.setLayoutManager(new LinearLayoutManager(getContext()));


          changePassword.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getContext(),ChangePasswordActivity.class);
                  startActivity(intent);
              }
          });

        int id = SharedprefManager.getInstance(getContext()).getUserData().getId();
        getCustomerData();
        getFavouriteList(id);
        return view;

    }

    public void getCustomerData(){
        userData = SharedprefManager.getInstance(getContext()).getUserData();
        userName.setText(userData.getName());
        userEmail.setText(userData.getEmail());
        userPhone.setText(userData.getMobile());
        userLocation.setText(userData.getAddress());
        String url ="https://i-smart.app/demos/chef/public/";
        Glide.with(getContext()).load(url+userData.getImage()).into(userImage);
    }
    public void getFavouriteList(int customer_id){
        final InterfaceService getDataService = RetrofitInstanceClient.getDataService();
        Call<ClassCustomerFavouriteResponse> call = getDataService.getFavourites(ApiToken);
        call.enqueue(new Callback<ClassCustomerFavouriteResponse>() {
            @Override
            public void onResponse(Call<ClassCustomerFavouriteResponse> call, Response<ClassCustomerFavouriteResponse> response) {
                ClassCustomerFavouriteResponse response1 = response.body();
                if(response1.getStatus() == 1) {
                    data = response1.getData();
                    recyclerAdapterFavourites = new RecyclerAdapterFavourites(data, getContext());
                    recyclerView_favourite_list.setAdapter(recyclerAdapterFavourites);

                } else {
                    Toast.makeText(getContext(), "no data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClassCustomerFavouriteResponse> call, Throwable t) {

            }
        });
    }

}
