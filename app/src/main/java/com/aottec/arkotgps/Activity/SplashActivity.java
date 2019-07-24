package com.aottec.arkotgps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.aottec.arkotgps.Util.GlobalValues;
import com.aottec.arkotgps.R;

public class SplashActivity extends AppCompatActivity {
GlobalValues globalValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
