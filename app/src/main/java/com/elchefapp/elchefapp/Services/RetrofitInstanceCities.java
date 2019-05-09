package com.elchefapp.elchefapp.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceCities {
    private static Retrofit retrofit = null;
    private static String Base_url = "https://i-smart.app/demos/chef/public/api/v1/";
    public static InterfaceService getDataService(){
        if (retrofit == null)
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit.create(InterfaceService.class);
    }
}
