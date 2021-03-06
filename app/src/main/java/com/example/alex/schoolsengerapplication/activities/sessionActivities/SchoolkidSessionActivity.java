package com.example.alex.schoolsengerapplication.activities.sessionActivities;

import android.content.Intent;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.adapter.SessionAdapter;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;

import java.util.List;

public class SchoolkidSessionActivity extends SessionActivity{

    @Override
    protected void mySetAdapter(List<UsersData> interlocutorsData) {
        friendsListView.setAdapter(new SessionAdapter(interlocutorsData, this));
    }

    @Override
    void goToDialog(Integer idFirstUser, Integer idSecondUser) {
        Intent intent = new Intent(SchoolkidSessionActivity.this, DialogActivity.class);
        intent.putExtra("idCurrentUser", idFirstUser);
        intent.putExtra("idSecondUser", idSecondUser);

        startActivity(intent);
    }

    @Override
    protected void mySetContentView() {
        setContentView(R.layout.activity_schoolkid_session);
    }

    @Override
    public void setNothingFound() {
        Toast.makeText(this,
                "Ничего не найдено =(",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSomethingFound(UsersDataMapJson usersDataMap) {

        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra("currentUser", currentUser);
        intent.putExtra("usersDataMap", usersDataMap);
        startActivity(intent);
    }
}
