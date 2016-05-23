package com.example.alex.schoolsengerapplication.asyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.api.GetterUsersDataFromServer;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.parsers.UsersDataJsonParser;
import com.example.alex.schoolsengerapplication.presenters.SearchPresenter;

/**
 * Created by Alex on 23.05.2016.
 */
public class SearchUsersAsync extends AsyncTask<Void, Void, UsersDataMapJson>{
    private String username;
    private SearchPresenter presenter;

    public SearchUsersAsync(String username, SearchPresenter presenter) {
        this.username = username;
        this.presenter = presenter;
    }

    @Override
    protected UsersDataMapJson doInBackground(Void... params) {
        GetterUsersDataFromServer getter = new GetterUsersDataFromServer();
        UsersDataJsonParser parser = new UsersDataJsonParser();

        return getter.getFoundUsersDataFromServer(username);
    }

    @Override
    protected void onPostExecute(UsersDataMapJson users) {
        super.onPostExecute(users);

        if(isEmptyMap(users)){
            presenter.setNothingFoundResult();
        }else{
            presenter.setSomethingFoundResult(users);
        }
    }

    private boolean isEmptyMap(UsersDataMapJson users) {
        return (users.getSchoolkidsData().isEmpty()
                && users.getTeachersData().isEmpty()
                && users.getSuperadminsData().isEmpty());
    }
}
