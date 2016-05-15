package com.example.alex.schoolsengerapplication.models.users;

import com.example.alex.schoolsengerapplication.models.users.User;

/**
 * Created by Alex on 12.05.2016.
 */
public class Teacher extends User {
    public Teacher(int id, String email, String username, String password, String firstname, String lastname) {
        super(id, email, username, password, firstname, lastname);
    }
}
