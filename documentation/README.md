<h2>OpenClass</h2>

![Entity Relationship Diagram - Image for OpenClass](https://github.com/BrunelCS/cs2001-18-19-2018-19-group-23/blob/master/documentation/Entity_Relationship_Diagram.png)

<p>3 Tables:</p> 
<p><b>User</b> – user_id, username, email, password</p>
<p><b>Chatrooms</b> - chatroom_id, name_class, pin_id, user_id</p>
<p><b>Messages</b> – chatroom_id, message_id, user_id, timestamp, message_con</p>



<p><b>User:</b></p>
Each user has its own unique ID, which is set as a primary key for this table. Each time a user is
created, the user_id is incremented by 1. The keys username, email and password are limited to 50
characters.

<p><b>Chatrooms:</b></p>
Each chatroom has its own unique ID, which is set as a primary key for this table. Each time a
chatroom is created, the chatroom_id is incremented by 1. Each chatroom has its own name and
pin, which would be a randomly generated integer. The user_id of the chatroom creator is fetched
from the table “User”.

<p><b>Messages:</b></p>
Each chatroom will have its own “Messages” table. The message_id is set as the primary key for this
table. This will identify each message individually, the time it was sent (timestamp) and the contents
of the message. It will also identify which user sent the message by user_id, which is fetched from
the table “User”. The chatroom_id is fetched from the table “Chatrooms”.

<h2>SQL CODE</h2>

https://pastebin.com/Pq1mSbUA

Website:
https://openclass.azurewebsites.net/

App Template:
https://github.com/Manlio92/GroupProject23-v1
