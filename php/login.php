<?php
require 'conn.php';
require 'database.php';

$username = $_POST["username"];
$password = $_POST["password"];

$qry = login($username,$password);

$result = mysqli_query($conn, $qry);

if (mysqli_num_rows($result) > 0) 
{
	echo 'LoginSuccessful ';
	while ($row = mysqli_fetch_array($result)) {
    		echo $row['user_id'];
	}

}
else {
	echo "LoginFailed";
}


?>
