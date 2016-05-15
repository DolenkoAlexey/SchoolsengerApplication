package com.example.alex.schoolsengerapplication.asyncModels;

import android.os.AsyncTask;

import com.example.alex.schoolsengerapplication.api.GetterDialogFromServer;
import com.example.alex.schoolsengerapplication.models.message.MessagesList;
import com.example.alex.schoolsengerapplication.parsers.MessageJsonParser;
import com.example.alex.schoolsengerapplication.presenters.DialogPresenter;

/**
 * Created by Alex on 15.05.2016.
 */
public class DialogAsync extends AsyncTask<Void, Void, MessagesList> {
    private Integer idFirstUser;
    private Integer idSecondUser;

    private DialogPresenter presenter;

    public DialogAsync(Integer idFirstUser, Integer idSecondUser, DialogPresenter presenter){
        this.idFirstUser = idFirstUser;
        this.idSecondUser = idSecondUser;
        this.presenter = presenter;
    }

    @Override
    protected MessagesList doInBackground(Void... params) {
        GetterDialogFromServer getter = new GetterDialogFromServer();
        MessageJsonParser parser = new MessageJsonParser();
        return parser.parseMessagesListFromJson(getter.getDialogFromServer(idFirstUser, idSecondUser));
    }

    @Override
    protected void onPostExecute(MessagesList messagesList) {
        super.onPostExecute(messagesList);

        presenter.setMessages(messagesList);
    }
}
