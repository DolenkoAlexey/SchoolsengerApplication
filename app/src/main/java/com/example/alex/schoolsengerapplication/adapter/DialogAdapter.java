package com.example.alex.schoolsengerapplication.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.models.message.Message;

import java.util.List;

/**
 * Created by Alex on 16.05.2016.
 */
public class DialogAdapter extends BaseAdapter {
    private List<Message> messages;
    private LayoutInflater inflater;
    private Integer idCurrentUser;

    public DialogAdapter(List<Message> messages, Integer idCurrentUser, Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messages = messages;
        this.idCurrentUser = idCurrentUser;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.item_message, parent, false);
        }

        TextView messageTextView = (TextView) view.findViewById(R.id.messageTextView);

        messageTextView.setText(messages.get(position).getMessageString());
        if(messages.get(position).getIdFrom() == idCurrentUser){
            messageTextView.setGravity(Gravity.RIGHT);
        }

        return view;
    }
}
