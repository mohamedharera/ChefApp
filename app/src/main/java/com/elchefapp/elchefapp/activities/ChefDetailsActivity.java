package com.elchefapp.elchefapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.InterfaceService;
import com.elchefapp.elchefapp.Services.RetrofitInstanceClient;
import com.elchefapp.elchefapp.adapters.customer.RecyclerAdapterRestaurantDetails;
import com.elchefapp.elchefapp.adapters.customer.RecyclerAdapterSliderCheDetails;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsData;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsFood;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsImages;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsResponse;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChefDetailsActivity extends AppCompatActivity{

    RatingBar restaurant_rating;
    TextView restaurantName, restaurantLocation, resraurantDetails_close,resraurantDetails_open, restaurantTimeOpenFrom, restaurantTimeOpenTo, call_number, restaurant_type, menu_average;
    RecyclerView recyclerView;
    ImageButton facebookBtn, phoneCall,locationBtn;
    List<ClassCustomerRestaurantDetailsFood> foodList;
    ClassCustomerRestaurantDetailsData customerRestaurantDetailsData;
    CheckBox isFavouriteCheckBox;
    DiscreteScrollView scrollView;
    RecyclerAdapterRestaurantDetails adapterRestaurantDetails;
    RecyclerAdapterSliderCheDetails recyclerAdapterSliderCheDetails;
    List<ClassCustomerRestaurantDetailsImages> listImage;
    int chef_id;

    String API_TOKEN = "fP5MExFbHNx0Vk5IlWpgI5EUtp4GmeZejn9Qs1BXGzaXwyZBeqUsNL10iTCy";
    String rateApiToken = "fP5MExFbHNx0Vk5IlWpgI5EUtp4GmeZejn9Qs1BXGzaXwyZBeqUsNL10iTCy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_details);

        initGUIComponents();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //    get id from chefProfile
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        chef_id = prefs.getInt("chefDetails_id", 0);
        getListOfFood(chef_id);
        getRestaurantDetails(chef_id);
        openChefLocation(chef_id);
        addRate(chef_id);
        getSliderImages(chef_id);
    }

    private void initGUIComponents() {
        restaurant_rating = findViewById(R.id.res_rating);
        restaurantName = findViewById(R.id.resraurantDetails_resName);
        restaurantLocation = findViewById(R.id.resraurantDetails_resLocation);
        resraurantDetails_open = findViewById(R.id.resraurantDetails_open);
        resraurantDetails_close = findViewById(R.id.resraurantDetails_close);

        restaurantTimeOpenFrom = findViewById(R.id.open_time_from);
        restaurantTimeOpenTo = findViewById(R.id.open_time_to);
        call_number = findViewById(R.id.call_number);
//        restaurant_type = findViewById(R.id.restaurant_type);
        menu_average = findViewById(R.id.menu_average);
        phoneCall = findViewById(R.id.phoneCall);
        facebookBtn = findViewById(R.id.facebookBtn);
        recyclerView = findViewById(R.id.recyclerView_list_of_food);
        isFavouriteCheckBox = findViewById(R.id.isFavourite);
        locationBtn = findViewById(R.id.locationBtn);
        scrollView = findViewById(R.id.picker_chefDetails);
    }

    public void getSliderImages(int chef_id) {
        final InterfaceService getDataService = RetrofitInstanceClient.getDataService();
        Call<ClassCustomerRestaurantDetailsResponse> call = getDataService.listofFood(API_TOKEN,chef_id);
        call.enqueue(new Callback<ClassCustomerRestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<ClassCustomerRestaurantDetailsResponse> call, Response<ClassCustomerRestaurantDetailsResponse> response) {
                if (response.body() != null) {
                    ClassCustomerRestaurantDetailsResponse response1 = response.body();
                    if (response1.getStatus() == 1) {
                        listImage = response1.getData().getImages();
                        recyclerAdapterSliderCheDetails = new RecyclerAdapterSliderCheDetails(listImage,ChefDetailsActivity.this);
                        scrollView.setAdapter(recyclerAdapterSliderCheDetails);

                    }
                }
            }

            @Override
            public void onFailure(Call<ClassCustomerRestaurantDetailsResponse> call, Throwable t) {

            }
        });
    }

    public void openChefLocation(int chef_id){
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InterfaceService getDataService = RetrofitInstanceClient.getDataService();
                Call<ClassCustomerRestaurantDetailsResponse> call = getDataService.listofFood(API_TOKEN, chef_id);
                call.enqueue(new Callback<ClassCustomerRestaurantDetailsResponse>() {
                    @Override
                    public void onResponse(Call<ClassCustomerRestaurantDetailsResponse> call, Response<ClassCustomerRestaurantDetailsResponse> response) {
                        ClassCustomerRestaurantDetailsResponse response1 = response.body();
                        if (response1.getStatus()==1){
                            double latitude = customerRestaurantDetailsData.getLat();
                            double longitude = customerRestaurantDetailsData.getLng();

                            Uri uri = Uri.parse("geo:"+latitude+","+longitude);
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(mapIntent);
                            }
//                            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)"+latitude+","+longitude);
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                            intent.setPackage("com.google.android.apps.maps");
//                            startActivity(intent);

//                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                                    Uri.parse("geo:"+latitude,-longitude));
//                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassCustomerRestaurantDetailsResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void getListOfFood(int chef_id) {
        final InterfaceService getDataService = RetrofitInstanceClient.getDataService();
        Call<ClassCustomerRestaurantDetailsResponse> call = getDataService.listofFood(API_TOKEN, chef_id);
        call.enqueue(new Callback<ClassCustomerRestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<ClassCustomerRestaurantDetailsResponse> call, Response<ClassCustomerRestaurantDetailsResponse> response) {
                if (response.body().getStatus() == 1) {

                    foodList = response.body().getData().getFoods();
                    adapterRestaurantDetails = new RecyclerAdapterRestaurantDetails(foodList, getApplicationContext());
                    recyclerView.setAdapter(adapterRestaurantDetails);

                } else {
                    Toast.makeText(getApplicationContext(), "no customerRestaurantDetailsData found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClassCustomerRestaurantDetailsResponse> call, Throwable t) {
                if (t.getMessage() != null) {
                    Log.i("response error", t.getMessage());
                }

            }
        });
    }

    public void addRate(int chefId){
        restaurant_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float ratingValue = customerRestaurantDetailsData.setRate(rating);
                final InterfaceService getDataService = RetrofitInstanceClient.getDataService();
                Call<ClassCustomerRestaurantDetailsResponse> call = getDataService.addRate(rateApiToken, chefId,ratingValue);
                call.enqueue(new Callback<ClassCustomerRestaurantDetailsResponse>() {
                    @Override
                    public void onResponse(Call<ClassCustomerRestaurantDetailsResponse> call, Response<ClassCustomerRestaurantDetailsResponse> response) {
                        final ClassCustomerRestaurantDetailsResponse restaurantDetailsResponse = response.body();
                        if (restaurantDetailsResponse.getStatus()==1){
                            Log.i("myrespondmessage", restaurantDetailsResponse.getMessage());
                            restaurant_rating.getRating();
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassCustomerRestaurantDetailsResponse> call, Throwable t) {

                    }
                });
            }
        });

    }

    public void getRestaurantDetails(int chef_id) {
        final InterfaceService getDataService = RetrofitInstanceClient.getDataService();
        Call<ClassCustomerRestaurantDetailsResponse> call = getDataService.listofFood(API_TOKEN, chef_id);
        call.enqueue(new Callback<ClassCustomerRestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<ClassCustomerRestaurantDetailsResponse> call, final Response<ClassCustomerRestaurantDetailsResponse> response) {
                if (response.body().getStatus() == 1) {
                    customerRestaurantDetailsData = response.body().getData();
                    restaurantName.setText(String.valueOf(customerRestaurantDetailsData.getName()));
                    restaurantLocation.setText(String.valueOf(customerRestaurantDetailsData.getAddress()));
                    restaurantTimeOpenFrom.setText(String.valueOf(customerRestaurantDetailsData.getOpenFrom()));
                    restaurantTimeOpenTo.setText(String.valueOf(customerRestaurantDetailsData.getOpenTo()));
                    call_number.setText(String.valueOf(customerRestaurantDetailsData.getVisitsCount()));
//                    restaurant_type.setText(String.valueOf(customerRestaurantDetailsData.getType()));
                    menu_average.setText(String.valueOf(customerRestaurantDetailsData.getMenuAvg()));
                    restaurant_rating.setRating(customerRestaurantDetailsData.getRate());

                    int status = customerRestaurantDetailsData.getIsOpen();
                    if (status == 0) {
                        resraurantDetails_close.setVisibility(View.VISIBLE);
                        resraurantDetails_open.setVisibility(View.INVISIBLE);
                    } else if (status == 1){
                        resraurantDetails_open.setVisibility(View.INVISIBLE);
                        resraurantDetails_close.setVisibility(View.VISIBLE);
                    }


                    phoneCall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String phoneNumber = customerRestaurantDetailsData.getPhone();
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("tel:" + phoneNumber));
                            startActivity(i);
                        }
                    });

