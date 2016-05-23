package com.example.alex.schoolsengerapplication.activities.registrationActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.api.RequesterAPI;
import com.example.alex.schoolsengerapplication.models.users.Teacher;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.parsers.UserJsonParser;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Alex on 12.05.2016.
 */
public abstract class BaseContinuedRegistration extends AppCompatActivity {
    EditText lastnameEditText;
    EditText firstnameEditText;
    EditText usernameEditText;

    Button enterButton;
    Button cancelButton;

    User user;

    String email;
    String pass;
    String lastname;
    String firstname;
    String username;

    protected abstract void initUI();

    protected void initUserCharacteristics(){
        lastname = lastnameEditText.getText().toString();
        firstname = firstnameEditText.getText().toString();
        username = usernameEditText.getText().toString();
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");
    }

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
                        startBeginActivity();
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

    abstract protected void startBeginActivity();
}
