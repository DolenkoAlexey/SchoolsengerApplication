package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.UIElements.TokenUIElement;
import com.example.alex.schoolsengerapplication.asyncModels.TokenAsync;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by Alex on 17.05.2016.
 */
public class TokenPresenter extends  BasePresenter<TokenUIElement>{
    static private TokenPresenter presenter;

    private TokenPresenter(){
    }

    public static TokenPresenter getPresenter(){
        if(presenter == null)
            presenter = new TokenPresenter();
        return presenter;
    }

    public void runAsync(InstanceID instanceID){
        TokenAsync tokenAsync = new TokenAsync(instanceID, this);
        tokenAsync.execute();
    }

    @Override
    public void attachView(TokenUIElement _currentActivity) {
        currentActivity = _currentActivity;
    }

    public void setToken(String token){
        currentActivity.setToken(token);
    }


}
