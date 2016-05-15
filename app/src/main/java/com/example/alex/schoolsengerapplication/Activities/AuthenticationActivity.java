package com.example.alex.schoolsengerapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.UIElements.AuthenticationUIElement;
import com.example.alex.schoolsengerapplication.activities.sessionActivities.FriendListActivity;
import com.example.alex.schoolsengerapplication.models.users.User;
import com.example.alex.schoolsengerapplication.presenters.AuthenticationPresenter;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationUIElement{

    EditText emailEditText;
    EditText passwordEditText;

    Button enterButton;
    Button cancelButton;

    AuthenticationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        presenter = AuthenticationPresenter.getPresenter();
        presenter.attachView(this);

        initUI();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                presenter.runAsync(email, password);
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
        presenter.detatch();
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

    @Override
    public void setWrongUserDataAsyncResult(){
        Toast.makeText(this, "Неправильный e-mail или пароль, попробуйте еще раз", Toast.LENGTH_SHORT).show();
        setEditTextsEmpty();
    }

    @Override
    public void setCorrectUserDataAsyncResult(User user){
        Intent intent = new Intent(this, FriendListActivity.class);
        intent.putExtra("currentUser", user);
        startActivity(intent);
    }
}
