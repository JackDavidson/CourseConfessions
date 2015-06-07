<html>
  <head>
    <title>Course Confessions Admin Page</title>
  </head>
  <body><h3>Course Confessions Admin Page</h3><hr></body>
</html>
<?php
  include("config.php");

  //create boolean flag for true to detect flagged reviews and one to check for second submit
  $bool_set=true;

  //create login screen
  login();

  //first run to check initial login and list courses   
  if ($_SERVER["REQUEST_METHOD"] == "POST") {

    //if check for login submit button
    if ($_POST['button'] == 'Login') {
      $admin=mysqli_real_escape_string($db,$_POST['user']);
      $pass=mysqli_real_escape_string($db,$_POST['password']);
      
      //create an sql query to check admin login credentials
      $sql="SELECT * FROM USER WHERE PK_USER_ID='$admin' and PASSWORD='$pass' and ADMIN='$bool_set'";
      $result=mysqli_query($db,$sql);
      
      //check to make sure of a match
      $rowcount=mysqli_num_rows($result);

      if ($rowcount == 1) {
        //create an sql query instructed to pull all report flagged reviews
        $sql="SELECT COURSE_CAT, COURSE_DES, FK_USER_ID, REVIEW, REPORT_TEXT FROM REVIEWS WHERE REPORT='$bool_set'";

        //store query results into a variable
        $result=mysqli_query($db,$sql);
        $rowcount=mysqli_num_rows($result);

        //only list items if flagged reviews exist
        if ($rowcount > 0) {
          $outputlist=array();
          //set counter for the sake of variables and array of IDs for performing actions on
          $count=0;
          //create a form in html for administrators to delete unsavory posts
          print("<form action='' method='post'>");
          while($row=$result->fetch_assoc()) {
            //format the string nicely for viewing using the function pre-built and return ID to variable
            createEntry($row, $count);
            
            //increment count
            $count=$count+1;
          }
          //create submit button and end form
          print("<input type='submit' name='button' value='Update'/><br/>");
          print("</form>");
        } else { //if query has no results, notify administrator
          print("Currently no Flagged Reviews.");
        }
      } else { //if invalid login information for an admin account, deny access
        print("Access Denied.");
      }
    } //end login
    //if check for update server button
    if ($_POST['button'] == 'Update') {
      //create array for review IDs
      $IDs=array();
      //research for flagged posts to perform appropriate actions
      $sql="SELECT REVIEW_ID FROM REVIEWS WHERE REPORT='$bool_set'";
      $result=mysqli_query($db,$sql);
      //set counter
      $count=0;
      while($row=$result->fetch_assoc()) {
        $curID=current($row);
        settype($curID, "integer");
        $select="select$count";
        $curOption=mysqli_real_escape_string($db,$_POST[$select]);
        //if selection is delete, delete from database
        if ($curOption=="delete") {
          $sql="DELETE FROM REVIEWS WHERE REVIEW_ID='$curID'";
          mysqli_query($db,$sql);
        } else if ($curOption=="remove flag") {
          //if option is to remove flag, remove flag from review in database
          $sql="UPDATE REVIEWS SET REPORT='0' and REPORT_TEXT=NULL WHERE REVIEW_ID='$curID'";
          mysqli_query($db,$sql);
        }
        $count=$count+1;
      }
      print("Actions Performed!");
      print("<br>Please login again to perform more actions.");
    } //end admin priveledges
  } //end POST

  mysqli_close($db);
  ?>
  
<?php
  //create function to add elements to list for admin priviledges
  function createEntry($row, $num) {
    if (is_array($row)) {
      print("COURSE: ");
      $course=current($row);
      $course.=" ";
      next($row);
      $course.=current($row);
      next($row);
      //first set textarea for course category and number
      print("<textarea rows='1' cols='7'>$course</textarea><br>");
      print("AUTHOR: ");
      $author=current($row);
      next($row);
      //next set textarea for author
      print("<textarea rows='1' cols='15'>$author</textarea><br>");
      print("REVIEW: <br>");
      $review=current($row);
      next($row);
      //next is the review itself
      print("<textarea name='review$num' rows='6' cols='28'>$review</textarea><br>");
      print("REPORTER'S COMMENTS: <br>");
      $comments=current($row);
      next($row);
      //lastly post the comments of the reporter
      print("<textarea rows='3' cols='28'>$comments</textarea>");
      //create dropdown and set variable for it according to $num
      print("<select name='select$num'><option>select one</option><option>delete</option><option>remove flag</option></select><br>");
    } else {
      //if no reviews are currently flagged, let the admin know
      print("No reviews are currently flagged as inappropriate.");
    }
  }
  //function to create login screen
  function login() {
    print("<form action='' method='post'>");
    print("Username : ");
    print("<input type='text' name='user'/><br/>");
    print("Password : ");
    print("<input type='password' name='password'/><br/>");
    print("<input type='submit' name='button' value='Login'/><br/>");
    print("</form>");
  }
?>

