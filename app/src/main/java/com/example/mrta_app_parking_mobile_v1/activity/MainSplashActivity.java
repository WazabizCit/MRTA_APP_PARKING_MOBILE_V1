package com.example.mrta_app_parking_mobile_v1.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;


import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

public class MainSplashActivity extends ImportantMethod {



    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 1000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                if (ActivityCompat.checkSelfPermission(MainSplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainSplashActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                    return;
                } else {

                    Intent intent = new Intent(MainSplashActivity.this, LoginMainActivity.class);
                    startActivity(intent);
                    finish();


                }
            }
        };
    }

    public void onResume() {
        super.onResume();
        //   loadPreferences();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }


}