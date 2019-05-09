package com.elchefapp.elchefapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.InterfaceService;
import com.elchefapp.elchefapp.Services.RetrofitInstanceChef;
import com.elchefapp.elchefapp.Services.RetrofitInstanceClient;
import com.elchefapp.elchefapp.helper.AlbumUpload;
import com.elchefapp.elchefapp.models.models_chef.register_chef.ClassChefRegisterResponse;
import com.elchefapp.elchefapp.models.models_client.register.ClassCustomerRegisterResponse;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static com.elchefapp.elchefapp.helper.AlbumUpload.convertToRequestBody;
import static com.elchefapp.elchefapp.helper.AlbumUpload.openAlbum;

public class RegisterActivity extends AppCompatActivity {

    Button registerGoBtn;
    private EditText etChefUserName;
    private EditText etChefEmail;
    private EditText etChefPass;
    private EditText etChefConfirm;
    private EditText et_chef_mobile;
    private EditText et_chef_address;
    ImageView userImage;
    TextView register_LoginText;
    int myValue;

    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();
    private MultipartBody.Part photoRequestBody_client,photoRequestBody_chef;
    Action<ArrayList<AlbumFile>> action;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerGoBtn = findViewById(R.id.register_customer_go_btn);
        userImage = findViewById(R.id.user_photo);
        etChefUserName = findViewById(R.id.et_customer_userName);
        etChefEmail = findViewById(R.id.et_customer_email);
        etChefPass = findViewById(R.id.et_custmer__pass);
        etChefConfirm = findViewById(R.id.et_customer_confirm);
        et_chef_mobile = findViewById(R.id.et_customer_mobile);
        et_chef_address = findViewById(R.id.et_customer_address);
        register_LoginText = findViewById(R.id.register_LoginText);
        register_LoginText.setPaintFlags(register_LoginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        progressDialog = new ProgressDialog(this);

        myValue= getIntent().getIntExtra("user_type",0);
        Log.i("MyValue",""+myValue);

        clickImage();
        makeRegister();

    }


    public void makeRegister(){

        registerGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = etChefUserName.getText().toString();
                String userEmail = etChefEmail.getText().toString();
                String userPass = etChefPass.getText().toString();
                String userConfirm_pass = etChefConfirm.getText().toString();
                String userMobile= et_chef_mobile.getText().toString();
                String userAddress = et_chef_address.getText().toString();

                if (userName.isEmpty()) {
                    etChefUserName.setError("Name is required");
                    etChefUserName.requestFocus();
                    return;
                }
                if (userEmail.isEmpty()) {
                    etChefEmail.setError("Email is required");
                    etChefEmail.requestFocus();
                    return;
                }
                if (userPass.isEmpty()) {
                    etChefPass.setError("Passowrd is required");
                    etChefPass.requestFocus();
                    return;
                }
                if (userConfirm_pass.isEmpty()) {
                    etChefConfirm.setError("Confirm Password  is required");
                    etChefConfirm.requestFocus();
                    return;
                }
                if (userMobile.isEmpty()) {
                    et_chef_mobile.setError("Mobile is required");
                    et_chef_mobile.requestFocus();
                    return;
                }
                if (userAddress.isEmpty()) {
                    et_chef_address.setError("Address is required");
                    et_chef_address.requestFocus();
                    return;
                }

                if(!userPass.equals(userConfirm_pass)){
                    Toast.makeText(getApplicationContext(),"Password Not matching",Toast.LENGTH_SHORT).show();
                }

                if (myValue==1){

                    progressDialog.setTitle("Sign up");
                    progressDialog.setMessage("please wait while account is registered");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    String path = ImagesFiles.get(0).getPath();
                    photoRequestBody_client = AlbumUpload.convertFileToMultipart(path, "img");
                    InterfaceService apiInterface = RetrofitInstanceClient.getDataService();
                    Call<ClassCustomerRegisterResponse> call = apiInterface.customerRegister(convertToRequestBody(userName),
                            convertToRequestBody(userEmail), convertToRequestBody(userPass), convertToRequestBody(userMobile)
                            ,convertToRequestBody(userAddress),photoRequestBody_client);
                    call.enqueue(new Callback<ClassCustomerRegisterResponse>() {
                        @Override
                        public void onResponse(Call<ClassCustomerRegisterResponse> call, Response<ClassCustomerRegisterResponse> response) {

                            if (response.body() != null) {
                                ClassCustomerRegisterResponse registerResponse = response.body();
                                if (registerResponse.getStatus()==1){
                                    Toast.makeText(RegisterActivity.this, "you register successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    intent.putExtra("go_main_to_login", myValue);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_SHORT).show();

                                }else {
                                    progressDialog.dismiss();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ClassCustomerRegisterResponse> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.toString());
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }

                else if (myValue==2){

                    progressDialog.setTitle("Sign up");
                    progressDialog.setMessage("please wait while account is registered");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    String path = ImagesFiles.get(0).getPath();
                    photoRequestBody_chef = AlbumUpload.convertFileToMultipart(path, "img");

                    InterfaceService apiInterface = RetrofitInstanceChef.getDataService();
                    Call<ClassChefRegisterResponse> call = apiInterface.chefRegister(convertToRequestBody(userName),
                            convertToRequestBody(userEmail), convertToRequestBody(userPass), convertToRequestBody(userMobile)
                            ,convertToRequestBody(userAddress),photoRequestBody_chef);
                    call.enqueue(new Callback<ClassChefRegisterResponse>() {
                        @Override
                        public void onResponse(Call<ClassChefRegisterResponse> call, Response<ClassChefRegisterResponse> response) {

                            if (response.body() != null) {
                                ClassChefRegisterResponse registerResponse = response.body();
                                if (registerResponse.getStatus()==1){
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "you register successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    intent.putExtra("go_main_to_login", myValue);
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    progressDialog.dismiss();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ClassChefRegisterResponse> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.toString());
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });
    }
    public void clickImage(){
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        ImagesFiles.clear();
                        ImagesFiles.addAll(result);
                        Glide.with(getApplicationContext()).load(ImagesFiles.get(0).getPath())
                                .into(userImage);
                    }
                };
                openAlbum(3, RegisterActivity.this, ImagesFiles, action);
            }
        });
    }


}



