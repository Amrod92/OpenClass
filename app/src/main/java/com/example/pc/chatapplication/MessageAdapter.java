package com.example.pc.chatapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {
    private int userId;
    private int adminId;

    MessageAdapter(Context context, ArrayList<Message> messages) {
        super(context, 0, messages);
    }

    MessageAdapter(Context context, ArrayList<Message> messages, String userid, String adminid) {
        super(context, 0, messages);
        userId = Integer.parseInt(userid);
        adminId = Integer.parseInt(adminid);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View messageListView = convertView;
        if (null == messageListView) {
            messageListView = LayoutInflater.from(getContext())
                    .inflate(R.layout.message_list_item, parent, false);
        }
        Message message = getItem(position);
        TextView messageView = (TextView) messageListView.findViewById(R.id.message_text_view);
        if (adminId == message.getUserID()) messageView.setBackgroundColor(Color.GREEN);
        else if (userId == message.getUserID()) messageView.setBackgroundColor(Color.LTGRAY);
        messageView.setText(message.getMessageContent());
        return messageListView;
    }
}
