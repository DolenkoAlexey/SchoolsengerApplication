package com.example.alex.schoolsengerapplication;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Response;


public class GetterInterlocutorsFromServer {
    public List<Integer> getInterlocutorsFromServerSync(Integer idFrom){

        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
        Call<List<Integer>> call = requesterAPI.getInterlocutorsFromServer(idFrom);

        Response<List<Integer>> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.body();
    }
}
