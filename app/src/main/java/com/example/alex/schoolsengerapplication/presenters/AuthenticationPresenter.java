package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.asyncModels.AuthenticationAsync;
import com.example.alex.schoolsengerapplication.UIElements.AuthenticationUIElement;
import com.example.alex.schoolsengerapplication.models.users.User;

/**
 * Created by Alex on 10.05.2016.
 */
public class AuthenticationPresenter extends BasePresenter<AuthenticationUIElement>{

    static private AuthenticationPresenter presenter;

    private AuthenticationPresenter(){
    }

    public static AuthenticationPresenter getPresenter(){
        if(presenter == null)
            presenter = new AuthenticationPresenter();
        return presenter;
    }

    public void runAsync(String email, String pass){
        AuthenticationAsync loginner = new AuthenticationAsync(email, pass, this);
        loginner.execute();
    }

    @Override
    public void attachView(AuthenticationUIElement _currentActivity){
        currentActivity = _currentActivity;
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
