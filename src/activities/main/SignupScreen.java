package activities.main;

import util.LoginException;
import util.phpInteractions;
import visuals.PlacementEditText;
import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import activities.BaseScene.Screen;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
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
public class SignupScreen extends BaseScene {
	// ===========================================================
	// Constants
	// ===========================================================
	// private static final int height = 1280;
	// private static int width;

	// ===========================================================
	// Fields
	// ===========================================================
	private PlacementEditText placeUserText;
	private PlacementEditText placeEmailText;
	private PlacementEditText placePassText;
	private PlacementEditText placeConfirmPassText;

	// ===========================================================
	// Constructors
	// ===========================================================
	// phpInteractions php = new phpInteractions();

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set background ===== */
		PlacementImage image = new PlacementImage(this, R.raw.backgroundsignup,
				(int) (widthPx / (2 * nativeToPxRatio) - 1000 / 2), 0, 1000,
				1280);
		image.attachToScene();
		/* ==== END set background ===== */

		/* ========= Layout Params for screen width ========= */
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		/* ========= END Layout Params ========= */

		/* ========= Text Entry ========= */
		placeUserText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 - 179, 500, 70, "Username");
		placeEmailText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 - 48, 500, 70, "Email");
		placePassText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 + 84, 500, 70, "Password");
		placeConfirmPassText = new PlacementEditText(this, width / 2 - 500 / 2
				+ 34, height / 2 + 216, 500, 70, "Confirm Password");
		/* ========= End Text Entry ========= */

		/********
		 * notice!!!!! we may need to change to honeycomb (api 11/android3.0)for
		 * this!!! TODO
		 *****/
		placeUserText.attachToScene();
		placeEmailText.attachToScene();
		placePassText.attachToScene();
		placeConfirmPassText.attachToScene();
		/* ========= End How to do text entry ================== */

		/* ========= Signup button ========= */
		Button signupBtn = new Button(this);
		signupBtn.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholdersignup));
		signupBtn.setX(widthPx / 2 - lp.width / 2);
		signupBtn.setY((height - 250) * nativeToPxRatio);

		RelativeLayout.LayoutParams signup = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (100 * nativeToPxRatio));
		signupBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					attemptSignup();
					return true;
				}

				return false;
			}
		});
		addContentView(signupBtn, signup);
		/* ========= End Signup Button ========= */

		/* ========= Login Button ========= */
		Button loginButton = new Button(this);
		loginButton.setBackgroundDrawable(getResources().getDrawable(
				R.raw.loginbtn));
		loginButton.setX((widthPx / 2) - 170 * nativeToPxRatio);
		loginButton.setY((height - 70) * nativeToPxRatio);

		RelativeLayout.LayoutParams loginBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (28 * nativeToPxRatio));
		loginButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startScreen(Screen.HomeScreen);
					finish();
					return true;
				}

				return false;
			}
		});
		addContentView(loginButton, loginBtnLayout);
		/* ========= End Login Button ========= */

		/* ========= Forgot Button ========= */
		Button forgotButton = new Button(this);
		forgotButton.setBackgroundDrawable(getResources().getDrawable(
				R.raw.forgotbtn));
		forgotButton.setX((widthPx / 2) + 30 * nativeToPxRatio);
		forgotButton.setY((height - 70) * nativeToPxRatio);

		RelativeLayout.LayoutParams forgotBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (32 * nativeToPxRatio));
		forgotButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startScreen(Screen.ForgotScreen);
					finish();
					return true;
				}

				return false;
			}
		});
		addContentView(forgotButton, forgotBtnLayout);
		/* ========= End Forgot Button ========= */

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void attemptSignup() {
		boolean signupSuccess = false;
		String userName = placeUserText.getEditText().getText().toString();
		String userEmail = placeEmailText.getEditText().getText().toString();
		String userPass = placePassText.getEditText().getText().toString();
		String userConfirmPass = placeConfirmPassText.getEditText().getText()
				.toString();

		try {
			signupSuccess = phpInteractions.attemptSignup(userName, userEmail, userPass, userConfirmPass, this);
		} catch (LoginException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		if (signupSuccess) {
			this.startScreen(Screen.HomeScreen);
			finish();
		}
	}

}