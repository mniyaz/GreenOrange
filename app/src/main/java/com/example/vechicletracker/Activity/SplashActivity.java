package com.example.vechicletracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.vechicletracker.R;
import com.example.vechicletracker.Util.GlobalValues;

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
