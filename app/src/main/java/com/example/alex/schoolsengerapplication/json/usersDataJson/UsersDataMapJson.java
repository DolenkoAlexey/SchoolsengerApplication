package com.example.alex.schoolsengerapplication.json.usersDataJson;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 13.05.2016.
 */
public class UsersDataMapJson {

    @SerializedName("schoolkidsDatas")
    private List<SchoolkidsDataJson> schoolkidsDataJsons;

    @SerializedName("teachersDatas")
    private List<TeachersDataJson> teachersDataJsons;

    @SerializedName("superadminsDatas")
    private List<SuperadminsDataJson> superadminsDataJsons;

    public UsersDataMapJson(Map<Class, List<? extends UsersDataJson>> usersData) {
        schoolkidsDataJsons = (List<SchoolkidsDataJson>)usersData.get(SchoolkidsDataJson.class);
        teachersDataJsons = (List<TeachersDataJson>)usersData.get(TeachersDataJson.class);
        superadminsDataJsons = (List<SuperadminsDataJson>)usersData.get(SuperadminsDataJson.class);
    }

    public List<SchoolkidsDataJson> getSchoolkidsData(){
        return schoolkidsDataJsons;
    }

    public List<SuperadminsDataJson> getSuperadminsData() {
        return superadminsDataJsons;
    }

    public List<TeachersDataJson> getTeachersData() {
        return teachersDataJsons;
    }
}
