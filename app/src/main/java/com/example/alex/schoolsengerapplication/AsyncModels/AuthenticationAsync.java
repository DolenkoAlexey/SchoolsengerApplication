package com.example.alex.schoolsengerapplication.AsyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.GetterUserFromServer;
import com.example.alex.schoolsengerapplication.models.User;
import com.example.alex.schoolsengerapplication.presenters.AuthenticationActivityPresenter;

/**
 * Created by Alex on 10.05.2016.
 */
public class AuthenticationAsync extends AsyncTask<Void, String, User> {
    private String email;
    private String pass;
    private AuthenticationActivityPresenter presenter;

    public AuthenticationAsync(String email, String pass, AuthenticationActivityPresenter presenter) {
        this.email = email;
        this.pass = pass;
        this.presenter = presenter;
    }

    @Override
    protected User doInBackground(Void... params) {
        GetterUserFromServer getter = new GetterUserFromServer();
        return getter.getUserFromServerSync(email);
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);

        if((user.getEmail() == null) || (!user.getPassword().equals(pass))){
            presenter.setWrongUserDataAsyncResult();
            return;
        }

        presenter.setCorrectUserDataAsyncResult(user);
    }
}