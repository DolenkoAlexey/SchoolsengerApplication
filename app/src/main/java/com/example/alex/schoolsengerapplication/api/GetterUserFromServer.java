package com.example.alex.schoolsengerapplication.api;

import com.example.alex.schoolsengerapplication.json.usersJson.SchoolkidJson;
import com.example.alex.schoolsengerapplication.json.usersJson.SuperadminJson;
import com.example.alex.schoolsengerapplication.json.usersJson.TeacherJson;
import com.example.alex.schoolsengerapplication.json.usersJson.UsersMapJson;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.parsers.UserJsonParser;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Alex on 05.05.2016.
 */
public class GetterUserFromServer {

    public User getUserFromServerSync(String email) {
        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();

        Call<UsersMapJson> call = requesterAPI.getIsUniqueEmailFromServer(email);

        Response<UsersMapJson> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        UsersMapJson usersMapJson = response.body();
        List<SchoolkidJson> schoolkidJsons = usersMapJson.getSchoolkids();
        List<TeacherJson> teacherJsons = usersMapJson.getTeachers();
        List<SuperadminJson> superadminJsons = usersMapJson.getSuperadmins();

        User user = null;
        UserJsonParser parser = new UserJsonParser();

        if(!schoolkidJsons.isEmpty()){
            user = parser.ParseUserFromJson(schoolkidJsons.get(0));
        }
        else if(!teacherJsons.isEmpty()){
            user = parser.ParseUserFromJson(schoolkidJsons.get(0));
        }
        else if (!superadminJsons.isEmpty()){
            user = parser.ParseUserFromJson(schoolkidJsons.get(0));
        }
        else {
            user = new User(); //// TODO: 15.05.2016 КОСТЫЛЬ!
        }

        return user;
    }

}
