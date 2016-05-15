package com.example.alex.schoolsengerapplication.models.usersData;

/**
 * Created by Alex on 14.05.2016.
 */
public class SchoolkidsData extends UsersData {
    private String classNumber;

    public SchoolkidsData(int id, String username, String firstname, String lastname, String classNumber) {
        super(id, username, firstname, lastname);
        setClassNumber(classNumber);
        role = "schoolkid";
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
