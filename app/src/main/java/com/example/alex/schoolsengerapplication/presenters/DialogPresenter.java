package com.example.alex.schoolsengerapplication.presenters;

import com.example.alex.schoolsengerapplication.UIElements.DialogUIElement;
import com.example.alex.schoolsengerapplication.asyncModels.DialogAsync;
import com.example.alex.schoolsengerapplication.models.message.MessagesList;

/**
 * Created by Alex on 15.05.2016.
 */
public class DialogPresenter extends BasePresenter<DialogUIElement> {
    static private DialogPresenter presenter;

    private DialogPresenter(){
    }

    public static DialogPresenter getPresenter(){
        if(presenter == null)
            presenter = new DialogPresenter();
        return presenter;
    }

    @Override
    public void attachView(DialogUIElement _currentActivity) {
        currentActivity = _currentActivity;
    }

    public void runAsync(Integer idFirstUser, Integer idSecondUser){
        DialogAsync dialog = new DialogAsync(idFirstUser, idSecondUser, this);
        dialog.execute();
    }

    public void setMessages(MessagesList messagesList){
        if(currentActivity != null){
            currentActivity.setMessages(messagesList);
        }
    }

    public void setMessageFromInterlocutor(String message) {
        currentActivity.setMessageFromInterlocutor(message);
    }
}
