package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.asyncModels.RegistrationAsync;
import com.example.alex.schoolsengerapplication.UIElements.RegistrationUIElement;

/**
 * Created by Alex on 10.05.2016.
 */
public class RegistrationPresenter extends BasePresenter<RegistrationUIElement>{
    static private RegistrationPresenter presenter;

    private RegistrationPresenter(){
    }

    public void runAsync(String email){
        RegistrationAsync registrator = new RegistrationAsync(email, this);
        registrator.execute();
    }

    @Override
    public void attachView(RegistrationUIElement _currentActivity){
        currentActivity = _currentActivity;
    }

    public void setWrongUserDataAsyncResult(){
        if(currentActivity != null){
            currentActivity.setWrongUserDataAsyncResult();
        }
    }

    public void setCorrectUserDataAsyncResult(){
        if(currentActivity != null){
            currentActivity.setCorrectUserDataAsyncResult();
        }
    }

    public static RegistrationPresenter getPresenter(){
        if(presenter == null)
            presenter = new RegistrationPresenter();
        return presenter;
    }
}