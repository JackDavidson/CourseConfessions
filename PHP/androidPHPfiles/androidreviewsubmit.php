<?php
include("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST")
{
  //first create string variables for "author" and "review"
  $curAuthor=mysqli_real_escape_string($db,$_POST['author']);
  $newReview=mysqli_real_escape_string($db,$_POST['review']);
  $revDept=($_POST['dept']);
  $revCourseNum=($_POST['courseNum']);
  //make sure review is non-empty
  if (!(strlen($newReview) == 0)) {
    //write objects to sql database
    $sql="INSERT INTO REVIEWS (FK_USER_ID, COURSE_CAT, COURSE_DES, REVIEW, RATED) VALUES ('$curAuthor', '$revDept', '$revCourseNum', '$newReview', '$curAuthor')";
  }
  //check for successful insertion and echo error if didn't work
  if (mysqli_query($db, $sql)) {
    $result="Successfully submitted Review!";
  }
  else {
    $result="fail";
    mysqli_error($db);
  }
  
  $outputlist=array();
  array_push($outputlist, $result);

  $output[]=array('REVIEW_STATUS' => $outputlist);
  print(json_encode($output));

  mysqli_close($db);
}
?>
