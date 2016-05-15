package com.example.alex.schoolsengerapplication.json.messageJson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alex on 15.05.2016.
 */
public class MessagesListJson {
    @SerializedName("messages")
    private List<MessageJson> messages;

    public MessagesListJson(List<MessageJson> messages) {
        this.messages = messages;
    }

    public List<MessageJson> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageJson> messages) {
        this.messages = messages;
    }
}
