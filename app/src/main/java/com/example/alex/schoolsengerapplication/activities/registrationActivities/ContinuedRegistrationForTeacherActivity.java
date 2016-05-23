package com.example.alex.schoolsengerapplication.activities.registrationActivities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.activities.BeginActivity;
import com.example.alex.schoolsengerapplication.models.users.Teacher;

public class ContinuedRegistrationForTeacherActivity extends BaseContinuedRegistration {

    @Override
    protected void startBeginActivity() {
        Toast.makeText(ContinuedRegistrationForTeacherActivity.this,
                "Регистрация прошла успешно.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ContinuedRegistrationForTeacherActivity.this, BeginActivity.class);
        startActivity(intent);
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
