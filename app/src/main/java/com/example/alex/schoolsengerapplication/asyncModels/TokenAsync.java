package com.example.alex.schoolsengerapplication.asyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.presenters.TokenPresenter;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by Alex on 17.05.2016.
 */
public class TokenAsync extends AsyncTask<Void, Void, String>{
    private static final String SENDER_ID = "954531636648";

    private TokenPresenter presenter;
    private InstanceID instanceID;

    public TokenAsync(InstanceID instanceID, TokenPresenter presenter) {
        this.instanceID = instanceID;
        this.presenter = presenter;
    }

    @Override
    protected String doInBackground(Void... params) {
        String token = null;
        try {
            token = instanceID.getToken(SENDER_ID,  GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    protected void onPostExecute(String token) {
        super.onPostExecute(token);

        presenter.setToken(token);
    }
}
