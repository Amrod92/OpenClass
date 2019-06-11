<?php
require 'conn.php';
require 'database.php';

$email = $_POST["email"];
$username = $_POST["username"];
$password = $_POST["password"];

$qry = register($email,$username,$password);

mysqli_query($conn, $qry); 

?>
