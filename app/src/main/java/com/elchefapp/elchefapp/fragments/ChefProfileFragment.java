package com.elchefapp.elchefapp.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.InterfaceService;
import com.elchefapp.elchefapp.Services.RetrofitInstanceChef;
import com.elchefapp.elchefapp.Services.RetrofitInstanceCities;
import com.elchefapp.elchefapp.activities.ChefHomeActivity;
import com.elchefapp.elchefapp.activities.ChefMapActivity;
import com.elchefapp.elchefapp.adapters.chef.RecyclerAdapterRestaurantImages;
import com.elchefapp.elchefapp.adapters.chef.RecyclerAdapterSliderImageProfile;
import com.elchefapp.elchefapp.adapters.chef.RecyclerAdpaterChefMeals;
import com.elchefapp.elchefapp.helper.AlbumUpload;
import com.elchefapp.elchefapp.helper.SwipeToDeleteCallback;
import com.elchefapp.elchefapp.models.models_chef.addImage.ClassAddImageResponse;
import com.elchefapp.elchefapp.models.models_chef.addMeal.ClassAddMealResponse;
import com.elchefapp.elchefapp.models.models_chef.chefProfile.ClassChefProfileData;
import com.elchefapp.elchefapp.models.models_chef.chefProfile.ClassChefProfileResponse;
import com.elchefapp.elchefapp.models.models_chef.city.ClassCityDatum;
import com.elchefapp.elchefapp.models.models_chef.city.ClassCityResponse;
import com.elchefapp.elchefapp.models.models_chef.login_chef.ClassChefLoginData;
import com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages.ClassChefRestaurantListsFood;
import com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages.ClassChefRestaurantListsImages;
import com.elchefapp.elchefapp.models.models_chef.restaurant_listsOf_MealsAndImages.ClassChefRestaurantListsResponse;
import com.elchefapp.elchefapp.sharedPrefrences.SharedprefManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.elchefapp.elchefapp.helper.AlbumUpload.convertToRequestBody;
import static com.elchefapp.elchefapp.helper.AlbumUpload.openAlbum;


public class ChefProfileFragment extends Fragment{

    View mView, mView2;
    ImageView foodImage;
    EditText foodName, foodPrice;
    Button addMealBtn;
    ProgressDialog progressDialog, progressDialog2;
    ConstraintLayout constrainlayout_image;
    ImageButton faceBook, twitter, insta;
    ConstraintLayout constranlayout_delivery;
    TextView restaurantName, restaurantLocation, profile_open_from, profile_open_to, restaurantPhone, restaurantType;
    ImageView restaurantPhoto;
    ClassChefLoginData restaurantData;
    ConstraintLayout constrainAddImage;
    ConstraintLayout constrain_add_desc_img;
    Button addImageBtn;
    ImageView decription_img;
    ImageButton image_add_item,sendLocation;
    Spinner spinnerCity;
    ImageButton openFromTimePicker, openToTimePicker;
    int chef_id;
    String ApiToken = "1555512087Y9Vw8PVwWhCYC97YsQDv9Z80rHO7aQyGr3lHG6NRkKUREoVNhsbt62ai7s5V";
    ConstraintLayout chef_profile_container;
    CheckBox online, happyHours, delivery;
    RecyclerAdpaterChefMeals recyclerAdpaterChefMeals;
    List<ClassChefRestaurantListsFood> foods;
    int foodId;
    int imageId;
    String facebookUrl;
    ClassChefProfileData openChefFrom,openChefTo;
    RecyclerView.ViewHolder deleteImageviewHolder;
    RecyclerView recyclerview_meals, recycler_res_image;
    DiscreteScrollView scrollView;
    List<ClassChefRestaurantListsImages> listImage;
    RecyclerAdapterRestaurantImages recyclerAdapterRestaurantImages;
    RecyclerAdapterSliderImageProfile recyclerAdapterSliderImageProfile;
    ClassChefProfileData data;
    CallbackManager callbackManager;
    LoginButton loginButton;
    List<Integer> listIds;
    int id;
    int mHour, mMinute;
    String timeFrom,timeTo;
    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();
    private ArrayList<AlbumFile> ImagesFiles_AddMeal = new ArrayList<>();
    private MultipartBody.Part photoRequestBody, photoRequestBody_AddMeal;
    Action<ArrayList<AlbumFile>> action;
    Action<ArrayList<AlbumFile>> action_AddMeal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chef_profile, container, false);

        FacebookSdk.sdkInitialize(getContext());

        image_add_item = view.findViewById(R.id.image_add_item);
        restaurantName = view.findViewById(R.id.profile_res_name);
        restaurantLocation = view.findViewById(R.id.profile_location);
        restaurantPhone = view.findViewById(R.id.profile_phone);
        restaurantType = view.findViewById(R.id.profile_type);
        restaurantPhoto = view.findViewById(R.id.profile_restaurant_image);
        faceBook = view.findViewById(R.id.face);
        profile_open_from = view.findViewById(R.id.profile_open_from);
        profile_open_to = view.findViewById(R.id.profile_open_to);
        twitter = view.findViewById(R.id.twitter);
        insta = view.findViewById(R.id.insta);
        online = view.findViewById(R.id.online);
        delivery = view.findViewById(R.id.delivery);
        happyHours = view.findViewById(R.id.happyHours);
        constranlayout_delivery = view.findViewById(R.id.constranlayout_delivery);
        constrainAddImage = view.findViewById(R.id.constrainAddImage);
        chef_profile_container = view.findViewById(R.id.chef_profile_container);
        scrollView = view.findViewById(R.id.picker);
        spinnerCity = view.findViewById(R.id.city_id);
