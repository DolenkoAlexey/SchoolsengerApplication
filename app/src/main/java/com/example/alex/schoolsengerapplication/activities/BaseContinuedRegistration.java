package com.example.alex.schoolsengerapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.alex.schoolsengerapplication.models.User;

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

}
