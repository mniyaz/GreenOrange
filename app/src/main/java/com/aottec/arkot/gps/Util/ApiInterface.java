package com.aottec.arkot.gps.Util;


import com.aottec.arkot.gps.Model.DrawerObjectResponseModel;
import com.aottec.arkot.gps.Model.LoginResponseModel;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET("api.php?api=user&cmd=USER_GET_OBJECTS")
    Call<ArrayList<DrawerObjectResponseModel>> calluserObject(@QueryMap Map<String, String> options);

    /*@GET("api.php?api=user&ver={version}&key={key}&cmd={command}")
    Call<LoginResponseModel> callLogin(@Path("version") String version,@Path("key") String key,@Path("command") String command);*/

    @GET
    Call<LoginResponseModel> callLogin(@Url String url);

    @GET
    Call<LoginResponseModel> upDateFCM(@Url String url);


}