//        openToTimePicker = view.findViewById(R.id.openToTimePicker);
//        openFromTimePicker = view.findViewById(R.id.openFromTimePicker);
        sendLocation = view.findViewById(R.id.sendLocation);

        recyclerview_meals = view.findViewById(R.id.recyclerview_meals);
        recyclerview_meals.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_res_image = view.findViewById(R.id.recycler_res_image);
        recycler_res_image.setHasFixedSize(true);
        recycler_res_image.setItemAnimator(new DefaultItemAnimator());
        recycler_res_image.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        callbackManager = CallbackManager.Factory.create();
        loginButton = view.findViewById(R.id.login_button);



        id = SharedprefManager.getInstance(getContext()).getChefData().getId();
            enableSwipeToDeleteAndUndoFood();
            getListCities(id);
//            registerFacebook(id);
            getRestaurantData(id);
            getRestaurantMeals(id);
            getImagesAndDelete(id);
            addnewItem(id);
            manipulateChefImageButtonsStatus(id);
            getSliderImages(id);


////        getTimeTo();
//        getTimeFrom();
        sendLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ChefMapActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getListCities(int Id) {
        final InterfaceService getDataService = RetrofitInstanceCities.getDataService();
        Call<ClassCityResponse> call = getDataService.getCities();
        call.enqueue(new Callback<ClassCityResponse>() {

            @Override
            public void onResponse(Call<ClassCityResponse> call, Response<ClassCityResponse> response) {
                ClassCityResponse city = response.body();
                final List<ClassCityDatum> listofCities = city.getData();
                List<String> listSpinnerNames = new ArrayList<String>();
                listIds = new ArrayList<>();
                listSpinnerNames.add("اختار المدينه");

                for (int i = 0; i < listofCities.size(); i++) {
                    listSpinnerNames.add(listofCities.get(i).getName());
                    listIds.add(listofCities.get(i).getId());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listSpinnerNames);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerCity.setAdapter(adapter);
                spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position!=0){
                            spinnerCity.getSelectedItem().toString();
                            int CityId =  spinnerCity.getSelectedItemPosition();
                            sendCityId(Id,CityId);

                            SharedPreferences sharedPref = getContext().getSharedPreferences("FileName",0);
                            SharedPreferences.Editor prefEditor = sharedPref.edit();
                            prefEditor.putInt("spinnerChoice",CityId);
                            prefEditor.commit();

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ClassCityResponse> call, Throwable t) {
                Log.i("governetmodel", "error");
            }
        });
    }
    public void sendCityId(int id, int Cityid){
        final InterfaceService getDataService = RetrofitInstanceChef.getDataService();
        Call<ClassChefProfileResponse> call = getDataService.sendCityId(ApiToken, Cityid);
        call.enqueue(new Callback<ClassChefProfileResponse>() {
            @Override
            public void onResponse(Call<ClassChefProfileResponse> call, Response<ClassChefProfileResponse> response) {
                ClassChefProfileResponse response1 = response.body();
                if (response1.getStatus()==1){
                    Log.i("city id", "sent successfully");
                }
            }
            @Override
            public void onFailure(Call<ClassChefProfileResponse> call, Throwable t) {
            }
        });

    }
    private void enableSwipeToDeleteAndUndoFood() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

                Call<ClassChefRestaurantListsResponse> call = RetrofitInstanceChef.getDataService().deleteMeal(ApiToken, foodId);
                call.enqueue(new Callback<ClassChefRestaurantListsResponse>() {
                    @Override
                    public void onResponse(Call<ClassChefRestaurantListsResponse> call, Response<ClassChefRestaurantListsResponse> response) {
                        if (response.body().getStatus() == 1) {
                            ClassChefRestaurantListsResponse response1 = response.body();
                            final int position = viewHolder.getAdapterPosition();
                            final ClassChefRestaurantListsFood item = (recyclerAdpaterChefMeals.getData().get(position));
                            recyclerAdpaterChefMeals.removeItem(position);
                            Snackbar snackbar = Snackbar.make(chef_profile_container, "Item removed from the list.", Snackbar.LENGTH_LONG);
                            snackbar.setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    recyclerAdpaterChefMeals.restoreItem(item, position);
                                    recyclerview_meals.scrollToPosition(position);
                                }
                            });

                            snackbar.setActionTextColor(Color.YELLOW);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassChefRestaurantListsResponse> call, Throwable t) {

                    }
                });

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerview_meals);
    }
    public void getRestaurantData(int id) {
        // retrieve data with shared\

        restaurantData = SharedprefManager.getInstance(getContext()).getChefData();
        chef_id = restaurantData.getId();
        restaurantName.setText(String.valueOf(restaurantData.getName()));
        restaurantLocation.setText(String.valueOf(restaurantData.getAddress()));
        profile_open_from.setText(String.valueOf(restaurantData.getOpenFrom()));
        profile_open_to.setText(String.valueOf(restaurantData.getOpenTo()));
        restaurantPhone.setText(String.valueOf(restaurantData.getPhone()));
        restaurantType.setText(String.valueOf(restaurantData.getType()));
        String url ="https://i-smart.app/demos/chef/public/";
        Glide.with(getContext()).load(url+restaurantData.getImage()).into(restaurantPhoto);




//        if (getArguments() != null) {
//            Uri imageuri = Uri.parse(getArguments().getString( "chef_image"));
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageuri);
//                restaurantPhoto.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
    public void getRestaurantMeals(int id) {
        final InterfaceService getDataService = RetrofitInstanceChef.getDataService();
        Call<ClassChefRestaurantListsResponse> call = getDataService.getRestaurantLists(ApiToken);
        call.enqueue(new Callback<ClassChefRestaurantListsResponse>() {
            @Override
            public void onResponse(Call<ClassChefRestaurantListsResponse> call, final Response<ClassChefRestaurantListsResponse> response) {
                final ClassChefRestaurantListsResponse response1 = response.body();
                if (response1.getStatus() == 1) {
                    foods = response1.getData().getFoods();
                    foodId = foods.get(0).getId();
                    recyclerAdpaterChefMeals = new RecyclerAdpaterChefMeals(foods, getContext());
                    recyclerview_meals.setAdapter(recyclerAdpaterChefMeals);

                } else {
                    Toast.makeText(getContext(), "no data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClassChefRestaurantListsResponse> call, Throwable t) {

            }
        });
    }

    public void manipulateChefImageButtonsStatus(int id) {

        online.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (online.isChecked()) {
                    changeOnlineStatus( id,isChecked, 1);
                } else {
                    changeOnlineStatus(id,isChecked, 0);
                }
            }
        });
        happyHours.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (happyHours.isChecked()) {
                    changeHoursStatus(id,isChecked, 1);
                } else {
                    changeHoursStatus(id,isChecked, 0);
                }
            }
        });
        delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (delivery.isChecked()) {
                    changeDeliveryStatus(id,isChecked, 1);
                } else {
                    changeDeliveryStatus(id,isChecked, 0);
                }
            }
        });
    }

    public void changeOnlineStatus(int id ,final boolean isChecked, final int status) {
        Call<ClassChefProfileResponse> call = RetrofitInstanceChef.getDataService().setProfileOnlineLinks(ApiToken, status);
        call.enqueue(new Callback<ClassChefProfileResponse>() {
            @Override
            public void onResponse(Call<ClassChefProfileResponse> call, Response<ClassChefProfileResponse> response) {

                ClassChefProfileResponse response1 = response.body();
                if (response1.getStatus() == 1) {
                    if (online.isChecked()) {
                        online.setChecked(isChecked);
                        online.setBackgroundResource(R.drawable.online_green);
                    } else {
                        online.setChecked(isChecked);
                        online.setBackgroundResource(R.drawable.online_red);

                    }
                }
            }

            @Override
            public void onFailure(Call<ClassChefProfileResponse> call, Throwable t) {

            }
        });
    }

    public void changeHoursStatus(int id,final boolean isChecked, final int status) {

        Call<ClassChefProfileResponse> call = RetrofitInstanceChef.getDataService().setProfileHoursLinks(ApiToken, status);
        call.enqueue(new Callback<ClassChefProfileResponse>() {
            @Override
            public void onResponse(Call<ClassChefProfileResponse> call, Response<ClassChefProfileResponse> response) {
                ClassChefProfileResponse response1 = response.body();
                if (response1.getStatus() == 1) {
                    if (happyHours.isChecked()) {
                        happyHours.setChecked(isChecked);
                        happyHours.setBackgroundResource(R.drawable.happy_green);
                    } else {
                        happyHours.setChecked(isChecked);
                        happyHours.setBackgroundResource(R.drawable.happy_red);

                    }
                } else {
                    Toast.makeText(getContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClassChefProfileResponse> call, Throwable t) {

            }
        });
    }

    public void changeDeliveryStatus(int id,final boolean isChecked, final int status) {
        Call<ClassChefProfileResponse> call = RetrofitInstanceChef.getDataService().setProfileDeliveryLinks(ApiToken, status);
        call.enqueue(new Callback<ClassChefProfileResponse>() {
            @Override
            public void onResponse(Call<ClassChefProfileResponse> call, Response<ClassChefProfileResponse> response) {
                ClassChefProfileResponse response1 = response.body();
                if (response1.getStatus() == 1) {
                    if (delivery.isChecked()) {
                        delivery.setChecked(isChecked);
                        delivery.setBackgroundResource(R.drawable.delivery_green);
                        constranlayout_delivery.setBackgroundResource(R.drawable.circle_green);
                    } else {
                        delivery.setChecked(isChecked);
                        delivery.setBackgroundResource(R.drawable.delivery_red);

                    }
                } else {
                    Toast.makeText(getContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClassChefProfileResponse> call, Throwable t) {

            }
        });
    }

    public void getSliderImages(int id) {
        final InterfaceService getDataService = RetrofitInstanceChef.getDataService();
        Call<ClassChefRestaurantListsResponse> call = getDataService.getRestaurantLists(ApiToken);
        call.enqueue(new Callback<ClassChefRestaurantListsResponse>() {
            @Override
            public void onResponse(Call<ClassChefRestaurantListsResponse> call, Response<ClassChefRestaurantListsResponse> response) {
                if (response.body() != null) {
                    ClassChefRestaurantListsResponse response1 = response.body();
                    if (response1.getStatus() == 1) {
                        listImage = response1.getData().getImages();


                        recyclerAdapterSliderImageProfile = new RecyclerAdapterSliderImageProfile(listImage, getContext());
                        scrollView.setAdapter(recyclerAdapterSliderImageProfile);

                    }
                }
            }

            @Override
            public void onFailure(Call<ClassChefRestaurantListsResponse> call, Throwable t) {

            }
        });
    }

    public void getImagesAndDelete(int id) {
        final InterfaceService getDataService = RetrofitInstanceChef.getDataService();
        Call<ClassChefRestaurantListsResponse> call = getDataService.getRestaurantLists(ApiToken);
        call.enqueue(new Callback<ClassChefRestaurantListsResponse>() {
            @Override
            public void onResponse(Call<ClassChefRestaurantListsResponse> call, Response<ClassChefRestaurantListsResponse> response) {
                ClassChefRestaurantListsResponse response1 = response.body();
                if (response1.getStatus() == 1) {
                    listImage = response1.getData().getImages();
                    imageId = listImage.get(0).getId();
                    recyclerAdapterRestaurantImages = new RecyclerAdapterRestaurantImages(getContext(), listImage,
                            new RecyclerAdapterRestaurantImages.OnItemClickListener() {
                        @Override
                        public void onItemClickAddImage(ClassChefRestaurantListsImages item) {
                            addNewImage(id);
                        }
                        @Override
                        public void onItemClickDeleteImage(ClassChefRestaurantListsImages item,final int position) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Delete image")
                                    .setMessage("do you want to delete image")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Call<ClassChefRestaurantListsResponse> call = RetrofitInstanceChef.getDataService().deleteImage(ApiToken, imageId);
                                            call.enqueue(new Callback<ClassChefRestaurantListsResponse>() {
                                                @Override
                                                public void onResponse(Call<ClassChefRestaurantListsResponse> call, Response<ClassChefRestaurantListsResponse> response) {
                                                    ClassChefRestaurantListsResponse response1 = response.body();
                                                    if (response1.getStatus() == 1) {
                                                        ClassChefRestaurantListsImages item = (recyclerAdapterRestaurantImages.getData().get(position));
                                                        recyclerAdapterRestaurantImages.removeItem(position);
                                                        Snackbar snackbar = Snackbar.make(chef_profile_container, "Item removed from the list.", Snackbar.LENGTH_LONG);
                                                        snackbar.setAction("UNDO", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                recyclerAdapterRestaurantImages.restoreItem(item, position);
                                                                recycler_res_image.scrollToPosition(position);
                                                            }
                                                        });
                                                        snackbar.setActionTextColor(Color.YELLOW);
                                                        snackbar.show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ClassChefRestaurantListsResponse> call, Throwable t) {

                                                }
                                            });
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();

                        }

                    });
                    recycler_res_image.setAdapter(recyclerAdapterRestaurantImages);
                    Log.i("Error here", "recycler");

                }
            }

            @Override
            public void onFailure(Call<ClassChefRestaurantListsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addNewImage(int id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MyAlertDialogStyle);
        mView2 = getActivity().getLayoutInflater().inflate(R.layout.add_image_layout, null);
        decription_img = mView2.findViewById(R.id.add_decription_img);
        constrain_add_desc_img = mView2.findViewById(R.id.constrain_add_desc_img);
        addImageBtn = mView2.findViewById(R.id.addImageBtn);
        progressDialog2 = new ProgressDialog(getContext());
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImageResponse(id);
            }
        });
        constrain_add_desc_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        ImagesFiles.clear();
                        ImagesFiles.addAll(result);
                        Glide.with(getActivity()).load(ImagesFiles.get(0).getPath())
                                .into(decription_img);
                    }
                };
                openAlbum(3, getActivity(), ImagesFiles, action);

            }
        });

        builder.show();
        builder.setView(mView2);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addImageResponse(int id) {
        progressDialog2.setTitle("Add new Restaurant image");
        progressDialog2.setMessage("please wait while image uploaded");
        progressDialog2.setCanceledOnTouchOutside(false);
        progressDialog2.show();
        String path = ImagesFiles.get(0).getPath();
        photoRequestBody = AlbumUpload.convertFileToMultipart(path, "img");
        InterfaceService apiInterface = RetrofitInstanceChef.getDataService();
        Call<ClassAddImageResponse> call = apiInterface.addImage(convertToRequestBody(ApiToken), photoRequestBody);
        call.enqueue(new Callback<ClassAddImageResponse>() {
            @Override
            public void onResponse(Call<ClassAddImageResponse> call, Response<ClassAddImageResponse> response) {
                if (response.body() != null) {
                    ClassAddImageResponse response1 = response.body();
                    if (response1.getStatus() == 1) {
                        progressDialog2.dismiss();
                        Intent intent = new Intent(getContext(), ChefHomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog2.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClassAddImageResponse> call, Throwable t) {
                progressDialog2.dismiss();
            }
        });
    }

    public void addnewItem(int id) {

        image_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MyAlertDialogStyle);
                mView = getActivity().getLayoutInflater().inflate(R.layout.add_item_layout, null);
                Toolbar toolbar = mView.findViewById(R.id.my_toolbar_add_item);
                foodImage = mView.findViewById(R.id.add_item_food_image);
                foodName = mView.findViewById(R.id.add_item_food_name);
                foodPrice = mView.findViewById(R.id.add_item_price_);
                addMealBtn = mView.findViewById(R.id.addMealBtn);
                constrainlayout_image = mView.findViewById(R.id.cons_image);
                progressDialog = new ProgressDialog(getContext());

                addMealBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addMeal(id);
                    }
                });
                constrainlayout_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        action_AddMeal = new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                                ImagesFiles_AddMeal.clear();
                                ImagesFiles_AddMeal.addAll(result);
                                Glide.with(getActivity()).load(ImagesFiles_AddMeal.get(0).getPath())
                                        .into(foodImage);
                            }
                        };
                        openAlbum(3, getActivity(), ImagesFiles_AddMeal, action_AddMeal);
                    }
                });
                builder.show();
                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    public void addMeal(int id) {
        String name = foodName.getText().toString();
        String price = foodPrice.getText().toString();
        if (name.isEmpty()) {
            foodName.setError("Food name is required");
            foodName.requestFocus();
            return;
        }
        if (price.isEmpty()) {
            foodPrice.setError("Food Price is required");
            foodPrice.requestFocus();
            return;
        }
        progressDialog.setTitle("Add Image");
        progressDialog.setMessage("please wait while food image");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String path = ImagesFiles_AddMeal.get(0).getPath();
        photoRequestBody_AddMeal = AlbumUpload.convertFileToMultipart(path, "img");
        InterfaceService apiInterface = RetrofitInstanceChef.getDataService();
        Call<ClassAddMealResponse> call = apiInterface.addMeal(convertToRequestBody(ApiToken),
                convertToRequestBody(name), convertToRequestBody(price), photoRequestBody_AddMeal);
        call.enqueue(new Callback<ClassAddMealResponse>() {
            @Override
            public void onResponse(Call<ClassAddMealResponse> call, Response<ClassAddMealResponse> response) {
                if (response.body() != null) {
                    ClassAddMealResponse response1 = response.body();
                    if (response1.getStatus() == 1) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(getContext(), ChefHomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClassAddMealResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public void getTimeFrom() {

        final InterfaceService getDataService = RetrofitInstanceChef.getDataService();
        Call<ClassChefProfileResponse> call = getDataService.sendOpenTimeStatus(ApiToken);
        call.enqueue(new Callback<ClassChefProfileResponse>() {
            @Override
            public void onResponse(Call<ClassChefProfileResponse> call, Response<ClassChefProfileResponse> response) {
                ClassChefProfileResponse restaurantListsResponse = response.body();
                if (restaurantListsResponse.getStatus()==1){
                    openFromTimePicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar c = Calendar.getInstance();
                            mHour = c.get(Calendar.HOUR_OF_DAY);
                            mMinute = c.get(Calendar.MINUTE);
                            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    String time=hourOfDay+":"+minute;
                                    openChefFrom.setOpenFrom(time);
                                }
                            }, mHour, mMinute, false);
                            timePickerDialog.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ClassChefProfileResponse> call, Throwable t) {

            }
        });
    }
    public void getTimeTo() {

        final InterfaceService getDataService = RetrofitInstanceChef.getDataService();
        Call<ClassChefProfileResponse> call = getDataService.sendOpenTimeStatus(ApiToken);
        call.enqueue(new Callback<ClassChefProfileResponse>() {
            @Override
            public void onResponse(Call<ClassChefProfileResponse> call, Response<ClassChefProfileResponse> response) {
                ClassChefProfileResponse restaurantListsResponse = response.body();
                if (restaurantListsResponse.getStatus()==1){
                    openToTimePicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar c = Calendar.getInstance();
                            mHour = c.get(Calendar.HOUR_OF_DAY);
                            mMinute = c.get(Calendar.MINUTE);
                            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    String time=hourOfDay+":"+minute;
                                    openChefTo.setOpenTo(time);
                                }
                            }, mHour, mMinute, false);
                            timePickerDialog.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ClassChefProfileResponse> call, Throwable t) {

            }
        });
    }

}
