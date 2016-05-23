package com.example.alex.schoolsengerapplication.json.usersDataJson;

import java.io.Serializable;

/**
 * Created by Alex on 13.05.2016.
 */
public class SuperadminsDataJson extends UsersDataJson implements Serializable {
    public SuperadminsDataJson(int id, String username, String firstname, String lastname) {
        super(id, username, firstname, lastname);
    }
}
