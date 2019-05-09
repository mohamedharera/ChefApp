package com.elchefapp.elchefapp.Services;

import com.elchefapp.elchefapp.models.models_chef.addImage.ClassAddImageResponse;
import com.elchefapp.elchefapp.models.models_chef.addMeal.ClassAddMealResponse;
import com.elchefapp.elchefapp.models.models_chef.chefProfile.ClassChefProfileResponse;
import com.elchefapp.elchefapp.models.models_chef.city.ClassCityResponse;
import com.elchefapp.elchefapp.models.models_chef.login_chef.ClassChefLoginResponse;
import com.elchefapp.elchefapp.models.models_chef.register_chef.ClassChefRegisterResponse;
import com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages.ClassChefRestaurantListsResponse;
import com.elchefapp.elchefapp.models.models_client.changePassword.ClassChangePsswordResponse;
import com.elchefapp.elchefapp.models.models_client.chefDetails.ClassCustomerRestaurantDetailsResponse;
import com.elchefapp.elchefapp.models.models_client.favourites.ClassCustomerFavouriteResponse;
import com.elchefapp.elchefapp.models.models_client.login.ClassCustomerLoginResponse;
import com.elchefapp.elchefapp.models.models_client.places.ClassCustomerPlacesResponse;
import com.elchefapp.elchefapp.models.models_client.register.ClassCustomerRegisterResponse;
import com.elchefapp.elchefapp.models.models_client.registerFacebook.ClassRegisterFacebookResponse;
import com.elchefapp.elchefapp.models.models_client.registerGoogle.ClassRegisterGoogleResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterfaceService {
    // client interface

    // login
    @FormUrlEncoded
    @POST("login")
    Call<ClassCustomerLoginResponse> customerLogin(@Field("email")String email,
                                                   @Field("password")String password);
    // register
    @Multipart
    @POST("register")
    Call<ClassCustomerRegisterResponse> customerRegister(@Part("name") RequestBody name,
                                                         @Part("email")RequestBody email,
                                                         @Part("password")RequestBody password,
                                                         @Part("mobile")RequestBody mobile,
                                                         @Part("address")RequestBody address,
                                                         @Part MultipartBody.Part img);

      // register Login facebook
    @POST("facebook")
    Call<ClassRegisterFacebookResponse> customerFacebookLogin(@Field("email")String email,
                                                              @Field("fb_token")String fb_token,
                                                              @Field("name")String name);
    // register Login facebook
    @POST("google")
    Call<ClassRegisterGoogleResponse> customerGoogleLogin(@Field("email")String email,
                                                          @Field("fb_token")String fb_token,
                                                          @Field("name")String name);

    // list of restaurant(Chefs)
    @FormUrlEncoded
    @POST("chefs")
    Call<ClassCustomerPlacesResponse>listRestaurant_customer(@Field("city_id") int city_id);

    // list of food
    @FormUrlEncoded
    @POST("chef")
    Call<ClassCustomerRestaurantDetailsResponse>listofFood(@Field("api_token") String api_token,
                                                           @Field("chef_id")int chef_id);

    //add favourite
    @FormUrlEncoded
    @POST("favourite")
    Call<ClassCustomerRestaurantDetailsResponse>addFavourite(@Field("api_token")String api_token,
                                                             @Field("chef_id")int chef_id);

    //     my_favourites
    @FormUrlEncoded
    @POST("my_favourites")
    Call<ClassCustomerFavouriteResponse> getFavourites(@Field("api_token")String api_token);

    //     change_password
    @FormUrlEncoded
    @POST("change_password")
    Call<ClassChangePsswordResponse> changePassword(@Field("api_token")String api_token,
                                                    @Field("old_password")String old_password,
                                                    @Field("password")String password);

    @FormUrlEncoded
    @POST("rate")
    Call<ClassCustomerRestaurantDetailsResponse>addRate(@Field("api_token")String api_token,
                                                        @Field("chef_id")int chef_id,
                                                        @Field("rate")float rate);


    // register Twitter twitter
//    @POST("twitter")
//    Call<ClassLoginResponse> customerLogin(@Field("email")String email,
//                                           @Field("twitter_token")String twitter_token,
//                                           @Field("name")String name);

    // register Twitter twitter
//    @POST("google")
//    Call<ClassLoginResponse> customerLogin(@Field("email")String email,
//                                           @Field("google_plus_token")String google_plus_token,
//                                           @Field("name")String name);

    // map
//    @POST("map")
//    Call<ClassLoginResponse> customerLogin(@Field("lat")String lat,
//                                           @Field("lng")String lng);

    // profile
//    @POST("map")
//    Call<ClassLoginResponse> customerLogin(@Field("api_token")String api_token);








    // chef interface
    // login
    @FormUrlEncoded
    @POST("login")
    Call<ClassChefLoginResponse> chefLogin(@Field("email")String email,
                                           @Field("password")String password);
    // register
    @Multipart
    @POST("register")
    Call<ClassChefRegisterResponse> chefRegister(@Part("name") RequestBody name,
                                                 @Part("email")RequestBody email,
                                                 @Part("password")RequestBody password,
                                                 @Part("mobile")RequestBody mobile,
                                                 @Part("address")RequestBody address,
                                                 @Part MultipartBody.Part img);
    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>setProfileOnlineLinks(@Field("api_token") String api_token,
                                                        @Field("is_open")int is_open);
    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>setProfileHoursLinks(@Field("api_token") String api_token,
                                                       @Field("happy_hour")int happy_hour);
    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>setProfileDeliveryLinks(@Field("api_token") String api_token,
                                                          @Field("is_delivery")int is_delivery);
    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>setProfileFacebook(@Field("api_token") String api_token,
                                                     @Field("is_delivery")int is_delivery);
    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>setProfileTwitter(@Field("api_token") String api_token,
                                                     @Field("twitter")int is_delivery);
    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>setProfileInsta(@Field("api_token") String api_token,
                                                     @Field("instagram")int is_delivery);

    @Multipart
    @POST("add_image")
    Call<ClassAddImageResponse>addImage(@Part("api_token") RequestBody api_token,
                                        @Part MultipartBody.Part img);

    @FormUrlEncoded
    @POST("home")
    Call<ClassChefRestaurantListsResponse>getRestaurantLists(@Field("api_token")String api_token);

    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>sendOpenTimeStatus(@Field("api_token")String api_token);


    // Add Meal
    @Multipart
    @POST("add_meal")
    Call<ClassAddMealResponse> addMeal(@Part("api_token") RequestBody api_token,
                                       @Part("name")RequestBody name,
                                       @Part("price")RequestBody price,
                                       @Part MultipartBody.Part img);

    @FormUrlEncoded
    @POST("delete_meal")
    Call<ClassChefRestaurantListsResponse>deleteMeal(@Field("api_token")String api_token,
                                                     @Field("id")int id);

    @FormUrlEncoded
    @POST("delete_image")
    Call<ClassChefRestaurantListsResponse>deleteImage(@Field("api_token")String api_token,
                                                      @Field("id")int id);

    @GET("cities")
    Call<ClassCityResponse>getCities();

    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>sendCityId(@Field("api_token")String api_token,
                                             @Field("city_id")int city_id);

    @FormUrlEncoded
    @POST("profile")
    Call<ClassChefProfileResponse>sendLocation(@Field("api_token")String api_token,
                                               @Field("lat")double lat,
                                               @Field("lng")double lng);
}
