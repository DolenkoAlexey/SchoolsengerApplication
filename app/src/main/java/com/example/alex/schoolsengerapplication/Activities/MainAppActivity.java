package com.example.alex.schoolsengerapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.alex.schoolsengerapplication.GetterInterlocutorsFromServer;
import com.example.alex.schoolsengerapplication.json.UserJson;
import com.example.alex.schoolsengerapplication.R;

import java.util.List;

public class MainAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        UserJson user = (UserJson) getIntent().getSerializableExtra("user");

        GetterInterlocutorsFromServer getterInterlocutors = new GetterInterlocutorsFromServer();
        List<Integer> interlocutors = getterInterlocutors.getInterlocutorsFromServerSync(1);

        TextView textView = (TextView) findViewById(R.id.textView11);
        textView.setText(interlocutors.get(0));


    }
}
