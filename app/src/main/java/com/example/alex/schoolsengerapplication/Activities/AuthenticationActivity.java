package com.example.alex.schoolsengerapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.json.UserJson;
import com.example.alex.schoolsengerapplication.presenters.AuthenticationActivityPresenter;

public class AuthenticationActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;

    Button enterButton;
    Button cancelButton;

    AuthenticationActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        AuthenticationActivityPresenter.attachView(this);

        initUI();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                presenter = new AuthenticationActivityPresenter(email, password);
                presenter.runAsync();
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
    protected void onDestroy() {
        super.onDestroy();
        AuthenticationActivityPresenter.detatch();
    }

    private void initUI() {
        emailEditText = (EditText) findViewById(R.id.emailLoginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordLoginEditText);

        enterButton = (Button) findViewById(R.id.enterLoginButton);
        cancelButton = (Button) findViewById(R.id.cancelLoginButton);
    }

    private void setEditTextsEmpty() {
        emailEditText.setText("");
        passwordEditText.setText("");
    }

    public void setWrongUserDataAsyncResult(){
        Toast.makeText(this, "Такой e-mail уже занят. Попробуйте еще раз", Toast.LENGTH_SHORT).show();
        setEditTextsEmpty();
    }

    public void setCorrectUserDataAsyncResult(UserJson userJson){
        Intent intent = new Intent(this, MainAppActivity.class);
        intent.putExtra("user", userJson);
        startActivity(intent);
    }
}
