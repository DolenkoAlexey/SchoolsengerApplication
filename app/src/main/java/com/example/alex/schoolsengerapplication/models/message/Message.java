package com.example.alex.schoolsengerapplication.models.message;

/**
 * Created by Alex on 14.05.2016.
 */
public class Message {

    private int id;
    private int idFrom;
    private int idTo;

    private String messageString;

    public Message(int idFrom, int idTo, String messageString) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.messageString = messageString;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }
}
