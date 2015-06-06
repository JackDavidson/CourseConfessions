package activities.main;

import util.LoginException;
import util.phpInteractions;
import visuals.PlacementEditText;
import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * This is the main file, the entry point into the program. the REAL entry point
 * is onCreateScene and in what you define there.
 * 
 * implements IOnSceneTouchListener is for touch interface directly through the
 * screen, not through the buttons. if all interface goes through your buttons,
 * that shouldn't be necessary.
 * 
 * @author Jack - jack.davidson38@gmail.com
 * @author Byrdor - byrdor@gmail.com
 * 
 */
public class ForgotScreen extends BaseScene {
	
	// ===========================================================
	// Fields
	// ===========================================================
	private PlacementEditText placeUsernameText;
	private PlacementEditText placeEmailText;

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set background ===== */
		PlacementImage image = new PlacementImage(this, R.raw.backgroundforgot,
				(int) (widthPx / (2 * nativeToPxRatio) - 1000 / 2), 0, 1000,
				1280);
		image.attachToScene();
		/* ==== END set background ===== */

		/* ========= Text Entry ========= */
		placeUsernameText = new PlacementEditText(this, width / 2 - 500 / 2
				+ 34, height / 2 - 80, 500, 70, "Username");
		placeEmailText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 + 52, 500, 70, "Email");
		/* ========= END Text Entry ========= */

		/* ========= Set Layout Params for screen ========= */
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		/* ========= END Layout Params ========= */

		
		/*** Notice: we may need to change to honeycomb ***/
		placeUsernameText.attachToScene();
		placeEmailText.attachToScene();
		
		
		/* ========= Forgot button ========= */
		Button forgotBtn = new Button(this);
		forgotBtn.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholderforgot));
		forgotBtn.setX(widthPx / 2 - lp.width / 2);
		forgotBtn.setY((height - 350) * nativeToPxRatio);

		RelativeLayout.LayoutParams forgot = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (100 * nativeToPxRatio));
		forgotBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					attemptPasswordReset();
					return true;
				}

				return false;
			}
		});
		addContentView(forgotBtn, forgot);
		/* ========= END Forgot Button ========= */

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
					startScreen(Screen.LoginScreen);
					finish();
					return true;
				}

				return false;
			}
		});
		addContentView(loginButton, loginBtnLayout);
		/* ========= End Login Button ========= */

		/* ========= Signup Button ========= */
		Button signupButton = new Button(this);
		signupButton.setBackgroundDrawable(getResources().getDrawable(
				R.raw.signupbtn));
		signupButton.setX((widthPx / 2) + 30 * nativeToPxRatio);
		signupButton.setY((height - 70) * nativeToPxRatio);

		RelativeLayout.LayoutParams signupBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (32 * nativeToPxRatio));
		signupButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					startScreen(Screen.SignupScreen);
					finish();
					return true;
				}

				return false;
			}
		});
		addContentView(signupButton, signupBtnLayout);
		/* ========= END Signup Button ========= */

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/* ===== Attempt Password Reset ===== */
	private void attemptPasswordReset() {
		/* Declare local variables */
		boolean sendEmailSuccess = false;
		String userName = placeUsernameText.getEditText().getText().toString();
		String userEmail = placeEmailText.getEditText().getText().toString();

		/* Attempt communication from server to attempt password reset */
		try {
			sendEmailSuccess = phpInteractions.attemptForgotPassword(userName,
					userEmail, this);
		} catch (LoginException e) {
			/* Throw an exception to the user */
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		/* Signup successful, take him back to the login screen */
		if (sendEmailSuccess) {
			this.startScreen(Screen.LoginScreen);
			finish();
		}
	}
	/* ===== End Attempt Password Reset ===== */

	@Override
	/* ===== Back button pressed, take user back to login screen ===== */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startScreen(Screen.LoginScreen);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	/* ===== End back button pressed ===== */
	
}