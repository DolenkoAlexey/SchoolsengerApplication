package com.example.alex.schoolsengerapplication.UIElements;

import com.example.alex.schoolsengerapplication.models.users.User;

/**
 * Created by Alex on 13.05.2016.
 */
public interface AuthenticationUIElement extends UIElement{
    void setCorrectUserDataAsyncResult(User user);

    void setWrongUserDataAsyncResult();
}
