<?php
require 'conn.php';
require 'database.php';

$name_class = $_POST["name_class"];
$pin = $_POST["pin"];
$user_id = $_POST["user_id"];

$qry = createchatroom($name_class,$pin,$user_id);

mysqli_query($conn, $qry);

?>

