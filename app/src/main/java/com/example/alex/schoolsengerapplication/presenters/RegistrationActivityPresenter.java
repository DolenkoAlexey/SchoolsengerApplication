package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.AsyncModels.RegistrationAsync;
import com.example.alex.schoolsengerapplication.UIElements.RegistrationUIElement;

/**
 * Created by Alex on 10.05.2016.
 */
public class RegistrationActivityPresenter extends BasePresenter<RegistrationUIElement>{
    RegistrationAsync registrator;
    static private RegistrationActivityPresenter presenter;


    private RegistrationActivityPresenter(){
    }

    public void runAsync(String email){
        registrator = new RegistrationAsync(email, this);
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

    public static RegistrationActivityPresenter getPresenter(){
        if(presenter == null)
            presenter = new RegistrationActivityPresenter();
        return presenter;
    }
}