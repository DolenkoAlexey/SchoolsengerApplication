package com.example.alex.schoolsengerapplication.parsers;

import com.example.alex.schoolsengerapplication.json.usersDataJson.SchoolkidsDataJson;
import com.example.alex.schoolsengerapplication.json.usersDataJson.SuperadminsDataJson;
import com.example.alex.schoolsengerapplication.json.usersDataJson.TeachersDataJson;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.models.usersData.SchoolkidsData;
import com.example.alex.schoolsengerapplication.models.usersData.SuperadminsData;
import com.example.alex.schoolsengerapplication.models.usersData.TeachersData;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;
import com.example.alex.schoolsengerapplication.models.usersData.UsersDataMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 14.05.2016.
 */
public class UsersDataJsonParser {
    public SchoolkidsData parseUsersDataFromJson(SchoolkidsDataJson json){
        return new SchoolkidsData(json.getId(), json.getUsername(),
                json.getFirstname(), json.getLastname(),
                json.getClassNumber());
    }

    public TeachersData parseUsersDataFromJson(TeachersDataJson json){
        return new TeachersData(json.getId(), json.getUsername(),
                json.getFirstname(), json.getLastname());
    }

    public SuperadminsData parseUsersDataFromJson(SuperadminsDataJson json){
        return new SuperadminsData(json.getId(), json.getUsername(),
                json.getFirstname(), json.getLastname());
    }

    public UsersDataMap parseUsersDataMapFromJson(UsersDataMapJson json){
        Map<Class, List<? extends UsersData>> userMap = new HashMap<>();

        List<SchoolkidsData> schoolkidsData = new ArrayList<>();
        List<TeachersData> teachersData = new ArrayList<>();
        List<SuperadminsData> superadminsData = new ArrayList<>();


        List<SchoolkidsDataJson> schoolkidsDataJson = json.getSchoolkidsData();
        List<TeachersDataJson> teachersDataJsons = json.getTeachersData();
        List<SuperadminsDataJson> superadminsDataJsons = json.getSuperadminsData();

        for (SchoolkidsDataJson data: schoolkidsDataJson) {
            schoolkidsData.add(parseUsersDataFromJson(data));
        }

        for (TeachersDataJson data: teachersDataJsons) {
            teachersData.add(parseUsersDataFromJson(data));
        }

        for (SuperadminsDataJson data: superadminsDataJsons) {
            superadminsData.add(parseUsersDataFromJson(data));
        }

        userMap.put(SchoolkidsData.class, schoolkidsData);
        userMap.put(TeachersData.class, teachersData);
        userMap.put(SuperadminsData.class, superadminsData);

        return new UsersDataMap(userMap);
    }
}
