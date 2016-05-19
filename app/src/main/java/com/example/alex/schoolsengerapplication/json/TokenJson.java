package com.example.alex.schoolsengerapplication.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 17.05.2016.
 */
public class TokenJson {
    @SerializedName("emailUser")
    private String emailUser;

    @SerializedName("token")
    private String token;

    public TokenJson(String emailUser, String token) {
        this.emailUser = emailUser;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmailUser() {

        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
