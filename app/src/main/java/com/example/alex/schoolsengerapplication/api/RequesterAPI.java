package com.example.alex.schoolsengerapplication.api;

import com.example.alex.schoolsengerapplication.json.TokenJson;
import com.example.alex.schoolsengerapplication.json.messageJson.MessageJson;
import com.example.alex.schoolsengerapplication.json.messageJson.MessagesListJson;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.json.usersJson.SchoolkidJson;
import com.example.alex.schoolsengerapplication.json.usersJson.TeacherJson;
import com.example.alex.schoolsengerapplication.json.usersJson.UsersMapJson;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RequesterAPI {

    @GET("authorization/user")
    Call<UsersMapJson> getIsUniqueEmailFromServer(@Query("email") String email);

    @Headers("Content-type: application/json")
    @POST("authorization/schoolkid")
    Call<JSONObject> sendSchoolkidToServer(@Body SchoolkidJson schoolkid);

    @Headers("Content-type: application/json")
    @POST("authorization/teacher")
    Call<JSONObject> sendTeacherToServer(@Body TeacherJson teacher);

    @GET("session/interlocutors")
    Call<UsersDataMapJson> getInterlocutorsFromServer(@Query("id") Integer id);

    @GET("session/dialog")
    Call<MessagesListJson> getDialogFromServer(@Query("idFirstUser") Integer idFirstUser,
                                               @Query("idSecondUser") Integer idSecondUser);

    @Headers("Content-type: application/json")
    @POST("session/message")
    Call<JSONObject> sendMessageToServer(@Body MessageJson message);

    @Headers("Content-type: application/json")
    @POST("session/refreshtoken")
    Call<JSONObject> refreshToken(@Body TokenJson tokenJson);

    @GET("session/token")
    Call<TokenJson> getTokenFromServerByEmail(@Query("emailUser") String emailUser);

    @GET("session/byusername")
    Call<UsersDataMapJson> getUsersDataByUsernameFromServer(@Query("username") String username);

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
