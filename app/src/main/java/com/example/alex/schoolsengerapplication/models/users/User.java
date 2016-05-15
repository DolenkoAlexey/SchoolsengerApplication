package com.example.alex.schoolsengerapplication.models.users;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alex on 12.05.2016.
 */
public class User implements Serializable{

    private Integer id;

    private String email;
    private String username;
    private String password;

    private String firstname;
    private String lastname;

    private List<Integer> interlocutors;

    public User(){ }

    public User(Integer id, String email, String username, String password, String firstname, String lastname){
        setId(id);
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

    public List<Integer> getInterlocutors(){ return interlocutors; }

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

    public void setInterlocutors(List<Integer> interlocutors) {
        this.interlocutors = interlocutors;
    }
}
