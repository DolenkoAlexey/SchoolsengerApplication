package com.example.alex.schoolsengerapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.models.User;

public class MainAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        User user = (User) getIntent().getSerializableExtra("user");

//        GetterInterlocutorsFromServer getterInterlocutors = new GetterInterlocutorsFromServer();
//        List<Integer> interlocutors = getterInterlocutors.getInterlocutorsFromServerSync(1);
//
//        TextView textView = (TextView) findViewById(R.id.textView11);
//        textView.setText(interlocutors.get(0));


    }
}
