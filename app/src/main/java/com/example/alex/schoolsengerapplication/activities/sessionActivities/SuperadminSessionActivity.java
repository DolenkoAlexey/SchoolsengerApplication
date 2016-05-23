package com.example.alex.schoolsengerapplication.activities.sessionActivities;

import android.os.Bundle;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;

import java.util.List;

public class SuperadminSessionActivity extends SessionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {
        //// TODO: 22.05.2016
    }

    @Override
    protected void mySetContentView() {
        setContentView(R.layout.activity_superadmin_session);
    }

    @Override
    protected void mySetAdapter(List<UsersData> interlocutorsData) {
        //// TODO: 22.05.2016
    }

    @Override
    void goToDialog(Integer idFirstUser, Integer idSecondUser) {
        //// TODO: 22.05.2016
    }

    @Override
    public void setNothingFound() {

    }

    @Override
    public void setSomethingFound(UsersDataMapJson usersDataMap) {

    }
}
