package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.UIElements.UIElement;

/**
 * Created by Alex on 11.05.2016.
 */
public abstract class BasePresenter <T extends UIElement>{
    protected T currentActivity;

    abstract public void attachView(T _currentActivity);

    public void detatch(){
        currentActivity = null;
    }
}
