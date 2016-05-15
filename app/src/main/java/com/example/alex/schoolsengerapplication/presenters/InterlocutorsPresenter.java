package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.asyncModels.InterlocutorsAsync;
import com.example.alex.schoolsengerapplication.UIElements.FriendListUIElement;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;

/**
 * Created by Alex on 13.05.2016.
 */
public class InterlocutorsPresenter extends BasePresenter<FriendListUIElement> {
    static private InterlocutorsPresenter presenter;

    private InterlocutorsPresenter(){
    }

    public static InterlocutorsPresenter getPresenter(){
        if(presenter == null)
            presenter = new InterlocutorsPresenter();
        return presenter;
    }

    @Override
    public void attachView(FriendListUIElement _currentActivity) {
        currentActivity = _currentActivity;
    }

    public void runAsync(Integer idFrom){
        InterlocutorsAsync registrator = new InterlocutorsAsync(idFrom, this);
        registrator.execute();
    }

    public void setInterlocutors(UsersDataMapJson usersData){
        if(currentActivity != null){
            currentActivity.setInterlocutors(usersData);
        }
    }
}
