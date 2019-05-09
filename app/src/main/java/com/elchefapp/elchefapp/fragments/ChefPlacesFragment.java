package com.elchefapp.elchefapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.InterfaceService;
import com.elchefapp.elchefapp.Services.RetrofitInstanceClient;
import com.elchefapp.elchefapp.adapters.chef.RecylerAdapterChefPlaces;
import com.elchefapp.elchefapp.helper.OnEndless;
import com.elchefapp.elchefapp.models.models_client.places.ClassCustomerPlacesDatum;
import com.elchefapp.elchefapp.models.models_client.places.ClassCustomerPlacesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ChefPlacesFragment extends Fragment implements SearchView.OnQueryTextListener {

    RecyclerView listOfRestaurant;
    SearchView search;
    RecylerAdapterChefPlaces adapterChefPlaces;
    List<ClassCustomerPlacesDatum> data;
    int max_page;
    int spinnerValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_chef_places, container, false);

        search = view.findViewById(R.id.searchRestaurant_chef);
        listOfRestaurant = view.findViewById(R.id.reccylerView_list_of_restaurant_chef);
        search.setQueryHint("Search a item..");
        search.setOnQueryTextListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listOfRestaurant.setLayoutManager(linearLayoutManager);

        OnEndless onEndlesslistener = new OnEndless(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page < max_page) {
                    getListOfRestaurant(spinnerValue,current_page);
                } else {
//                    Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                }
            }
        };
        listOfRestaurant.addOnScrollListener(onEndlesslistener);

        SharedPreferences sharedPref = getContext().getSharedPreferences("FileName",MODE_PRIVATE);
        spinnerValue = sharedPref.getInt("spinnerChoice",0);
        if(spinnerValue != 0) {
            getListOfRestaurant(spinnerValue,1);
        }

        return view;
    }

    public void getListOfRestaurant(int cityId,final int page){
        final InterfaceService getDataService = RetrofitInstanceClient.getDataService();
        Call<ClassCustomerPlacesResponse> call = getDataService.listRestaurant_customer(cityId);
        call.enqueue(new Callback<ClassCustomerPlacesResponse>() {
            @Override
            public void onResponse(Call<ClassCustomerPlacesResponse> call, Response<ClassCustomerPlacesResponse> response) {
                if (response.body().getStatus()==1){
                    ClassCustomerPlacesResponse response1 = response.body();
                    viewResponse(response1,page);
                    max_page = response1.getData().getLastPage();
                }else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClassCustomerPlacesResponse> call, Throwable t) {
                if( t.getMessage() != null) {
                    Log.i("response error", t.getMessage());
                }

            }
        });
    }

    public void viewResponse(ClassCustomerPlacesResponse body, int page) {
        if (page == 1) {
            data= body.getData().getData();
            adapterChefPlaces = new RecylerAdapterChefPlaces(data,getContext());
            listOfRestaurant.setAdapter(adapterChefPlaces);
        } else{
            data.addAll(body.getData().getData());
            adapterChefPlaces.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (adapterChefPlaces != null){
            adapterChefPlaces.getFilter().filter(s);
        }
        return false;
    }

}
