<?php
require 'database.php';
require 'conn.php';


//This section is for declaring variables
$email = 'email';
$username = 'username';
$password = 'password';
$name_class = 'name_class';
$pin = '1234';
$chatroomID = '14';
$user = '3';
$message = 'messagetest';

//Testing if register has passed
$rqry = register($email,$username,$password);

$rtest = mysqli_query($conn,$rqry);

if ($rtest) {
  echo "Register - Passed <br>";
} else {
  echo "Register - Failed <br>";
}


//Testing if createchatroom has passed
$cqry = createchatroom($name_class,$pin,$user);

$ctest = mysqli_query($conn,$cqry);

if ($ctest) {
  echo "Create Chatroom - Passed <br>";
} else {
  echo "Create Chatroom - Failed <br>";
}


//Testing if login has passed
$logqry = login($username,$password);

$logtest = mysqli_query($conn,$logqry);

if ($logtest) {
  echo "Login - Passed <br>";
} else {
  echo "Login - Failed <br>";
}

//Testing if receive message has passed
$rmqry = receivemessage($chatroomID);

$rmtest = mysqli_query($conn,$rmqry);

if ($rmqry) {
  echo "Receive Message - Passed <br>";
} else {
  echo "Receive Message - Failed <br>";
}

//Testing if send message has passed
$sqry = sendmessage($chatroomID, $user, $message);

$stest = mysqli_query($conn,$sqry);

if ($sqry) {
  echo "Send Message - Passed <br>";
} else {
  echo "Send Message - Failed <br>";
}

//Testing if list chatrooms passed
$lqry = listchatrooms();

$ltest = mysqli_query($conn,$ltest);

if ($lqry) {
  echo "List Chatrooms - Passed";
} else {
  echo "List Chatrooms - Failed";
}

//Deleting test entries from database
$sql = "DELETE FROM User WHERE username = 'username';";
$result = mysqli_query($conn,$sql);
// echo "$result";

$sql = "DELETE FROM Chatrooms WHERE name_class = 'name_class';";
$result = mysqli_query($conn,$sql);
// echo "$result";

$sql = "DELETE FROM Chatroom WHERE user_id = '3' and message_con = 'messagetest';";
$result = mysqli_query($conn,$sql);
// echo "$result";

?>
