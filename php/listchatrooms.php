<?php
require 'conn.php';
require 'database.php';

$qry = listchatrooms();

$result = mysqli_query($conn, $qry);
while ($row = mysqli_fetch_array($result)) {
    echo $row['name_class'] . "space" . $row['chatroom_id'] . "<br>";
}
?>
