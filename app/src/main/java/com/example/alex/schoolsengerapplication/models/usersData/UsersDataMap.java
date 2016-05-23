package com.example.alex.schoolsengerapplication.models.usersData;

import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 14.05.2016.
 */
public class UsersDataMap {
    private List<SchoolkidsData> schoolkidsData;
    private List<TeachersData> teachersData;
    private List<SuperadminsData> superadminsData;

    public UsersDataMap(){}

    public UsersDataMap(Map<Class, List<? extends UsersData>> usersData) {
        schoolkidsData = (List<SchoolkidsData>)usersData.get(SchoolkidsData.class);
        teachersData = (List<TeachersData>)usersData.get(TeachersData.class);
        superadminsData = (List<SuperadminsData>)usersData.get(SuperadminsData.class);
    }


    public void setMessages(Map<Class, List<? extends UsersData>> usersData) {
        schoolkidsData = (List<SchoolkidsData>)usersData.get(SchoolkidsData.class);
        teachersData = (List<TeachersData>)usersData.get(TeachersData.class);
        superadminsData = (List<SuperadminsData>)usersData.get(SuperadminsData.class);
    }

    public List<SchoolkidsData> getSchoolkidsData(){
        return schoolkidsData;
    }

    public List<SuperadminsData> getSuperadminsData() {
        return superadminsData;
    }

    public List<TeachersData> getTeachersData() {
        return teachersData;
    }
}
