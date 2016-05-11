package com.example.alex.schoolsengerapplication;

import com.example.alex.schoolsengerapplication.json.UserJson;

import org.json.JSONObject;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RequesterAPI {

    @GET("authorization/getuser")
    Call<UserJson> getIsUniqueEmailFromServer(@Query("email") String email);


    @Headers("Content-type: application/json")
    @POST("authorization/adduser")
    Call<JSONObject> sendUserToServer(@Body UserJson user);


    @GET("session/getinterlocutors")
    Call<List<Integer>> getInterlocutorsFromServer(@Query("id") Integer id);

    class Creator {
        static RequesterAPI requesterAPI;

        public static RequesterAPI getRequester() {
            if(requesterAPI == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://schoolsenger.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                requesterAPI = retrofit.create(RequesterAPI.class);
            }
            return requesterAPI;

        }
    }
}
