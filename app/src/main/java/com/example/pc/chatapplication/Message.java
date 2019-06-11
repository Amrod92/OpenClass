package com.example.pc.chatapplication;

//TODO: needs additional fields (timestamp, etc...)
class Message {
    private int mMessageId;
    private String mMessageContent;
    private int mUserId;
    private int mChatroomId;

    Message(int message_id, String message_content, int user_id, int chatroom_id) {
        this.mMessageId = message_id;
        this.mMessageContent = message_content;
        this.mUserId = user_id;
        this.mChatroomId = chatroom_id;
    }

    int getMessageID() {
        return mMessageId;
    }

    String getMessageContent() {
        return mMessageContent;
    }

    int getUserID() {
        return mUserId;
    }

    int getChatroomID() {
        return mChatroomId;
    }
}
