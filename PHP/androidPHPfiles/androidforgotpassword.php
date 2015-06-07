<?php
  include("config.php");
  
  if($_SERVER["REQUEST_METHOD"] == "POST") {
  // username and password sent from Form
  $myusername=mysqli_real_escape_string($db,$_POST['username']);
  $myemail=mysqli_real_escape_string($db,$_POST['email']);
  
  //create output array for json encoding
  $output=array();

  if (!(strpos($myemail,'ucsd.edu') !== false)) {
    array_push($output, "Error, need valid @ucsd.edu email");
    print(json_encode($output));
    die();
  }
  //if both checks pass, post to the database
  $sql="SELECT PASSWORD FROM USER WHERE PK_USER_ID='$myusername' AND EMAIL='$myemail'";
  $result = mysqli_query($db, $sql);
  $count = mysqli_num_rows($result);
  $row=mysqli_fetch_array($result);
  //add fetched password to a string
  foreach($row as $cur){
    $newpass = $cur;
  }
  //if insert was successful, push a success message to output array
  if($count == 1) { 
    array_push($output, "success");
    // Message to be sent
    $subject="CourseConfessions Password retrieval for: $myusername";
    $message="Your password is: $newpass";
    // Send the mail
    mail($myemail, $subject, $message);
  }
  else {
    array_push($output, "Incorrect Username or Email");
  }
  //send json encoding of array
  print(json_encode($output));
  //close database connection
  mysqli_close($db);
}
?>
