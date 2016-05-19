package com.example.alex.schoolsengerapplication.UIElements;

import com.example.alex.schoolsengerapplication.models.message.MessagesList;

/**
 * Created by Alex on 15.05.2016.
 */
public interface DialogUIElement extends UIElement {

    void setMessages(MessagesList messagesList);
    void setMessageFromInterlocutor(String message);
}
