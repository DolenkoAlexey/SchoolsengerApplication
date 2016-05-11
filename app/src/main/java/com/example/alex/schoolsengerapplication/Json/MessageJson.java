package com.example.alex.schoolsengerapplication.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alex on 05.05.2016.
 */
public class MessageJson implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("idFrom")
    private int idFrom;

    @SerializedName("idTo")
    private int idTo;

    @SerializedName("messageString")
    private String messageString;

    public MessageJson(){}



    public MessageJson(int idFrom, int idTo, String messageString) {
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
