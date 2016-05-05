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

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class RegistrationActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;

    Button enterButton;
    Button cancelButton;

    UserJson user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initUI();
        user = new UserJson();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String passwordAgain = passwordAgainEditText.getText().toString();

                if(!password.equals(passwordAgain)){
                    Toast.makeText(RegistrationActivity.this,
                            "Пароли не совпадают. Попробуйте еще раз",
                            Toast.LENGTH_SHORT).show();
                    setEditTextsEmpty();
                    return;
                }

                user.setPassword(password);

                new RegistratorAsync(email).execute();
            }
        });
    }

    private void initUI() {
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        passwordAgainEditText = (EditText)findViewById(R.id.passwordAgainEditText);

        enterButton = (Button) findViewById(R.id.enterButton);
        cancelButton= (Button) findViewById(R.id.cancelButton);
    }

    private void setEditTextsEmpty() {
        emailEditText.setText("");
        passwordEditText.setText("");
        passwordAgainEditText.setText("");
    }


    private class RegistratorAsync extends AsyncTask<Void, String, UserJson> {
        private String email;

        public RegistratorAsync(String email) {
            this.email = email;
        }
        @Override
        protected UserJson doInBackground(Void... params) {
            GetterUserFromServer getter = new GetterUserFromServer();
            return getter.getUserFromServerSync(email);
        }

        @Override
        protected void onPostExecute(UserJson userJson) {
            super.onPostExecute(userJson);

            try {
                if(!isUniqueEmail(userJson)) {
                    Toast.makeText(RegistrationActivity.this,
                            "Такой e-mail уже занят. Попробуйте еще раз",
                            Toast.LENGTH_SHORT).show();
                    emailEditText.setText("");
                    return;
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            user.setEmail(email);

            Intent intent = new Intent(RegistrationActivity.this, ContinuedRegistrationActivity.class);
            intent.putExtra("user", (Serializable) user);
            startActivity(intent);
        }

        private boolean isUniqueEmail(UserJson userJson) throws ExecutionException, InterruptedException {
            // Если с сервера получен пустой объект,
            // значит выборка по запрашиваемому email была пуста,
            // значит этот email уникальен и его можно использовать
            return userJson.getEmail() == null;
        }
    }
}
