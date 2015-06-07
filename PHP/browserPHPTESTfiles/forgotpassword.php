<form action="" method="post">
  <label>UserName :</label>
  <input type="text" name="username"/><br>
  <label>Email :</label>
  <input type="email" name="email"/><br>
  <input type="submit" value=" Submit"/><br>
</form>
<?php
  include("config.php");
  
  if($_SERVER["REQUEST_METHOD"] == "POST") {
    //username and email sent from Form
    $myusername=mysqli_real_escape_string($db,$_POST['username']);
    $myemail=mysqli_real_escape_string($db,$_POST['email']);
    
    //declare SQL request to pull only rows that match BOTH username and email
    $sql="SELECT PASSWORD FROM USER WHERE PK_USER_ID='$myusername' and EMAIL='$myemail'";
    //execute mysql query
    $result=mysqli_query($db,$sql);
    //set $row to the matching row, given there is one, and count as a signaller for success
    $row=mysqli_fetch_array($result,MYSQLI_ASSOC);
    //add fetched password to a string
    foreach($row as $cur){
      $newpass = $cur;
    }
    $count=mysqli_num_rows($result);
    
    //if $count=1, means there was a match, send email with user's password
    if($count == 1) {
      //set message to be sent
      $subject="CourseConfessions Password retrieval for: '$myusername'";
      $message="your password is: '$newpass'";
      //send the mail
      mail($myemail, $subject, $message);
      echo "Email sent!";
    }
    else {
      echo "username and email do not match or were not found!";
    }
    //close database connection
    mysqli_close($db);
  }


?>