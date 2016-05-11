package com.example.alex.schoolsengerapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.json.UserJson;
import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.RequesterAPI;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class ContinuedRegistrationActivity extends AppCompatActivity {

    EditText lastnameEditText;
    EditText firstnameEditText;
    EditText usernameEditText;

    Button enterButton;
    Button cancelButton;

    RadioGroup radioGroup;
    RadioButton schoolkidRadioButton;
    RadioButton teacherRadioButton;

    UserJson user;

    String email;
    String pass;
    String lastname;
    String firstname;
    String username;
    String character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continued_registration);

        initUI();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUserCharacteristics();

                RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
                Call<JSONObject> call = requesterAPI.sendUserToServer(user);

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Response<JSONObject> response) {
                        Toast.makeText(ContinuedRegistrationActivity.this,
                                "Регистрация прошла успешно.",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ContinuedRegistrationActivity.this, BeginActivity.class);
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

    private void initUI() {
        lastnameEditText = (EditText)findViewById(R.id.lastnameEditText);
        firstnameEditText = (EditText)findViewById(R.id.firstnameEditText);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);

        enterButton = (Button) findViewById(R.id.enterContinuedRegistrationButton);
        cancelButton = (Button) findViewById(R.id.cancelContinuedRegistrationButton);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        schoolkidRadioButton = (RadioButton) findViewById(R.id.schoolkidRadioButton);
        teacherRadioButton = (RadioButton) findViewById(R.id.teacherRadioButton);
    }
    private void initUserCharacteristics(){
        lastname = lastnameEditText.getText().toString();
        firstname = firstnameEditText.getText().toString();
        username = usernameEditText.getText().toString();
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");

        switch (radioGroup.getCheckedRadioButtonId())
        {
            case R.id.schoolkidRadioButton:
                character = "schoolkid";
                break;
            case R.id.teacherRadioButton:
                character = "teacher";
                break;
        }
        user = new UserJson(email, username, pass, firstname, lastname, character);
    }
}
