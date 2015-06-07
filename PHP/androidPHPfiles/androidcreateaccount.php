<?php
  include("config.php");
//session_start();
if($_SERVER["REQUEST_METHOD"] == "POST") {
  // username and password sent from Form
  $myusername=mysqli_real_escape_string($db,$_POST['username']);
  $myemail=mysqli_real_escape_string($db,$_POST['email']);
  $mypassword=mysqli_real_escape_string($db,$_POST['password']);
  $mycpassword=mysqli_real_escape_string($db,$_POST['cpassword']);
  
  //create output array for json encoding
  $output=array();

  //check for password match and correct email
  if (!($mypassword == $mycpassword)) {
    array_push($output, "Error, passwords do not match");
  }
  if (!(strpos($myemail,'@ucsd.edu') !== false)) {
    array_push($output, "Error, need valid @ucsd.edu email");
  }
  //if both checks pass, post to the database
  if (($mypassword == $mycpassword) && (strpos($myemail,'@ucsd.edu') !== false)) {
    $sql="INSERT INTO USER (PK_USER_ID, EMAIL, PASSWORD) VALUES('$myusername', '$myemail', '$mypassword')";
    $result = mysqli_query($db,$sql);
  }
  //if insert was successful, push a success message to output array
  if($result) { 
    array_push($output, "success");
  }
  //send json encoding of array
  print(json_encode($output));
  //close database connection
  mysqli_close($db);
}?>
