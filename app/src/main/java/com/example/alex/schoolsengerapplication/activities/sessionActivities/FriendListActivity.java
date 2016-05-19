package com.example.alex.schoolsengerapplication.activities.sessionActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.UIElements.FriendListUIElement;
import com.example.alex.schoolsengerapplication.UIElements.TokenUIElement;
import com.example.alex.schoolsengerapplication.adapter.FriendListAdapter;
import com.example.alex.schoolsengerapplication.api.RequesterAPI;
import com.example.alex.schoolsengerapplication.json.TokenJson;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;
import com.example.alex.schoolsengerapplication.models.usersData.UsersDataMap;
import com.example.alex.schoolsengerapplication.parsers.UsersDataJsonParser;
import com.example.alex.schoolsengerapplication.presenters.InterlocutorsPresenter;
import com.example.alex.schoolsengerapplication.presenters.TokenPresenter;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class FriendListActivity extends AppCompatActivity implements FriendListUIElement, TokenUIElement{
    ListView friendsListView;

    InterlocutorsPresenter interlocutorsPresenter;
    TokenPresenter tokenPresenter;
    String token;

    User currentUser;
    private List<UsersData> interlocutorsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        initUI();

        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        getInterlocutors();
        getToken();
    }

    private void getToken() {
        tokenPresenter = TokenPresenter.getPresenter();
        tokenPresenter.attachView(this);
        tokenPresenter.runAsync(InstanceID.getInstance(this));
    }

    private void getInterlocutors() {
        interlocutorsPresenter = InterlocutorsPresenter.getPresenter();
        interlocutorsPresenter.attachView(this);

        interlocutorsPresenter.runAsync(currentUser.getId());
    }

    private void initUI(){
        friendsListView = (ListView) findViewById(R.id.friendsListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        interlocutorsPresenter.detatch();
        tokenPresenter.detatch();
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

    @Override
    public void setToken(String token) {
        this.token = token;

        checkIsEqualsTokensOnServerAndClient();
    }


    private void checkIsEqualsTokensOnServerAndClient(){
        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
        TokenJson tokenJson = new TokenJson(currentUser.getEmail(), token);
        Call<TokenJson> call = requesterAPI.getTokenFromServerByEmail(tokenJson.getEmailUser());

        call.enqueue(new Callback<TokenJson>() {
            @Override
            public void onResponse(Response<TokenJson> response) {
                if((response.body() == null) || (!response.body().getToken().equals(token))) {
                    refreshToken();//Если токены, полученный с GSM сервера и хранящийся
                                   // на PUSH сервере не совпадают, то обновляем серверный токен на новый
                                   // такое бывает, если приложение переустанавливается
                }
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

    private void refreshToken() {
        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();

        TokenJson tokenJson = new TokenJson(currentUser.getEmail(), token);
        Call<JSONObject> call = requesterAPI.refreshToken(tokenJson);

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Response<JSONObject> response) {
                Toast.makeText(FriendListActivity.this,
                        "Токен обновлен!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }
}
