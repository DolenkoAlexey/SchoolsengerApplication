package com.example.alex.schoolsengerapplication.api;

import com.example.alex.schoolsengerapplication.json.messageJson.MessagesListJson;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Alex on 15.05.2016.
 */
public class GetterDialogFromServer {
    public MessagesListJson getDialogFromServer(Integer idFirstUser, Integer idSecondUser){

        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
        Call<MessagesListJson> call = requesterAPI.getDialogFromServer(idFirstUser, idSecondUser);

        Response<MessagesListJson> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.body();
    }
}
