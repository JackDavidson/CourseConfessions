<?php
include("config.php");
//create sql query to REVIEWS table
$sql="SELECT * FROM COURSE_LIST";

//create array to store output
$output=array();

//store query results into a variable
$result=mysqli_query($db,$sql);
while($row=$result->fetch_assoc()) {
  $newrow="";
  foreach ($row as $cur) {
    $newrow.=$cur;
    $newrow.=" ";
  }
  $newrow = rtrim($newrow);
  array_push($output, $newrow);
}

foreach ($output as $cur) {
  $print.=$cur;
  $print.=", ";
}

$print = rtrim($print, ", ");
echo $print;

mysqli_close($db);
?>
