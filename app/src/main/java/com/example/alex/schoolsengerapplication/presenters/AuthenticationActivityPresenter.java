package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.AsyncModels.AuthenticationAsync;
import com.example.alex.schoolsengerapplication.activities.AuthenticationActivity;
import com.example.alex.schoolsengerapplication.models.User;

/**
 * Created by Alex on 10.05.2016.
 */
public class AuthenticationActivityPresenter {

    static AuthenticationActivity currentActivity;
    static AuthenticationAsync loginner;

    String email;
    String pass;

    public AuthenticationActivityPresenter(String email, String pass){
        this.email = email;
        this.pass = pass;
    }

    public void runAsync(){
        loginner = new AuthenticationAsync(email, pass, this);
        loginner.execute();
    }

    public static void attachView(AuthenticationActivity _currentActivity){
        currentActivity = _currentActivity;
    }

    public static void detatch(){
        currentActivity = null;
    }

    public void setWrongUserDataAsyncResult(){
        if(currentActivity != null) {
            currentActivity.setWrongUserDataAsyncResult();
        }
    }

    public void setCorrectUserDataAsyncResult(User user){
        if(currentActivity!= null) {
            currentActivity.setCorrectUserDataAsyncResult(user);
        }
    }
}
