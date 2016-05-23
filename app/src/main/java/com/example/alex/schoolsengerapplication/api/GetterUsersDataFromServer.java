package com.example.alex.schoolsengerapplication.api;

import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Alex on 23.05.2016.
 */
public class GetterUsersDataFromServer {
    public UsersDataMapJson getFoundUsersDataFromServer(String username){
        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
        Call<UsersDataMapJson> call = requesterAPI.getUsersDataByUsernameFromServer(username);

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