//                    facebookBtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(customerRestaurantDetailsData.getFacebook()+""));
//                            startActivity(intent);
//                        }
//                    });

                    manipulateLikeRestaurantButton();


                } else {
                    Toast.makeText(getApplicationContext(), "no customerRestaurantDetailsData found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ClassCustomerRestaurantDetailsResponse> call, Throwable t) {
                if (t.getMessage() != null) {
                    Log.i("response error", t.getMessage());
                }

            }
        });
    }

    private void manipulateLikeRestaurantButton() {
        isFavouriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isFavouriteCheckBox.isChecked()) {
                    sendLikeStatusToChefRestaurant(isChecked,chef_id,1);
                    isFavouriteCheckBox.setBackgroundResource(R.drawable.ic_like);

                }
                else {
                    sendLikeStatusToChefRestaurant(isChecked,chef_id,0);
                    isFavouriteCheckBox.setBackgroundResource(R.drawable.ic_dislike);

                }
            }

        });
    }
    private void sendLikeStatusToChefRestaurant(final boolean isChecked,int id,int status) {
        Call<ClassCustomerRestaurantDetailsResponse> call = RetrofitInstanceClient.getDataService().addFavourite(API_TOKEN,id);

        call.enqueue(new Callback<ClassCustomerRestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<ClassCustomerRestaurantDetailsResponse> call, Response<ClassCustomerRestaurantDetailsResponse> response) {
                ClassCustomerRestaurantDetailsResponse customerRestaurantDetailsResponse = response.body();
                if (customerRestaurantDetailsResponse.getStatus() == 1) {
                    if (isFavouriteCheckBox .isChecked()) {
                        isFavouriteCheckBox.setChecked(isChecked);
                        isFavouriteCheckBox.setBackgroundResource(R.drawable.ic_like);
                    }
                    else {
                        isFavouriteCheckBox.setChecked(isChecked);
                        isFavouriteCheckBox.setBackgroundResource(R.drawable.ic_dislike);

                    }

                }
            }

            @Override
            public void onFailure(Call<ClassCustomerRestaurantDetailsResponse> call, Throwable t) {
                Log.i("isChecked", "error  " + t.getMessage());
            }
        });


    }


}


