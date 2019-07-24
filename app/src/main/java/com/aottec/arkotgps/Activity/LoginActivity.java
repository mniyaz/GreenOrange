package com.aottec.arkotgps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import com.aottec.arkotgps.Model.LoginResponseModel;
import com.aottec.arkotgps.Util.GlobalValues;
import com.aottec.arkotgps.R;
import com.aottec.arkotgps.Util.APIClient;
import com.aottec.arkotgps.Util.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail, editPassword;
    GlobalValues globalValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        globalValues = new GlobalValues(LoginActivity.this);
        editEmail = findViewById(R.id.input_email);
        editPassword = findViewById(R.id.input_password);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEmail.getText().toString().equals("")) {

                    editEmail.setError("Please enter the username");

                } else if (editPassword.getText().toString().equals("")) {
                    editPassword.setError("Please enter the password");
                } else {
                    callLoginService();


                    // loginView.showSuccess();
                }


            }
        });

    }

    private void callLoginService() {
        ApiInterface apiService = APIClient.getClient().create(ApiInterface.class);
        String xapi = CommonUtil.ApiKey;


        String URL = "api.php?api=user&ver=3.9&key="+xapi+"&cmd="+"LOGIN_CHECK,"+editEmail.getText().toString()+","+editPassword.getText().toString();
        Call<LoginResponseModel> call = apiService.callLogin(URL );
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                String s = String.valueOf(response);
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("Success")) {
                        globalValues.put("loginStatus", "true");
                        globalValues.put("api_key", response.body().getApi_key());
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);

                        startActivity(i);
                        finish();
                    } else {
                        editEmail.setError("Wrong username or password");
                    }
                } else {
                    editEmail.setError("Wrong username or password");
                }


            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                //  progressBar.setVisibility(View.GONE);
                String error = String.valueOf(call);
            }


        });
    }


}