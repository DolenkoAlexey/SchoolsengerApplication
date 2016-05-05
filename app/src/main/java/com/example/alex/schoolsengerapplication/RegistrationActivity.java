package com.example.alex.schoolsengerapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.Json.UserJson;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RegistrationActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;

    Button enterButton;
    Button cancelButton;

    Map<String, String> userMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initUI();
        userMap = new HashMap<>();

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

                userMap.put("password", password);

                new MyAsync(email).execute();
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


    private class MyAsync extends AsyncTask<Void, String, UserJson> {
        private String email;

        public MyAsync(String email) {
            this.email = email;
        }
        @Override
        protected UserJson doInBackground(Void... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://schoolsenger.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Requester requester = retrofit.create(Requester.class);
            Call<UserJson> call = requester.getIsUniqueEmailFromServer(email);

            Response<UserJson> response = null;
            try {
                response = call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert response != null;
            return response.body();
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

            userMap.put("email", email);

            Intent intent = new Intent(RegistrationActivity.this, ContinuedRegistrationActivity.class);
            intent.putExtra("userMap", (Serializable) userMap);
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
