package com.example.vechicletracker.Util;


import com.example.vechicletracker.Model.DrawerObjectResponseModel;
import com.example.vechicletracker.Model.LoginResponseModel;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET("api.php?api=user&cmd=USER_GET_OBJECTS")
    Call<ArrayList<DrawerObjectResponseModel>> calluserObject(@QueryMap Map<String, String> options);

    /*@GET("api.php?api=user&ver={version}&key={key}&cmd={command}")
    Call<LoginResponseModel> callLogin(@Path("version") String version,@Path("key") String key,@Path("command") String command);*/

    @GET
    Call<LoginResponseModel> callLogin(@Url String url);


}
