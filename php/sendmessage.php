<?php
require 'conn.php';
require 'database.php';

$chatroomID = $_POST["chatroomID"];
$user = '1';
$message = $_POST["message"];

$qry = sendmessage($chatroomID, $user, $message);

$result = mysqli_query($conn, $qry);

if($result){echo("message sent");}
else {echo("message not sent");}

?>
