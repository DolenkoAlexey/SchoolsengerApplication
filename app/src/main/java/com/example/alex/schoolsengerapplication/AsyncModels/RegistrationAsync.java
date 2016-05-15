package com.example.alex.schoolsengerapplication.asyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.api.GetterUserFromServer;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.presenters.RegistrationPresenter;

import java.util.concurrent.ExecutionException;

/**
 * Created by Alex on 10.05.2016.
 */
public class RegistrationAsync extends AsyncTask<Void, String, User> {
    private String email;
    private RegistrationPresenter presenter;

    public RegistrationAsync(String email, RegistrationPresenter presenter) {
        this.email = email;
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

        try {
            if(!isUniqueEmail(user)) {
                presenter.setWrongUserDataAsyncResult();
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        presenter.setCorrectUserDataAsyncResult();
    }

    private boolean isUniqueEmail(User user) throws ExecutionException, InterruptedException {
        // Если с сервера получен пустой объект,
        // значит выборка по запрашиваемому email была пуста,
        // значит этот email уникальен и его можно использовать
        return user.getEmail() == null;
    }
}
