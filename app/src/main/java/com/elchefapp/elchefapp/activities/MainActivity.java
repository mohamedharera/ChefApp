package com.elchefapp.elchefapp.activities;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.elchefapp.elchefapp.R;
import com.facebook.CallbackManager;

public class MainActivity extends AppCompatActivity {

    LinearLayout signupFaacebook,signupTwitter,signupGoogle,signupEmail;
    TextView LoginText;
    RadioButton customerRadioButton,chefRadioButton;
    RadioGroup rgDeterminde;
    int selectedType = 0;
    int myValue;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerRadioButton = findViewById(R.id.rbCustomer);
        chefRadioButton = findViewById(R.id.rbChegf);
        signupFaacebook = findViewById(R.id.ll_signup_face);
        signupTwitter = findViewById(R.id.ll_signup_twitter);
        signupGoogle = findViewById(R.id.ll_signup_google);
        signupEmail = findViewById(R.id.ll_signup_mail);
        LoginText = findViewById(R.id.LoginText);
        LoginText.setPaintFlags(LoginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        rgDeterminde = findViewById(R.id.rgDeterminde);
        callbackManager = CallbackManager.Factory.create();


        // get value from register
        myValue= getIntent().getIntExtra("go_main_to_login",0);
        // send to login page
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("login_type", myValue);
        editor.commit();


        chooseTypeSignUpEmail();
        goSignupEmail();
    }
    public void goSignupEmail(){
        signupEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedType=rgDeterminde.getCheckedRadioButtonId()== R.id.rbCustomer?1:rgDeterminde.getCheckedRadioButtonId()== R.id.rbChegf? 2 : 0;
                    if(selectedType != 0) {
                        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                        intent.putExtra("user_type", selectedType);
                        startActivity(intent);
                    }

            }
        });
    }
    public void chooseTypeSignUpEmail(){
        LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedType=rgDeterminde.getCheckedRadioButtonId()== R.id.rbCustomer?1:rgDeterminde.getCheckedRadioButtonId()== R.id.rbChegf? 2 : 0;
                if(selectedType != 0) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra("user_login_type", selectedType);
                    startActivity(intent);
                }
            }
        });
    }
}
