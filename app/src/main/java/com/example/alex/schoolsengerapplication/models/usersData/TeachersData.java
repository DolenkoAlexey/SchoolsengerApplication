package com.example.alex.schoolsengerapplication.models.usersData;

/**
 * Created by Alex on 14.05.2016.
 */
public class TeachersData extends UsersData {
    public TeachersData(int id, String username, String firstname, String lastname) {
        super(id, username, firstname, lastname);
        role = "teacher";
    }
}
