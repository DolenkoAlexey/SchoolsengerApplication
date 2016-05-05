package com.example.alex.schoolsengerapplication.Json;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;


public class UserJson {

    @SerializedName("id")
    private int id;

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

    @SerializedName("character")
    private String character;

    public UserJson(){ }

    public UserJson(HashMap<String, String> user){
        setEmail(user.get("email"));
        setUsername(user.get("username"));
        setPassword(user.get("password"));
        setFirstname(user.get("firstname"));
        setLastname(user.get("lastname"));
        setCharacter(user.get("character"));
    }

    public UserJson(String email, String username, String password, String firstname, String lastname, String character){
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setFirstname(firstname);
        setLastname(lastname);
        setCharacter(character);
    }

    public int getId(){
        return id;
    }

    public String getCharacter() {
        return character;
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

    public void setId(int id){
        this.id = id;
    }

    public void setCharacter(String character) {
        this.character = character;
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
