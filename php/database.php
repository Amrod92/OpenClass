<?php

function register($email, $username, $password)
{
	$register_query = "INSERT INTO `User` (`user_id`, `username`, `email`, `password`) VALUES (NULL, '$username', '$email', '$password');";
	return $register_query;
}

function createchatroom($name_class, $pin)
{
	$createchatroom_query = "INSERT INTO `Chatrooms` (`chatroom_id`, `name_class`, `pin_id`, `user_id`) VALUES (NULL, '$name_class', '$pin', '4');";
	return $createchatroom_query;
}



function login($username, $password)
{
	$login_query = "SELECT * FROM User WHERE username = '$username' AND password = '$password';";
	return $login_query;
}

function receivemessage($chatroomID)
{
	$receivemessage_query = "SELECT message_con FROM `Chatroom` WHERE chatroom_id = '$chatroomID';";
	return $receivemessage_query;
}

function sendmessage($chatroomID, $user, $message)
{
	$sendmessage_query = 
		"INSERT INTO `Chatroom` (`chatroom_id`, `message_id`, `user_id`, `timestamp`, `message_con`) VALUES ('$chatroomID', NULL, '$user', CURRENT_TIMESTAMP, '$message');";
	return $sendmessage_query;
}

function listchatrooms()
{
	$listchatrooms_query = "SELECT name_class, chatroom_id FROM `Chatrooms`";
	return $listchatrooms_query;
}

?>
