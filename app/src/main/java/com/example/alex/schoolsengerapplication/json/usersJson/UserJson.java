package com.example.alex.schoolsengerapplication.json.usersJson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UserJson implements Serializable{

    @SerializedName("id")
    private Integer id;

    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;


    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;

    public UserJson(){ }

    public UserJson(Integer id, String email, String username, String password, String firstname, String lastname){
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setFirstname(firstname);
        setLastname(lastname);
    }

    public Integer getId(){
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
