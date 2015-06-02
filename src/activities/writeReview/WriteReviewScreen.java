package activities.writeReview;

import com.bitsplease.courseconfessions.R;

import user.User;
import visuals.PlacementEditText;
import activities.SideMenuScene;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/* contact TODO for questions on this file */
/**
 * This is the main file, the entry point into the program. the REAL entry point
 * is onCreateScene and in what you define there.
 * 
 * Andengine provides a "SimpleBaseGameActivity from which your game activity
 * (in this case AndengineHello) should always be extended
 * 
 * implements IOnSceneTouchListener is for touch interface directly through the
 * screen, not through the buttons. if all interface goes through your buttons,
 * that shouldn't be necessary.
 * 
 * @author Jack - jack.davidson38@gmail.com
 * 
 */
//this will be the class that holds the write review screen.
public class WriteReviewScreen extends SideMenuScene {

	private PlacementEditText placeReviewText; //the textbox where we're putting the review in 
	//TODO: cap the textbox at 1000 characters?

	@Override
	//when the method is created this is ran. Most of the implementation 
	//is explained in the method itself.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* ==== Set placeholder write review title  ===== */		
		ImageView image = new ImageView(this);
		LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
				(int) (220*nativeToPxRatio), (int) (68*nativeToPxRatio));
		image.setBackgroundResource(R.raw.slidemenuwritereview);
		image.setX((width / 2)*nativeToPxRatio - (220 / 2)*nativeToPxRatio);
		image.setY(35*nativeToPxRatio);
		addContentView(image, linearLayout);
		/* ==== END set placeholder write review title ===== */
		
		/* ===== Text Entry ===== */
		int reviewTextWidth = 800 - 80;
		int reviewTextX = width / 2 - reviewTextWidth / 2;
		int reviewTextHeight = height;
		placeReviewText = new PlacementEditText(this, reviewTextX, 0,
				reviewTextWidth, reviewTextHeight, "Review");
		placeReviewText.attachToScene();
		/* === End Text Entry === */

		/* ====== reloading the User object ======== */
		User user = new User(this);
		String userNamePass = "username: " + user.getUserName() + "\n"
				+ "password: " + user.getPassword();
		Toast.makeText(this, "Recreated user:\n" + userNamePass,
				Toast.LENGTH_LONG).show();
		/* ===== end reloading the user object ==== */

	}
	
	@Override
	/**
	 * Override the SideMenuScene writeReview function so the current 
	 * page is not reloaded.
	 */
	public void writeReview() {
		/** Do nothing, on purpose */
	}
	
}