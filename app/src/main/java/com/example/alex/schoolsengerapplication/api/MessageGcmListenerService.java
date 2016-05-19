package com.example.alex.schoolsengerapplication.api;

import android.os.Bundle;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.presenters.DialogPresenter;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Alex on 17.05.2016.
 */
public class MessageGcmListenerService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        if(!from.equals(R.string.API_KEY)){
            // TODO: 17.05.2016
        }
        String message = data.getString("message");
        DialogPresenter.getPresenter().setMessageFromInterlocutor(message);
    }

    @Override
    public void onSendError(String msgId, String error) {
        super.onSendError(msgId, error);
        throw new RuntimeException();
    }
}
