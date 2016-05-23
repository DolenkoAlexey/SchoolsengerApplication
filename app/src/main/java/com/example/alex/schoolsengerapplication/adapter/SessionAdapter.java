package com.example.alex.schoolsengerapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alex.schoolsengerapplication.R;
import com.example.alex.schoolsengerapplication.models.usersData.SchoolkidsData;
import com.example.alex.schoolsengerapplication.models.usersData.UsersData;

import java.util.List;

/**
 * Created by Alex on 15.05.2016.
 */
public class SessionAdapter extends BaseAdapter {
    private List<UsersData> usersData;
    private LayoutInflater inflater;

    public SessionAdapter(List<UsersData>  usersData, Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.usersData = usersData;
    }

    @Override
    public int getCount() {
        return usersData.size();
    }

    @Override
    public Object getItem(int position) {
        return usersData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.item_friend_list_layout, parent, false);
        }

        TextView usernameTextView = (TextView)view.findViewById(R.id.usernameTextView);
        TextView roleTextView = (TextView) view.findViewById(R.id.roleTextView);
        TextView classNumberTextView = (TextView)view.findViewById(R.id.classNumberTextView);

        usernameTextView.setText(usersData.get(position).getUsername());
        roleTextView.setText(usersData.get(position).getRole());

        if(usersData.get(position) instanceof SchoolkidsData) {
            classNumberTextView.setText(((SchoolkidsData)usersData.get(position)).getClassNumber());
        }

        return view;
    }


}
