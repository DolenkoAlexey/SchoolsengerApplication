package com.example.alex.schoolsengerapplication.models.usersData;

/**
 * Created by Alex on 14.05.2016.
 */
public abstract class UsersData {
    private int id;

    private String username;

    private String firstname;
    private String lastname;

    protected String role;

    public UsersData(int id, String username, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole(){
        return role;
    }
}
