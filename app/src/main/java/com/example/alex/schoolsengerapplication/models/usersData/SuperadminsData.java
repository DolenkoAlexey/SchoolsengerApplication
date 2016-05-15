package com.example.alex.schoolsengerapplication.models.usersData;

/**
 * Created by Alex on 14.05.2016.
 */
public class SuperadminsData extends UsersData {
    public SuperadminsData(int id, String username, String firstname, String lastname) {
        super(id, username, firstname, lastname);
        role = "superadmin";
    }
}
