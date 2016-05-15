package com.example.alex.schoolsengerapplication.json.usersJson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 12.05.2016.
 */
public class SchoolkidJson extends UserJson {
    @SerializedName("classNumber")
    private String classNumber;

    public SchoolkidJson(int id, String email, String username, String password, String firstname, String lastname, String classNumber) {
        super(id, email, username, password, firstname, lastname);
        setClassNumber(classNumber);
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
