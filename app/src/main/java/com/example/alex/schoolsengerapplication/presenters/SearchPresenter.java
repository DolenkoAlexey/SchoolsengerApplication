package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.UIElements.SearchUIElement;
import com.example.alex.schoolsengerapplication.asyncModels.SearchUsersAsync;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;

/**
 * Created by Alex on 23.05.2016.
 */
public class SearchPresenter extends BasePresenter<SearchUIElement>{
    static private SearchPresenter presenter;

    private SearchPresenter(){
    }

    public void runAsync(String username){
        SearchUsersAsync searcher = new SearchUsersAsync(username, this);
        searcher.execute();
    }

    public void setNothingFoundResult(){
        if(currentActivity != null){
            currentActivity.setNothingFound();
        }
    }

    public void setSomethingFoundResult(UsersDataMapJson usersDataMap){
        if(currentActivity != null){
            currentActivity.setSomethingFound(usersDataMap);
        }
    }

    public static SearchPresenter getPresenter(){
        if(presenter == null)
            presenter = new SearchPresenter();
        return presenter;
    }

    @Override
    public void attachView(SearchUIElement _currentActivity) {
        currentActivity = _currentActivity;
    }
}
