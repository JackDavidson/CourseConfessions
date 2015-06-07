<form action="" method="post">
  <label>Username :</label>
  <input type="text" name="author"/><br/>
  <select name="dept">
    <option>CSE</option>
  </select>
  <select name="courseNum">
    <option>21</option>
    <option>100</option>
    <option>110</option>
    <option>120</option>
    <option>140</option>
    <option>140L</option>
  <select><br/>
  <label>Review :</label><br/>
  <textarea name="review" cols="50" rows="5"></textarea><br/>
  <input type="submit" value="Submit"/><br/>
</form>
<?php
include("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST")
{
  //first create variables for form selection
  $curAuthor=mysqli_real_escape_string($db,$_POST['author']);
  $newReview=mysqli_real_escape_string($db,$_POST['review']);
  $revDept=($_POST['dept']);
  $revCourseNum=($_POST['courseNum']);
  //make sure review is non-empty
  if (!(strlen($newReview) == 0)) {
    //write objects to sql database
    $sql="INSERT INTO REVIEWS (FK_USER_ID, COURSE_CAT, COURSE_DES, REVIEW, RATED) VALUES ('$curAuthor', '$revDept', '$revCourseNum', '$newReview', '$curAuthor')";
    //check for successful insertion and echo error if didn't work
    if (mysqli_query($db, $sql)) {
      echo "Successfully submitted Review!";
    }
  }
  else {
    echo "Error submitting Review.";
    mysqli_error($db);
  }
  
  mysqli_close($db);
}
?>

