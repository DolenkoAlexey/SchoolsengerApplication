package com.example.alex.schoolsengerapplication.activities.sessionActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.adapter.SessionAdapter;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;
import com.example.alex.schoolsengerapplication.models.usersData.UsersDataMap;
import com.example.alex.schoolsengerapplication.parsers.UsersDataJsonParser;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity{
    private ListView foundUsersListView;
    private Button cancelButton;

    private List<UsersData> foundUsersData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        UsersDataJsonParser parser = new UsersDataJsonParser();
        UsersDataMapJson usersDataMapJson = (UsersDataMapJson) getIntent().getSerializableExtra("usersDataMap");
        UsersDataMap usersDataMap = parser.parseUsersDataMapFromJson(usersDataMapJson);

        initUI();

        initFoundUsersData(usersDataMap);

        foundUsersListView.setAdapter(new SessionAdapter(foundUsersData, this));

        foundUsersListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this, DialogActivity.class);

                Integer idFirstUser = ((User)getIntent().getSerializableExtra("currentUser")).getId();
                Integer idSecondUser = foundUsersData.get(position).getId();

                startActivity(intent);
            }
        });
    }

    private void initUI() {
        foundUsersListView = (ListView) findViewById(R.id.foundUsersListView);
        cancelButton = (Button) findViewById(R.id.cancelSearchButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFoundUsersData(UsersDataMap usersDataMap){
        foundUsersData = new ArrayList<>();
        foundUsersData.addAll(usersDataMap.getSchoolkidsData());
        foundUsersData.addAll(usersDataMap.getTeachersData());
        foundUsersData.addAll(usersDataMap.getSuperadminsData());
    }
}
