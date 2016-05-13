package com.example.alex.schoolsengerapplication.models;

/**
 * Created by Alex on 12.05.2016.
 */
public class Schoolkid extends User{
    private String classNumber;

    public Schoolkid(int id, String email, String username, String password, String firstname, String lastname, String classNumber) {
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
