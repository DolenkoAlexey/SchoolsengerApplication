package com.example.alex.schoolsengerapplication;

import com.example.alex.schoolsengerapplication.Json.ResponseJson;
import com.example.alex.schoolsengerapplication.Json.UserJson;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface Requester {

        @GET("authorization/getuser")
        Call<UserJson> getIsUniqueEmailFromServer(@Query("email") String email);


        @Headers("Content-type: application/json")
        @POST("authorization/adduser")
        Call<ResponseJson> sendUserToServer(@Body UserJson user);
}
