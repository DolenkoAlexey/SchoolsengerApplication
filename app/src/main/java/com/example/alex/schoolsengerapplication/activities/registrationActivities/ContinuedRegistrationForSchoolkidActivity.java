package com.example.alex.schoolsengerapplication.activities.registrationActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.activities.BeginActivity;
import com.example.alex.schoolsengerapplication.api.RequesterAPI;
import com.example.alex.schoolsengerapplication.models.users.Schoolkid;
import com.example.alex.schoolsengerapplication.parsers.UserJsonParser;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class ContinuedRegistrationForSchoolkidActivity extends BaseContinuedRegistration {
    EditText classNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continued_registration_for_schoolkid);

        initUI();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUserCharacteristics();

                RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
                UserJsonParser parser = new UserJsonParser();

                Call<JSONObject> call = requesterAPI.sendSchoolkidToServer(parser.ParseUserToJson((Schoolkid)user));

                call.enqueue(new Callback<JSONObject>() {

                    @Override
                    public void onResponse(Response<JSONObject> response) {
                        Toast.makeText(ContinuedRegistrationForSchoolkidActivity.this,
                                "Регистрация прошла успешно.",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ContinuedRegistrationForSchoolkidActivity.this, BeginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                         throw new RuntimeException(t);
                    }
                });
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initUI() {
        lastnameEditText = (EditText)findViewById(R.id.lastnameSchoolkidEditText);
        firstnameEditText = (EditText)findViewById(R.id.firstnameSchoolkidEditText);
        usernameEditText = (EditText)findViewById(R.id.usernameSchoolkidEditText);
        classNumberEditText = (EditText) findViewById(R.id.classNumberEditText);

        enterButton = (Button) findViewById(R.id.enterSchoolkidButton);
        cancelButton = (Button) findViewById(R.id.cancelSchoolkidButton);

    }

    @Override
    protected void initUserCharacteristics(){
        super.initUserCharacteristics();
        String classNumber = classNumberEditText.getText().toString();
        user = new Schoolkid(0, email, username, pass, firstname, lastname, classNumber);
    }
}
