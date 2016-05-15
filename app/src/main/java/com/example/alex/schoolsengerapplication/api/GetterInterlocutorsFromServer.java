package com.example.alex.schoolsengerapplication.api;

import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;


public class GetterInterlocutorsFromServer {
    public UsersDataMapJson getInterlocutorsFromServerSync(Integer idFrom){

        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
        Call<UsersDataMapJson> call = requesterAPI.getInterlocutorsFromServer(idFrom);

        Response<UsersDataMapJson> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.body();
    }
}
