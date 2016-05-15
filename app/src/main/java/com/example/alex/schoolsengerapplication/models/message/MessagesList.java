package com.example.alex.schoolsengerapplication.models.message;

import java.util.List;

/**
 * Created by Alex on 15.05.2016.
 */
public class MessagesList {
    private List<Message> messages;

    public MessagesList(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
