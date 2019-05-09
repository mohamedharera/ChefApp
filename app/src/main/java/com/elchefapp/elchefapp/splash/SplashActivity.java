package com.elchefapp.elchefapp.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.elchefapp.elchefapp.R;

public class SplashActivity extends AppCompatActivity {

    ImageView iv_splash;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv_splash = findViewById(R.id.iv_splash);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, IntroSliderActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }

}
