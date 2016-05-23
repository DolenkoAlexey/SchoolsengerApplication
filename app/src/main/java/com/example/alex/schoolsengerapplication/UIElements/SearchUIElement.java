package com.example.alex.schoolsengerapplication.UIElements;

import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;

/**
 * Created by Alex on 23.05.2016.
 */
public interface SearchUIElement extends UIElement{
    void setNothingFound();

    void setSomethingFound(UsersDataMapJson usersDataMap);
}
