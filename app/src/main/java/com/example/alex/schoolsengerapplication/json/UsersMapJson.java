package com.example.alex.schoolsengerapplication.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 13.05.2016.
 */
public class UsersMapJson implements Serializable {
    @SerializedName("schoolkids")
    private List<SchoolkidJson> schoolkids;

    @SerializedName("teachers")
    private List<TeacherJson> teachers;

    @SerializedName("superadmins")
    private List<SuperadminJson> superadmins;

    public UsersMapJson(Map<Class, List<? extends UserJson>> users) {
        schoolkids = (List<SchoolkidJson>)users.get(SchoolkidJson.class);
        teachers = (List<TeacherJson>)users.get(TeacherJson.class);
        superadmins = (List<SuperadminJson>)users.get(SuperadminJson.class);
    }

    public void setMessages(Map<Class, List<? extends UserJson>> users) {
        schoolkids = (List<SchoolkidJson>)users.get(SchoolkidJson.class);
        teachers = (List<TeacherJson>)users.get(TeacherJson.class);
        superadmins = (List<SuperadminJson>)users.get(SuperadminJson.class);
    }

    public List<SchoolkidJson> getSchoolkids(){
        return schoolkids;
    }

    public List<SuperadminJson> getSuperadmins() {
        return superadmins;
    }

    public List<TeacherJson> getTeachers() {
        return teachers;
    }
}
