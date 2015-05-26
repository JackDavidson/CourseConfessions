package activities.writeReview;

import user.User;
import util.SDCardWriter;
import visuals.PlacementEditText;
import activities.BaseScene;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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
public class WriteReviewScreen extends BaseScene {

	private PlacementEditText placeReviewText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== TODO set background ===== */
		/* === END how to set background ==== */

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
}