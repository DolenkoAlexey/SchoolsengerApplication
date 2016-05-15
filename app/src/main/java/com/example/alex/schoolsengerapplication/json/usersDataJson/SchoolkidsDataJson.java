package com.example.alex.schoolsengerapplication.json.usersDataJson;

/**
 * Created by Alex on 13.05.2016.
 */
public class SchoolkidsDataJson extends UsersDataJson {
    private String classNumber;

    public SchoolkidsDataJson(int id, String username, String firstname, String lastname, String classNumber) {
        super(id, username, firstname, lastname);
        setClassNumber(classNumber);
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
