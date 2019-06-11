<?php
require 'conn.php';
require 'database.php';

$chatroomID = $_POST["chatroomID"];

$qry = receivemessage($chatroomID);

$result = mysqli_query($conn, $qry);
while ($row = mysqli_fetch_array($result)) {
    echo $row['message_con'] . "<br>";
}
?>
