<form action="" method="post">
  <label>Email :</label>
  <input type="text" name="email"/><br />
  <label>UserName :</label>
  <input type="text" name="username"/><br />
  <label>Password :</label>
  <input type="password" name="password"/><br/>
  <label>Confirm Password :</label>
  <input type="password" name="cpassword"/><br/>
  <input type="submit" value=" Submit "/><br />
</form>
<?php
  include("config.php");
//session_start();
if($_SERVER["REQUEST_METHOD"] == "POST") {
  // username and password sent from Form
  $myusername=mysqli_real_escape_string($db,$_POST['username']);
  $myemail=mysqli_real_escape_string($db,$_POST['email']);
  $mypassword=mysqli_real_escape_string($db,$_POST['password']);
  $mycpassword=mysqli_real_escape_string($db,$_POST['cpassword']);
  
  //check for password match and correct email
  if (!($mypassword == $mycpassword)) {
    echo "Error, passwords do not match";
  }
  if (!(strpos($myemail,'@ucsd.edu') !== false)) {
    echo "Error, need valid @ucsd.edu email";
  }

  if (($mypassword == $mycpassword) && (strpos($myemail,'@ucsd.edu') !== false)) {
    $sql="INSERT INTO USER (PK_USER_ID, EMAIL, PASSWORD) VALUES('$myusername', '$myemail', '$mypassword')";
    $result = mysqli_query($db,$sql);
  }

  if($result) { 
    echo "Signup Successful!";
  }
}?>
