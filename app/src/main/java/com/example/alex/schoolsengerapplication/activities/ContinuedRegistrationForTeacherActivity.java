package com.example.alex.schoolsengerapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.RequesterAPI;
import com.example.alex.schoolsengerapplication.models.Teacher;
import com.example.alex.schoolsengerapplication.parsers.UserJsonParser;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class ContinuedRegistrationForTeacherActivity extends BaseContinuedRegistration {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continued_registration_for_teacher);

        initUI();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUserCharacteristics();

                RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
                UserJsonParser parser = new UserJsonParser();

                Call<JSONObject> call = requesterAPI.sendTeacherToServer(parser.ParseUserToJson((Teacher)user));

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Response<JSONObject> response) {
                        Toast.makeText(ContinuedRegistrationForTeacherActivity.this,
                                "Регистрация прошла успешно.",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ContinuedRegistrationForTeacherActivity.this, BeginActivity.class);
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
        lastnameEditText = (EditText)findViewById(R.id.lastnameTeacherEditText);
        firstnameEditText = (EditText)findViewById(R.id.firstnameTeacherEditText);
        usernameEditText = (EditText)findViewById(R.id.usernameTeacherEditText);

        enterButton = (Button) findViewById(R.id.enterTeacherButton);
        cancelButton = (Button) findViewById(R.id.cancelTeacherButton);
    }

    @Override
    protected void initUserCharacteristics(){
        super.initUserCharacteristics();
        user = new Teacher(0, email, username, pass, firstname, lastname);
    }
}
