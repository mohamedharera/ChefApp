package com.elchefapp.elchefapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.InterfaceService;
import com.elchefapp.elchefapp.Services.RetrofitInstanceChef;
import com.elchefapp.elchefapp.Services.RetrofitInstanceClient;
import com.elchefapp.elchefapp.models.models_chef.login_chef.ClassChefLoginResponse;
import com.elchefapp.elchefapp.models.models_client.login.ClassCustomerLoginResponse;
import com.elchefapp.elchefapp.sharedPrefrences.SharedprefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginGoBtn;
    private EditText loginEmail;
    private EditText loginPass;
    ImageButton loginFacebook;
    ImageButton loginTwitter;
    ImageButton loginGmail;
    int user_login_type;
    ProgressDialog progressDialog;
    int myIntValue;
    ConstraintLayout login_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginEmail = findViewById(R.id.loginEmail);
        loginPass = findViewById(R.id.loginPass);
        loginFacebook = findViewById(R.id.login_facebook);
        loginTwitter = findViewById(R.id.login_twitter);
        loginGmail = findViewById(R.id.login_gmail);
        loginGoBtn = findViewById(R.id.login_go_btn);
        login_content = findViewById(R.id.login_content);
        progressDialog = new ProgressDialog(this);

        // user type sent from MainActivity and Register
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        myIntValue = sp.getInt("login_type", 0);
        user_login_type = getIntent().getIntExtra("user_login_type", 0);

        keepUserLogin();
        startLogin();
    }

    public void startLogin() {

        loginGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = loginEmail.getText().toString();
                final String pass = loginPass.getText().toString();

                if (email.isEmpty()) {
                    loginEmail.setError("phone is required");
                    loginEmail.requestFocus();
                    return;
                }
                if (pass.isEmpty()) {
                    loginPass.setError("password is required");
                    loginPass.requestFocus();
                    return;
                }

                if (user_login_type == 1 || myIntValue == 1) {
                    progressDialog.setTitle("Login Account");
                    progressDialog.setTitle("please wait while we are login");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();
                    InterfaceService getDataService = RetrofitInstanceClient.getDataService();
                    Call<ClassCustomerLoginResponse> call = getDataService.customerLogin(email, pass);
                    call.enqueue(new Callback<ClassCustomerLoginResponse>() {
                        @Override
                        public void onResponse(Call<ClassCustomerLoginResponse> call, Response<ClassCustomerLoginResponse> response) {

                            ClassCustomerLoginResponse response1 = response.body();
                           if (response1.getStatus()==1){
                               SharedprefManager.setLoggedIn(getApplicationContext(), true);
                               progressDialog.dismiss();
                               SharedprefManager.getInstance(LoginActivity.this).saveUserData(response1.getData());
                               Intent intent = new Intent(LoginActivity.this, CustomerHomeActivity.class);
                               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               startActivity(intent);
                           }else {
                               Toast.makeText(getApplicationContext(), "please enter correct customerRestaurantDetailsData", Toast.LENGTH_SHORT).show();
                               progressDialog.dismiss();
                           }

                        }

                        @Override
                        public void onFailure(Call<ClassCustomerLoginResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "please enter correct customerRestaurantDetailsData", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });


                } else if (user_login_type == 2 || myIntValue == 2) {
                    progressDialog.setTitle("Login Account");
                    progressDialog.setTitle("please wait while we are login");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();
                    InterfaceService getDataService = RetrofitInstanceChef.getDataService();
                    Call<ClassChefLoginResponse> call = getDataService.chefLogin(email, pass);
                    call.enqueue(new Callback<ClassChefLoginResponse>() {
                        @Override
                        public void onResponse(Call<ClassChefLoginResponse> call, Response<ClassChefLoginResponse> response) {
                            ClassChefLoginResponse response1 = response.body();
                            if(response1.getStatus()==1){
                                SharedprefManager.setLoggedIn(getApplicationContext(), true);
                                progressDialog.dismiss();
                                SharedprefManager.getInstance(LoginActivity.this).saveChefData(response1.getData());
                                Intent intent = new Intent(LoginActivity.this, ChefHomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "please enter correct customerRestaurantDetailsData", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ClassChefLoginResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "please enter correct customerRestaurantDetailsData", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }

            }
        });

    }

    public void keepUserLogin(){
        if(SharedprefManager.getLoggedStatus(getApplicationContext())) {
            if (user_login_type == 1 || myIntValue == 1) {
                Intent intent = new Intent(getApplicationContext(), CustomerHomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else if (user_login_type == 2 || myIntValue == 2){
                Intent intent = new Intent(getApplicationContext(), ChefHomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        } else {
            login_content.setVisibility(View.VISIBLE);
        }
    }


//    public void openCustomerProfile(){
//        CustomerProfileFragment fr = new CustomerProfileFragment();
//        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
//        dataFromActivityToFragment = (DataFromActivityToFragment) fr;
//        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        fragmentTransaction.replace(R.id.login_content, fr);
//        SharedprefManager.getInstance(LoginActivity.this).saveUserData(response1.getData());
//        int id = SharedprefManager.getInstance(LoginActivity.this).getUserData().getId();
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//        final Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            public void run() {
//                dataFromActivityToFragment.sendData(id);
//            }
//        };
//
//        handler.postDelayed(r, 5000);
//    }

//    public void openChefProfile(){
//        ChefProfileFragment fr = new ChefProfileFragment();
//        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
//        dataFromActivityToFragment = (DataFromActivityToFragment) fr;
//        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        fragmentTransaction.replace(R.id.login_content, fr);
//        SharedprefManager.getInstance(LoginActivity.this).saveUserData(response1.getData());
//        int id = SharedprefManager.getInstance(LoginActivity.this).getUserData().getId();
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//        final Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            public void run() {
//                dataFromActivityToFragment.sendData(id);
//            }
//        };
//
//        handler.postDelayed(r, 5000);
//    }

}

