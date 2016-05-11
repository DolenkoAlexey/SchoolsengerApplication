package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.activities.AuthenticationActivity;
import com.example.alex.schoolsengerapplication.AsyncModels.AuthenticationActivityAsync;
import com.example.alex.schoolsengerapplication.json.UserJson;

/**
 * Created by Alex on 10.05.2016.
 */
public class AuthenticationActivityPresenter {

    static AuthenticationActivity currentActivity;
    static AuthenticationActivityAsync loginner;

    String email;
    String pass;

    public AuthenticationActivityPresenter(String email, String pass){
        this.email = email;
        this.pass = pass;
    }

    public void runAsync(){
        loginner = new AuthenticationActivityAsync(email, pass, this);
        loginner.execute();
    }

    public static void attachView(AuthenticationActivity _currentActivity){
        currentActivity = _currentActivity;
    }

    public static void detatch(){
        currentActivity = null;
    }

    public void setWrongUserDataAsyncResult(){
        if(currentActivity!= null) {
            currentActivity.setWrongUserDataAsyncResult();
        }
    }

    public void setCorrectUserDataAsyncResult(UserJson userJson){
        if(currentActivity!= null) {
            currentActivity.setCorrectUserDataAsyncResult(userJson);
        }
    }
}
