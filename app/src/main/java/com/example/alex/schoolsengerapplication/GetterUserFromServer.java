package com.example.alex.schoolsengerapplication;

import com.example.alex.schoolsengerapplication.json.UserJson;

/**
 * Created by Alex on 05.05.2016.
 */
public class GetterUserFromServer {

    public UserJson getUserFromServerSync(String email) {
//        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
//
//        Call<UserJson> call = requesterAPI.getIsUniqueEmailFromServer(email);
//
//        Response<UserJson> response = null;
//        try {
//            response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        assert response != null;
//        return response.body();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new UserJson();
    }

}
