<?php
  include("config.php");
  //mysql_connect("127.0.0.1","root","12345");
  //mysql_select_db("FOOD");
$myusername=mysqli_real_escape_string($db,$_POST['username']);
$mypassword=mysqli_real_escape_string($db,$_POST['password']);

  $sql="SELECT * FROM USER WHERE PK_USER_ID='$myusername' and PASSWORD='$mypassword'";

  //while($e=mysql_fetch_assoc($sql))
$result=mysqli_query($db,$sql);
$row=mysqli_fetch_array($result,MYSQLI_ASSOC);
$active=$row['active'];
$count=mysqli_num_rows($result);


// If result matched $myusername and $mypassword, table row must be 1 row
  if($count==1)
  {
    $resultUsername = $row['PK_USER_ID'];
  } else {
    $resultUsername = "fail";
  }
  $output[]=array('USER' => $resultUsername);
 
  print(json_encode($output));
 
  mysqli_close($db);
?>
