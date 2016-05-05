package com.example.alex.schoolsengerapplication.Json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 05.05.2016.
 */
public class ResponseJson {

    @SerializedName("response")
    private boolean response;

    public ResponseJson(boolean response) {
        this.response = response;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
