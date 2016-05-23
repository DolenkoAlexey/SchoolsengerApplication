package com.example.alex.schoolsengerapplication.activities.sessionActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.UIElements.SearchUIElement;
import com.example.alex.schoolsengerapplication.UIElements.SessionUIElement;
import com.example.alex.schoolsengerapplication.UIElements.TokenUIElement;
import com.example.alex.schoolsengerapplication.api.RequesterAPI;
import com.example.alex.schoolsengerapplication.json.TokenJson;
import com.example.alex.schoolsengerapplication.json.usersDataJson.UsersDataMapJson;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;
import com.example.alex.schoolsengerapplication.models.usersData.UsersDataMap;
import com.example.alex.schoolsengerapplication.parsers.UsersDataJsonParser;
import com.example.alex.schoolsengerapplication.presenters.InterlocutorsPresenter;
import com.example.alex.schoolsengerapplication.presenters.SearchPresenter;
import com.example.alex.schoolsengerapplication.presenters.TokenPresenter;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Alex on 22.05.2016.
 */
public abstract class SessionActivity extends AppCompatActivity
        implements SessionUIElement, TokenUIElement, SearchUIElement {

    protected ListView friendsListView;
    protected EditText searchEditText;
    protected Button searchButton;

    protected InterlocutorsPresenter interlocutorsPresenter;
    protected TokenPresenter tokenPresenter;
    protected SearchPresenter searchPresenter;

    protected String token;

    protected User currentUser;
    protected List<UsersData> interlocutorsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySetContentView();
        initUI();

        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        getInterlocutors();
        getToken();
        getSearcher();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPresenter.runAsync(searchEditText.getText().toString());
            }
        });
    }

    protected  void initUI(){
        friendsListView = (ListView) findViewById(R.id.friendsListView);
        searchEditText = (EditText) findViewById(R.id.searchEditText);
        searchButton = (Button) findViewById(R.id.searchButton);
    }

    abstract protected void mySetContentView();

    protected void getToken() {
        tokenPresenter = TokenPresenter.getPresenter();
        tokenPresenter.attachView(this);
        tokenPresenter.runAsync(InstanceID.getInstance(this));
    }

    protected void getInterlocutors() {
        interlocutorsPresenter = InterlocutorsPresenter.getPresenter();
        interlocutorsPresenter.attachView(this);

        interlocutorsPresenter.runAsync(currentUser.getId());
    }

    protected void getSearcher(){
        searchPresenter = SearchPresenter.getPresenter();
        searchPresenter.attachView(this);
    }

    protected void initInterlocutorsData(UsersDataMap usersDataMap) {
        interlocutorsData = new ArrayList<>();
        interlocutorsData.addAll(usersDataMap.getSchoolkidsData());
        interlocutorsData.addAll(usersDataMap.getTeachersData());
        interlocutorsData.addAll(usersDataMap.getSuperadminsData());
    }

    protected void checkIsEqualsTokensOnServerAndClient(){
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

    protected void refreshToken() {
        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();

        TokenJson tokenJson = new TokenJson(currentUser.getEmail(), token);
        Call<JSONObject> call = requesterAPI.refreshToken(tokenJson);

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Response<JSONObject> response) {
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

    @Override
    public void setToken(String token) {
        this.token = token;

        checkIsEqualsTokensOnServerAndClient();
    }

    @Override
    public void setInterlocutors(UsersDataMapJson interlocutorsDataMapJson) {
        UsersDataJsonParser parser = new UsersDataJsonParser();
        UsersDataMap interlocutorsDataMap = parser.parseUsersDataMapFromJson(interlocutorsDataMapJson);

        initInterlocutorsData(interlocutorsDataMap);

        mySetAdapter(interlocutorsData);

        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer idFirstUser = currentUser.getId();
                Integer idSecondUser = interlocutorsData.get(position).getId();

                goToDialog(idFirstUser, idSecondUser);
            }
        });
    }

    abstract protected void mySetAdapter(List<UsersData> interlocutorsData);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        interlocutorsPresenter.detatch();
        tokenPresenter.detatch();
        searchPresenter.detatch();
    }

    abstract void goToDialog(Integer idFirstUser, Integer idSecondUser);
}
