package com.example.pc.chatapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChatroomCreationActivity extends AppCompatActivity {
    private EditText etChatroomName;
    private EditText etChatroomPIN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom_creation);
        etChatroomName = findViewById(R.id.etChatroomName);
        etChatroomPIN = findViewById(R.id.etChatroomPIN);
        final Button bCreateChatroom = findViewById(R.id.bCreateChatroom);
        bCreateChatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChatroom();
            }
        });
        SharedPreferences user = getSharedPreferences("MyPrefsFile",0);

    }

    private void createChatroom() {
        String method = "createRoom";
        BackgroundTask bgTask = new BackgroundCreateRoomTask(this);
        String name = etChatroomName.getText().toString().trim();
        if ("".equals(name)) {
            Toast.makeText(this, "Name of chatroom empty", Toast.LENGTH_LONG).show();
            return;
        }
        String pin = etChatroomPIN.getText().toString().trim();
        if ("".equals(pin)) {
            Toast.makeText(this, "PIN of chatroom empty", Toast.LENGTH_LONG).show();
            return;
        }
        bgTask.execute(method, name, pin, getUser());

        Intent toJoinRoom = new Intent(this, ChatList.class);
        toJoinRoom.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(toJoinRoom);
    }
    public String getUser() {
        SharedPreferences preferences = getSharedPreferences("MyPrefsFile",0);
        return preferences.getString("userID",null);
    }
}
