package com.example.alex.schoolsengerapplication.AsyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.GetterUserFromServer;
import com.example.alex.schoolsengerapplication.json.UserJson;
import com.example.alex.schoolsengerapplication.presenters.RegistrationActivityPresenter;

import java.util.concurrent.ExecutionException;

/**
 * Created by Alex on 10.05.2016.
 */
public class RegistrationAsync extends AsyncTask<Void, String, UserJson> {
    private String email;
    private RegistrationActivityPresenter presenter;

    public RegistrationAsync(String email, RegistrationActivityPresenter presenter) {
        this.email = email;
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

        try {
            if(!isUniqueEmail(userJson)) {
                presenter.setWrongUserDataAsyncResult();
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        presenter.setCorrectUserDataAsyncResult();
    }

    private boolean isUniqueEmail(UserJson userJson) throws ExecutionException, InterruptedException {
        // Если с сервера получен пустой объект,
        // значит выборка по запрашиваемому email была пуста,
        // значит этот email уникальен и его можно использовать
        return userJson.getEmail() == null;
    }
}
