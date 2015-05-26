package activities.main;

import user.User;
import util.LoginException;
import util.SDCardWriter;
import util.phpInteractions;
import visuals.PlacementEditText;
import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/* contact Jack for questions on this file. look up andengine examples on github for examples of how to do stuff */
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
public class HomeScreen extends BaseScene {
	// ===========================================================
	// Constants
	// ===========================================================
	// private static final int height = 1280;
	// private static int width;

	// ===========================================================
	// Fields
	// ===========================================================
	private PlacementEditText placeUserText;
	private PlacementEditText placePassText;
	private TextView loginResultTextView;

	// ===========================================================
	// Constructors
	// ===========================================================
	phpInteractions php = new phpInteractions();

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set background ===== */
		PlacementImage image = new PlacementImage(this, R.raw.background,
				(int) (widthPx / (2 * nativeToPxRatio) - 1000 / 2), 0, 1000,
				1280);
		image.attachToScene();
		/* ==== END set background ===== */

		/* ====== how to display text ====== */
		loginResultTextView = new TextView(this);
		loginResultTextView.setX(50 * nativeToPxRatio);
		loginResultTextView.setY((height / 2 + 100) * nativeToPxRatio);
		RelativeLayout.LayoutParams loginResultParams = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		loginResultTextView.setTextColor(Color.WHITE);
		loginResultTextView.setText("Ready");
		addContentView(loginResultTextView, loginResultParams);
		/* ==== END how to display text ==== */

		/* ========= How to do text entry ====================== */
		placeUserText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 - 104, 500, 70, "Username");
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		placePassText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 + 30, 500, 70, "Password");

		/********
		 * notice!!!!! we may need to change to honeycomb (api 11/android3.0)for
		 * this!!! TODO
		 *****/
		placeUserText.attachToScene();
		placePassText.attachToScene();
		/* ========= End How to do text entry ================== */

		/* ========= How to add a button ======================= */
		Button bt = new Button(this);
		bt.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholderlogin));
		bt.setX(widthPx / 2 - lp.width / 2);
		bt.setY((height - 200) * nativeToPxRatio);

		RelativeLayout.LayoutParams login = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (100 * nativeToPxRatio));
		bt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					attemptLogin();
					return true;
				}

				return false;
			}
		});
		addContentView(bt, login);
		/* ========= End How to add a button =================== */

		/* ======== Quick how to write/read files ============== */
		SDCardWriter.writeFile(getFilesDir().toString(), "placeholderName",
				"placeholderComment");
		String read = SDCardWriter.readFile(getFilesDir().toString()
				+ "placeholderName");
		Toast.makeText(this, "Read file, result is: " + read, Toast.LENGTH_LONG)
				.show();
		/* ========== END how to write/read files ============== */

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void attemptLogin() {
		boolean loginSuccess = false;
		User user = null;
		String userName = placeUserText.getEditText().getText().toString();
		String userPass = placePassText.getEditText().getText().toString();

		try {
			user = phpInteractions.attemptLoginAndCrateUser(userName, userPass,
					this);
			loginSuccess = true;
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			this.loginResultTextView.setText(e.getMessage());
		}
		if (loginSuccess) {
			// username/pass are valid, and we can now log in.
			this.loginResultTextView.setText("Login success! as: "
					+ user.getRealName());
			this.startScreen(Screen.CourseSelectScreen);
		}
		
		this.startScreen(Screen.WriteReviewScreen);
	}

}