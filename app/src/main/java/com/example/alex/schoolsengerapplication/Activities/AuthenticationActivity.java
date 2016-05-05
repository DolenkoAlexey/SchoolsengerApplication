package com.example.alex.schoolsengerapplication.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.GetterUserFromServer;
import com.example.alex.schoolsengerapplication.Json.UserJson;
import com.example.alex.schoolsengerapplication.R;

public class AuthenticationActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;

    Button enterButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        initUI();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                new LoginnerAsync(email, password).execute();
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
        emailEditText = (EditText) findViewById(R.id.emailLoginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordLoginEditText);

        enterButton = (Button) findViewById(R.id.enterLoginButton);
        cancelButton = (Button) findViewById(R.id.cancelLoginButton);
    }

    private void setEditTextsEmpty() {
        emailEditText.setText("");
        passwordEditText.setText("");
    }

    private class LoginnerAsync extends AsyncTask<Void, String, UserJson> {
        private String email;
        private String pass;

        public LoginnerAsync(String email, String pass) {
            this.email = email;
            this.pass = pass;
        }
        @Override
        protected UserJson doInBackground(Void... params) {
            GetterUserFromServer getter = new GetterUserFromServer();
            return getter.getUserFromServerSync(email);
        }

        @Override
        protected void onPostExecute(UserJson userJson) {
            super.onPostExecute(userJson);

            if((userJson.getEmail() == null) || (!userJson.getPassword().equals(pass))){
                Toast.makeText(AuthenticationActivity.this,
                        "Не верно введен E-mail или пароль. Попробуйте еще раз",
                        Toast.LENGTH_SHORT).show();
                setEditTextsEmpty();
                return;
            }

            Intent intent = new Intent(AuthenticationActivity.this, MainAppActivity.class);

            startActivity(intent);

        }
    }
}
