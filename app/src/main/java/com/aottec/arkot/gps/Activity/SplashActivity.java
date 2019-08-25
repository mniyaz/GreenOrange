package com.aottec.arkot.gps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.aottec.arkot.gps.R;
import com.aottec.arkot.gps.Util.GlobalValues;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;


public class SplashActivity extends AppCompatActivity {
GlobalValues globalValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(this);
        globalValues=new GlobalValues(this);


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                if(globalValues.has("loginStatus")) {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);

                    startActivity(i);
                    finish();

                }else
                {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);

                    startActivity(i);
                    finish();
                }
            }
        }, 2000);
    }

}
