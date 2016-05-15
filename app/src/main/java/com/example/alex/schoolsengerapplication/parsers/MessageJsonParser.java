package com.example.alex.schoolsengerapplication.parsers;

import com.example.alex.schoolsengerapplication.json.messageJson.MessageJson;
import com.example.alex.schoolsengerapplication.json.messageJson.MessagesListJson;
import com.example.alex.schoolsengerapplication.models.message.Message;
import com.example.alex.schoolsengerapplication.models.message.MessagesList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 12.05.2016.
 */
public class MessageJsonParser {
    public Message parseMessageFromJson(MessageJson json){
        return new Message(json.getIdFrom(), json.getIdTo(), json.getMessageString());
    }

    public MessageJson parseMessageToJson(Message json){
        return new MessageJson(json.getIdFrom(), json.getIdTo(), json.getMessageString());
    }

    public MessagesList parseMessagesListFromJson(MessagesListJson messagesListJson){
        List<Message> message = new ArrayList<>();
        for(MessageJson json: messagesListJson.getMessages()){
            message.add(parseMessageFromJson(json));
        }

        return new MessagesList(message);
    }
}
