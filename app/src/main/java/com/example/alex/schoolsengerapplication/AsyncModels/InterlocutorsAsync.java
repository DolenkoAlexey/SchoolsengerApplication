package com.example.alex.schoolsengerapplication.asyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.api.GetterInterlocutorsFromServer;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.presenters.InterlocutorsPresenter;

/**
 * Created by Alex on 13.05.2016.
 */
public class InterlocutorsAsync extends AsyncTask<Void, Integer, UsersDataMapJson>{
    private Integer idFrom;
    private InterlocutorsPresenter presenter;

    public InterlocutorsAsync(Integer idFrom, InterlocutorsPresenter presenter){
        this.idFrom = idFrom;
        this.presenter = presenter;
    }

    @Override
    protected UsersDataMapJson doInBackground(Void... params) {
        GetterInterlocutorsFromServer getter = new GetterInterlocutorsFromServer();
        return getter.getInterlocutorsFromServerSync(idFrom);
    }

    @Override
    protected void onPostExecute(UsersDataMapJson usersDataMapJson) {
        super.onPostExecute(usersDataMapJson);

        presenter.setInterlocutors(usersDataMapJson);
    }
}
