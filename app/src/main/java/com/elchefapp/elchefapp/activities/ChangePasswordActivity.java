package com.elchefapp.elchefapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.InterfaceService;
import com.elchefapp.elchefapp.Services.RetrofitInstanceClient;
import com.elchefapp.elchefapp.models.models_client.changePassword.ClassChangePasswordData;
import com.elchefapp.elchefapp.models.models_client.changePassword.ClassChangePsswordResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText oldPass,newPass;
    Button ChangePass;
    ProgressDialog progressDialog;
    String ApiToken="fP5MExFbHNx0Vk5IlWpgI5EUtp4GmeZejn9Qs1BXGzaXwyZBeqUsNL10iTCy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        progressDialog = new ProgressDialog(this);

        oldPass = findViewById(R.id.oldPass);
        newPass = findViewById(R.id.newPass);
        ChangePass = findViewById(R.id.ChangePass);
        ChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String old_pass = oldPass.getText().toString();
        String new_pass = oldPass.getText().toString();
        if (old_pass.isEmpty()) {
            oldPass.setError("old password is required");
            oldPass.requestFocus();
            return;
        }
        if (new_pass.isEmpty()) {
            oldPass.setError("new password is required");
            oldPass.requestFocus();
            return;
        }
        if (old_pass.equals(newPass)) {
            Toast.makeText(this, "the new password must be different", Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.setTitle("Change Password");
            progressDialog.setTitle("please wait while password is changed");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();

            InterfaceService getDataService = RetrofitInstanceClient.getDataService();
            Call<ClassChangePsswordResponse> call = getDataService.changePassword(ApiToken,old_pass, new_pass);
            call.enqueue(new Callback<ClassChangePsswordResponse>() {
                @Override
                public void onResponse(Call<ClassChangePsswordResponse> call, Response<ClassChangePsswordResponse> response) {
                    ClassChangePsswordResponse response1 = response.body();
                    if (response1.getStatus()==1){
                        ClassChangePasswordData data = response1.getData();
                        progressDialog.dismiss();
                        Intent intent = new Intent(ChangePasswordActivity.this,CustomerHomeActivity.class);
                        startActivity(intent);

                        Toast.makeText(ChangePasswordActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ClassChangePsswordResponse> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }

    }
}
