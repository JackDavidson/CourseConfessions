<form action="" method="post">
  <label>Department :</label>
  <input type="text" name="dept"/><br/>
  <label>Course Number :</label>
  <input type="text" name="num"/><br/>
  <input type="submit" name="search" value="Search"/><br/>
</form>
<?php
  include("config.php");
if($_SERVER["REQUEST_METHOD"] == "POST")
{
  //if the search button was pushed, perform a search query
  if ($_POST['search']) {
    //first create variables for form selection
    $courseDept=mysqli_real_escape_string($db,$_POST['dept']);
    $courseNum=mysqli_real_escape_string($db,$_POST['num']);

    //create sql query to REVIEWS table
    $sql="SELECT COURSE_CAT, COURSE_DES, FK_USER_ID, REVIEW, RATING, REVIEW_ID FROM REVIEWS WHERE COURSE_CAT='$courseDept' and COURSE_DES='$courseNum' ORDER BY RATING DESC";

    //create array to store output
    $output=array();

    //store query results into a variable
    $result=mysqli_query($db,$sql);
    $rowcount=mysqli_num_rows($result);

    $count=0;
    print("<form action='' method='post'>");
    while($row=$result->fetch_assoc()) {
      
      //for each row of results, print the review and a button to report the review
      displayResult($row, $count);
	  $count++;
    }
    print("</form>");
    //if the search ended with no results, print a failure
    if ($rowcount == 0) {
      print("No Reviews Found.");
    } //end search query
  }
  if ($_POST['report']) {
    //first deduce which review was reported by the ID
    $curID=$_POST['report'];
    $curID=substr($curID,6);
    settype($curID, "integer");
    //now, using the REVIEW_ID of the review, create a mysql query to flag the correct review
    $sql="UPDATE REVIEWS SET REPORT='1' WHERE REVIEW_ID='$curID'";
    
    if (mysqli_query($db,$sql)) {
      print("Thank you for reporting!<br> The review will be assessed by the administrators.");
    } else {
      print("Error reporting review.");
    }
  }
  if ($_POST['up']) {
    //first deduce which review was reported by the ID
    $curID=$_POST['up'];
    $curID=substr($curID,6);
    settype($curID, "integer");
    $test="test";
    //first search to make sure user hasn't upvoted review yet
    $sql="SELECT REVIEW FROM REVIEWS WHERE RATED LIKE '%$test%' and REVIEW_ID='$curID'";
    //if user has upvoted this review before, do not let them upvote, otherwise let them
    if (mysqli_query($db,$sql)) {
      print("You have already upvoted this review.");
    } else {
      //now, using the REVIEW_ID of the review, create a mysql query to flag the correct review
      $sql="UPDATE REVIEWS SET RATING=RATING+1, RATED=CONCAT('$test',RATED) WHERE REVIEW_ID='$curID'";
      
      if (mysqli_query($db,$sql)) {
        print("Thank you for upvoting!");
      } else {
        print("Error reporting review.");
      }
    }
  }
  //close database connection
  mysqli_close($db);
}
?>

<?php
  //function to display row results neatly and produce report button for each search result
  function displayResult($array, $num) {
    $course=($num + 1).". ";
    $course.=current($array);
    next($array);
    $course.=" ".current($array)."<br>";
    print($course);
    $author=next($array)."<br>";
    print($author);
    $review=next($array)."<br>";
    print($review);
    $rating="rating: ".next($array)." ";
    print($rating);
    $id=next($array);
    settype($id, "integer");
    print("<input type='submit' name='report' value='Report$id'>");
    print("<input type='submit' name='up' value='UpVote$id'><br>");
  }
?>
