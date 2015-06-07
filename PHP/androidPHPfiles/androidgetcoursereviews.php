<?php
  include("config.php");
if($_SERVER["REQUEST_METHOD"] == "POST")
{
  //first create variables for form selection
  $courseDept=mysqli_real_escape_string($db,$_POST['dept']);
  $courseNum=mysqli_real_escape_string($db,$_POST['num']);
  $courseReport=($_POST['report']);
  $reviewID=($_POST['reviewid']);

  //if it is a search for reviews, do a query based on review search
  if ($courseDept != "") {
    //create sql query to REVIEWS table
    $sql="SELECT COURSE_CAT, COURSE_DES, FK_USER_ID, REVIEW, RATING, REVIEW_ID FROM REVIEWS WHERE COURSE_CAT='$courseDept' and COURSE_DES='$courseNum' ORDER BY COURSE_CAT, COURSE_DES, RATING DESC";

    //create array to store output
    $outputlist=array();

    //store query results into a variable
    $result=mysqli_query($db,$sql);

    while($row=$result->fetch_assoc()) {
      foreach ($row as $cur) {
        //push each portion of each returned review object to the json array
        array_push($outputlist, $cur);
      }
    }
    if (empty($outputlist)) {
      //if no objects were returned, return 'fail' to be sent to the json 
      array_push($outputlist, "fail");
    }

    //create namevaluepair reference to array for json encoding
    $output[]=array('REVIEWS' => $outputlist);
    
    //send an encoded json of the array
    print(json_encode($output));
  }
  //if post was a report of a course and not a search query for reviews report the review in question
  else if ($courseReport != "") {
    settype($reviewID, "integer");
    
    $sql="UPDATE REVIEWS SET REPORT='1' WHERE REVIEW_ID='$reviewID'";
    
    $rowcount=mysqli_num_rows($result);
    if (mysqli_query($db,$sql)) {
      $outputlist="Review Reported!";
    } else {
      $outputlist="fail";
    }
    //send an encoded json of the message to confirm or deny the review was reported
    $output[]=array('REPORT' => $outputlist);
    print(json_encode($output));
  }
  if ($_POST['up']) {
    //create an output array
    $outputlist=array();
    
    //set the username to a variable
    $userName=($_POST['username']);
    //first search to make sure user hasn't upvoted review yet
    $sql="SELECT RATED FROM REVIEWS WHERE REVIEW_ID='$reviewID'";
    //if user has upvoted this review before, do not let them upvote, otherwise let them
    if($sqlResult = mysqli_query($db,$sql)) {
      $sqlString = mysqli_fetch_array($sqlResult);
      $exploded = explode(',', $sqlString['RATED']);
      $inArray = in_array($userName, $exploded);
    }
    if ($sqlResult and $inArray) {
      $outputlist="You have already upvoted this review.";
    } else {
      $userName = $userName . ',';
      //now, using the REVIEW_ID of the review, create a mysql query to flag the correct review
      $sql="UPDATE REVIEWS SET RATING=RATING+1, RATED=CONCAT('$userName',RATED) WHERE REVIEW_ID='$reviewID'";
      
      if (mysqli_query($db,$sql)) {
        $outputlist="Thank you for upvoting!";
      } else {
        $outputlist="fail";
      }
    }
    //send an encoded json of the message to confirm or deny upvote, or signal query error_get_last
    $output[]=array('UPVOTE' => $outputlist);
    print(json_encode($output));
  }
  mysqli_close($db);
}
?>
