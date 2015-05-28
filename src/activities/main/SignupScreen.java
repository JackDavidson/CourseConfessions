package activities.main;

import user.User;
import util.LoginException;
import util.SDCardWriter;
import util.phpInteractions;
import visuals.PlacementEditText;
import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
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
	private TextView loginResultTextView;

	// ===========================================================
	// Constructors
	// ===========================================================
	//phpInteractions php = new phpInteractions();

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

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));

		/* ========= Text Entry ========= */
		placeUserText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 - 179, 500, 70, "Username");
		placeEmailText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 - 48, 500, 70, "Email");
		placePassText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 + 84, 500, 70, "Password");
		placeConfirmPassText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 + 216, 500, 70, "Confirm Password");
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
					attemptLogin();
					return true;
				}

				return false;
			}
		});
		addContentView(signupBtn, signup);
		/* ========= End Signup Button  ========= */
		
		/* ========= Login Button ========= */
		Button loginButton = new Button(this);
		loginButton.setBackgroundDrawable(getResources().getDrawable(
				R.raw.loginbtn));
		loginButton.setX((widthPx / 2) - 170*nativeToPxRatio); //- lp.width / 2));// + 65*nativeToPxRatio);
		loginButton.setY((height - 70) * nativeToPxRatio);
		
		RelativeLayout.LayoutParams loginBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (28 * nativeToPxRatio));
		loginButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					finish();
					return true;
				}

				return false;
			}
		});
		addContentView (loginButton, loginBtnLayout);
		/* ========= End Login Button ========= */
		
		/* ========= Forgot Button ========= */
		Button forgotButton = new Button(this);
		forgotButton.setBackgroundDrawable(getResources().getDrawable(
				R.raw.forgotbtn));
		forgotButton.setX((widthPx / 2) + 30*nativeToPxRatio);//  - lp.width));// + 250*nativeToPxRatio);
		forgotButton.setY((height - 70) * nativeToPxRatio);

		RelativeLayout.LayoutParams forgotBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (32 * nativeToPxRatio));
		forgotButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					finish();
					startScreen(Screen.ForgotScreen);
					return true;
				}

				return false;
			}
		});
		addContentView (forgotButton, forgotBtnLayout);
		/* ========= End Forgot Button ========= */
		
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