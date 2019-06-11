package com.example.pc.chatapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChatList extends AppCompatActivity {
    private Handler handler;
    int delay = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        handler = new Handler();
        //Auto refresh chatrooms displayed
        handler.postDelayed(new Runnable() {
            public void run() {
                refreshRooms();
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    public void refreshRooms() {
        String method = "refreshRooms";
        BackgroundTask backgroundTask = new BackgroundRefreshRoomsTask(this);
        String rooms = "";
        try {
            rooms = backgroundTask.execute(method).get();
            ArrayList<Message> roomList = new ArrayList<>();
            String[] messagesSep = rooms.split(" ");
            for (int i=0; i<messagesSep.length; i++) {
                String[] names = messagesSep[i].split("space");
                String chatroomName = names[0];
                String[] ids = names[1].split("volvic");
                int chatroomId = Integer.parseInt(ids[0]);
                int userId = Integer.parseInt(ids[1]);
                Message m = new Message(0, chatroomName, userId, chatroomId);
                roomList.add(m);
            }
            MessageAdapter messageAdapter = new MessageAdapter(this, roomList);
            ListView chatList = (ListView) findViewById(R.id.chatList);
            chatList.setAdapter(messageAdapter);
            chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent chatroomIntent = new Intent(ChatList.this, ChatroomScreenActivity.class);
                    int chatRoomId = ((Message)parent.getItemAtPosition(position)).getChatroomID();
                    int userId = ((Message)parent.getItemAtPosition(position)).getUserID();
                    chatroomIntent.putExtra(Intent.EXTRA_TEXT, "chat"+chatRoomId+" admn"+userId);
                    startActivity(chatroomIntent);
                }
            });
        } catch (ExecutionException | InterruptedException e) {
            Log.e("receiveMessage_CSA", "failed with " + e.getLocalizedMessage());
            Toast.makeText(this, "Fetch failed", Toast.LENGTH_LONG).show();
        }
    }
}
