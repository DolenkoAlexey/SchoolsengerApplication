package com.example.alex.schoolsengerapplication.AsyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.json.UserJson;
import com.example.alex.schoolsengerapplication.GetterUserFromServer;
import com.example.alex.schoolsengerapplication.presenters.AuthenticationActivityPresenter;

/**
 * Created by Alex on 10.05.2016.
 */
public class AuthenticationActivityAsync extends AsyncTask<Void, String, UserJson> {
    private String email;
    private String pass;
    private AuthenticationActivityPresenter presenter;

    public AuthenticationActivityAsync(String email, String pass, AuthenticationActivityPresenter presenter) {
        this.email = email;
        this.pass = pass;
        this.presenter = presenter;
    }

    @Override
    protected UserJson doInBackground(Void... params) {
        GetterUserFromServer getter = new GetterUserFromServer();
        return getter.getUserFromServerSync(email);
    }

    @Override
    protected void onPostExecute(UserJson userJson) {
        super.onPostExecute(userJson);

        if((userJson.getEmail() == null) || (!userJson.getPassword().equals(pass))){
            presenter.setWrongUserDataAsyncResult();
            return;
        }

        presenter.setCorrectUserDataAsyncResult(userJson);
    }
}