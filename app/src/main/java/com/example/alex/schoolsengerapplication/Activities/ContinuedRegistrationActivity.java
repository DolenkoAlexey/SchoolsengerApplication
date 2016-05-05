package com.example.alex.schoolsengerapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.Json.ResponseJson;
import com.example.alex.schoolsengerapplication.Json.UserJson;
import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.Requester;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ContinuedRegistrationActivity extends AppCompatActivity {

    EditText lastnameEditText;
    EditText firstnameEditText;
    EditText usernameEditText;

    Button enterButton;
    Button cancelButton;

    RadioGroup radioGroup;
    RadioButton schoolkidRadioButton;
    RadioButton teacherRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continued_registration);

        initUI();

        final UserJson user = (UserJson) getIntent().getSerializableExtra("user");

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String lastname = lastnameEditText.getText().toString();
            String firstname = firstnameEditText.getText().toString();
            String username = usernameEditText.getText().toString();

            String character = null;

            switch (radioGroup.getCheckedRadioButtonId())
            {
                case R.id.schoolkidRadioButton:
                    character = "schoolkid";
                    break;
                case R.id.teacherRadioButton:
                    character = "teacher";
                    break;
            }

            user.setLastname(lastname);
            user.setFirstname(firstname);
            user.setUsername(username);
            user.setCharacter(character);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://schoolsenger.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Requester requester = retrofit.create(Requester.class);
            Call<ResponseJson> call = requester.sendUserToServer(user);

            call.enqueue(new Callback<ResponseJson>() {
                @Override
                public void onResponse(Response<ResponseJson> response) {
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
}
