package com.example.pc.chatapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChatroomScreenActivity extends AppCompatActivity {
    private EditText messageText;
    private int chatRoomId;
    private int adminId;
    private Handler handler;
    int delay = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom_screen);
        String[] nums = getIntent().getStringExtra(Intent.EXTRA_TEXT).split(" ");
        chatRoomId = Integer.parseInt(nums[0].substring(4));
        adminId = Integer.parseInt(nums[1].substring(4));
        handler = new Handler();
        messageText = (EditText) findViewById(R.id.messageText);
        final Button sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        //Auto refresh messages
        handler.postDelayed(new Runnable() {
            public void run() {
                receiveMessage();
                handler.postDelayed(this,delay);
            }
        },delay);
    }

    /** Method to send a message and store it in a chatroom table in the database **/
    public void sendMessage() {
        String method = "sendMessage";
        messageText = (EditText) findViewById(R.id.messageText);
        String message = messageText.getText().toString().trim();
        if ("".equals(message)) {
            Toast.makeText(this, "Message body empty", Toast.LENGTH_LONG).show();
            return;
        }
        messageText.setText("");
        SharedPreferences prefs = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        BackgroundTask backgroundTask = new BackgroundSendMessageTask(this);
        backgroundTask.execute(method, message, ""+chatRoomId, prefs.getString("userID", null));
    }

    /** Method that retrieves all messages stored in a table for the chatroom in the database **/
    public void receiveMessage() {
        String method = "receiveMessage";
        BackgroundTask backgroundTask = new BackgroundReceiveMessageTask(this);
        String messages = "";
        try {
            messages = backgroundTask.execute(method, ""+chatRoomId).get();
            ArrayList<Message> messageArrayList = new ArrayList<>();
            String[] messagesSep = messages.split("\n");
            for (int i=0; i<messagesSep.length; i+=2)
            {
                int userId = Integer.parseInt(messagesSep[i]);
                String userMessage = messagesSep[i+1];
                Message m = new Message(0, userMessage, userId, chatRoomId);
                messageArrayList.add(m);
            }
            SharedPreferences prefs = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
            String userid = prefs.getString("userID", null);
            MessageAdapter messageAdapter = new MessageAdapter(this, messageArrayList, userid, ""+adminId);
            ListView messageList = (ListView) findViewById(R.id.message_list);
            messageList.setAdapter(messageAdapter);
        } catch (ExecutionException | InterruptedException e) {
            Log.e("receiveMessage_CSA", "failed with " + e.getLocalizedMessage());
            Toast.makeText(this, "Fetch failed", Toast.LENGTH_LONG).show();
        }
    }
}
