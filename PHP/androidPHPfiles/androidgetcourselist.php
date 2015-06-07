<?php
include("config.php");
//create sql query to REVIEWS table
$sql="SELECT * FROM COURSE_LIST";

//create array to store output
$outputlist=array();

//store query results into a variable
$result=mysqli_query($db,$sql);
while($row=$result->fetch_assoc()) {
  $newrow="";
  foreach ($row as $cur) {
    $newrow.=$cur;
    $newrow.=" ";
  }
  $newrow = rtrim($newrow);
  array_push($outputlist,$newrow);
}

//create namevaluepair array for json encoding
$output[]=array('COURSES' => $outputlist);

//send an encoded json of the array
print(json_encode($output));

mysqli_close($db);
?>
