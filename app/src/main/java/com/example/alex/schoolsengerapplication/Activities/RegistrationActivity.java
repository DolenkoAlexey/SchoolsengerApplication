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

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.UIElements.RegistrationUIElement;
import com.example.alex.schoolsengerapplication.presenters.RegistrationActivityPresenter;

public class RegistrationActivity extends AppCompatActivity implements RegistrationUIElement{

    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;

    Button enterButton;
    Button cancelButton;

    RadioGroup radioGroup;
    RadioButton schoolkidRadioButton;
    RadioButton teacherRadioButton;

    String email;
    String password;
    String passwordAgain;

    RegistrationActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        presenter = RegistrationActivityPresenter.getPresenter();
        presenter.attachView(this);

        initUI();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            initUsersParams();

            if(!password.equals(passwordAgain)){
                Toast.makeText(RegistrationActivity.this,
                        "Пароли не совпадают. Попробуйте еще раз",
                        Toast.LENGTH_SHORT).show();
                setEditTextsEmpty();
                return;
            }

            presenter.runAsync(email);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detatch();
    }

    @Override
    public void setCorrectUserDataAsyncResult(){
        Intent intent = null;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case (R.id.schoolkidRadioButton):
                intent = new Intent(this, ContinuedRegistrationForSchoolkidActivity.class);
                break;
            case(R.id.teacherRadioButton):
                intent = new Intent(this, ContinuedRegistrationForTeacherActivity.class);
        }
        assert intent != null;
        intent.putExtra("email", email);
        intent.putExtra("pass", password);
        startActivity(intent);
    }

    @Override
    public void setWrongUserDataAsyncResult(){
        Toast.makeText(this, "Такой e-mail уже занят. Попробуйте еще раз", Toast.LENGTH_SHORT).show();
        setEmailEditTextEmpty();
    }

    private void setEmailEditTextEmpty(){
        emailEditText.setText("");
    }

    private void initUsersParams() {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        passwordAgain = passwordAgainEditText.getText().toString();
    }

    private void initUI() {
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        passwordAgainEditText = (EditText)findViewById(R.id.passwordAgainEditText);

        enterButton = (Button) findViewById(R.id.enterButton);
        cancelButton= (Button) findViewById(R.id.cancelButton);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        schoolkidRadioButton = (RadioButton) findViewById(R.id.schoolkidRadioButton);
        teacherRadioButton = (RadioButton) findViewById(R.id.teacherRadioButton);
    }

    private void setEditTextsEmpty() {
        emailEditText.setText("");
        passwordEditText.setText("");
        passwordAgainEditText.setText("");
    }
}
