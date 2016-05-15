package com.example.alex.schoolsengerapplication.activities.sessionActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.UIElements.FriendListUIElement;
import com.example.alex.schoolsengerapplication.adapter.FriendListAdapter;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;
import com.example.alex.schoolsengerapplication.models.usersData.UsersDataMap;
import com.example.alex.schoolsengerapplication.parsers.UsersDataJsonParser;
import com.example.alex.schoolsengerapplication.presenters.InterlocutorsPresenter;

import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends AppCompatActivity implements FriendListUIElement{
    ListView friendsListView;

    InterlocutorsPresenter presenter;

    User currentUser;
    private List<UsersData> interlocutorsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        initUI();

        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        getInterlocutors();
    }

    private void getInterlocutors() {
        presenter = InterlocutorsPresenter.getPresenter();
        presenter.attachView(this);

        presenter.runAsync(currentUser.getId());
    }

    private void initUI(){
        friendsListView = (ListView) findViewById(R.id.friendsListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detatch();
    }

    @Override
    public void setInterlocutors(UsersDataMapJson interlocutorsDataMapJson) {
        UsersDataJsonParser parser = new UsersDataJsonParser();
        UsersDataMap interlocutorsDataMap = parser.parseUsersDataMapFromJson(interlocutorsDataMapJson);

        initInterlocutorsData(interlocutorsDataMap);

        friendsListView.setAdapter(new FriendListAdapter(interlocutorsData, this));

        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer idFirstUser = currentUser.getId();
                Integer idSecondUser = interlocutorsData.get(position).getId();

                Intent intent = new Intent(FriendListActivity.this, DialogActivity.class);
                intent.putExtra("idCurrentUser", idFirstUser);
                intent.putExtra("idSecondUser", idSecondUser);

                startActivity(intent);
            }
        });
    }

    private void initInterlocutorsData(UsersDataMap usersDataMap) {
        interlocutorsData = new ArrayList<>();
        interlocutorsData.addAll(usersDataMap.getSchoolkidsData());
        interlocutorsData.addAll(usersDataMap.getTeachersData());
        interlocutorsData.addAll(usersDataMap.getSuperadminsData());
    }
}
