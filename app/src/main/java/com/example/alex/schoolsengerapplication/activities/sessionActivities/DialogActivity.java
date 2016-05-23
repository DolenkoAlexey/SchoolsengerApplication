package com.example.alex.schoolsengerapplication.activities.sessionActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.UIElements.DialogUIElement;
import com.example.alex.schoolsengerapplication.UIElements.MessageFromServerUIElement;
import com.example.alex.schoolsengerapplication.adapter.DialogAdapter;
import com.example.alex.schoolsengerapplication.api.RequesterAPI;
import com.example.alex.schoolsengerapplication.models.message.Message;
import com.example.alex.schoolsengerapplication.models.message.MessagesList;
import com.example.alex.schoolsengerapplication.parsers.MessageJsonParser;
import com.example.alex.schoolsengerapplication.presenters.DialogPresenter;

import org.json.JSONObject;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class DialogActivity extends AppCompatActivity implements DialogUIElement, MessageFromServerUIElement{
    private DialogPresenter presenter;
    DialogAdapter dialogAdapter;


    private List<Message> messages;

    private TextView userInfoTextView;
    Button cancelButton;
    Button sendButton;
    EditText messageEditText;
    ListView messageListView;

    Integer idCurrentUser;
    Integer idSecondUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initUI();

        idCurrentUser = getIntent().getIntExtra("idCurrentUser", 0);
        idSecondUser = getIntent().getIntExtra("idSecondUser", 0);

        presenter = DialogPresenter.getPresenter();
        presenter.attachView(this);
        presenter.runAsync(idCurrentUser, idSecondUser);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
                MessageJsonParser parser = new MessageJsonParser();

                String messageString = messageEditText.getText().toString();

                if(messageString.equals("")){
                    Toast.makeText(DialogActivity.this,
                        "Введите сообщение!",Toast.LENGTH_SHORT).show();
                    return;
                }

                final Message message = new Message(idCurrentUser, idSecondUser, messageString);

                Call<JSONObject> call = requesterAPI.sendMessageToServer(parser.parseMessageToJson(message));

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Response<JSONObject> response) {
                        Toast.makeText(DialogActivity.this,
                                "Отправлено!",Toast.LENGTH_SHORT).show();

                        messages.add(message);
                        dialogAdapter.notifyDataSetChanged();
                        messageEditText.setText("");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
            }
        });
    }

    private void initUI(){
        userInfoTextView = (TextView)findViewById(R.id.userInfoTextView);
        cancelButton = (Button) findViewById(R.id.cancelDialogButton);
        sendButton = (Button) findViewById(R.id.sendButton);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        messageListView = (ListView) findViewById(R.id.messageListView);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detatch();
    }

    @Override
    public void setMessages(MessagesList messagesList) {
        messages = messagesList.getMessages();
        dialogAdapter = new DialogAdapter(messages, idCurrentUser, DialogActivity.this);
        messageListView.setAdapter(dialogAdapter);
        messageListView.setClickable(false);
    }

    @Override
    public void setMessageFromInterlocutor(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messages.add(new Message(idCurrentUser, idSecondUser, message));
                dialogAdapter.notifyDataSetChanged();
            }
        });
    }
}
