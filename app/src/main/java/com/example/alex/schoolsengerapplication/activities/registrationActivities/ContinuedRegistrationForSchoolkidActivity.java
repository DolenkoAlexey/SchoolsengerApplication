package com.example.alex.schoolsengerapplication.activities.registrationActivities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.activities.BeginActivity;
import com.example.alex.schoolsengerapplication.models.users.Schoolkid;

public class ContinuedRegistrationForSchoolkidActivity extends BaseContinuedRegistration {
    EditText classNumberEditText;

    @Override
    protected void startBeginActivity() {
        Toast.makeText(ContinuedRegistrationForSchoolkidActivity.this,
                "Регистрация прошла успешно.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ContinuedRegistrationForSchoolkidActivity.this, BeginActivity.class);
        startActivity(intent);
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
