package com.example.alex.schoolsengerapplication;

import com.example.alex.schoolsengerapplication.Json.UserJson;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Alex on 05.05.2016.
 */
public class GetterUserFromServer {

    public UserJson getUserFromServerSync(String email) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://schoolsenger.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Requester requester = retrofit.create(Requester.class);
        Call<UserJson> call = requester.getIsUniqueEmailFromServer(email);

        Response<UserJson> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.body();
    }

}
