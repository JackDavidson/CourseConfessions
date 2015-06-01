package activities.main;

import user.User;
import util.LoginException;
import util.phpInteractions;
import visuals.PlacementEditText;
import visuals.PlacementImage;
import com.bitsplease.courseconfessions.R;
import activities.BaseScene;
import android.annotation.SuppressLint;
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
	
	// ===========================================================
	// Constructors
	// ===========================================================
	//phpInteractions php = new phpInteractions();

	// ===========================================================
	@SuppressLint("ClickableViewAccessibility")
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@SuppressWarnings("deprecation")
	@Override
	//set background display text and other stuff, mostly explained in method
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set background ===== */
		PlacementImage image = new PlacementImage(this, R.raw.background,
				(int) (widthPx / (2 * nativeToPxRatio) - 1000 / 2), 0, 1000,
				1280);
		image.attachToScene();
		/* ==== END set background ===== */

		/* ========= Text Entry ========= */
		placeUserText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 - 104, 500, 70, "Username");
		placePassText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 + 30, 500, 70, "Password");
		/* ========= END Text Entry ========= */
		
		/* ========= Set Layout Params for screen ========= */
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		/* ========= END Layout Params ========= */
		
		/********
		 * notice!!!!! we may need to change to honeycomb (api 11/android3.0)for
		 * this!!! TODO
		 *****/
		placeUserText.attachToScene();
		placePassText.attachToScene();
		/* ========= End How to do text entry ================== */

		/* ========= Login button ========= */
		Button loginBtn = new Button(this);
		loginBtn.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholderlogin));
		loginBtn.setX(widthPx / 2 - lp.width / 2);
		loginBtn.setY((height - 350) * nativeToPxRatio);

		RelativeLayout.LayoutParams login = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (100 * nativeToPxRatio));
		loginBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					attemptLogin();
					return true;
				}

				return false;
			}
		});
		addContentView(loginBtn, login);
		/* ========= End Login Button  ========= */
		
		/* ========= Signup Button ========= */
		Button signupButton = new Button(this);
		signupButton.setBackgroundDrawable(getResources().getDrawable(
				R.raw.signupbtn));
		signupButton.setX((widthPx / 2) - 170*nativeToPxRatio);
		signupButton.setY((height - 70) * nativeToPxRatio);

		RelativeLayout.LayoutParams signupBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (28 * nativeToPxRatio));
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
		addContentView (signupButton, signupBtnLayout);
		/* ========= End Signup Button ========= */
		
		/* ========= Forgot Button ========= */
		Button forgotButton = new Button(this);
		forgotButton.setBackgroundResource(R.raw.forgotbtn);
		forgotButton.setX((widthPx / 2) + 30*nativeToPxRatio);
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
		addContentView (forgotButton, forgotBtnLayout);
		/* ========= End Forgot Button ========= */
	}

	@Override
	/**
	 *  call baseScene.onResume(); 
	 *  */
	protected void onResume() {
		super.onResume();
	}
	
	/* Attempt login when user click on the login screen */
	private void attemptLogin() {
		boolean loginSuccess = false;
		User user = null;
		String userName = placeUserText.getEditText().getText().toString();
		String userPass = placePassText.getEditText().getText().toString();

		this.startScreen(Screen.MainMenuScreen);
		finish();
		try {
			user = phpInteractions.attemptLoginAndCreateUser(userName, userPass, this);
			loginSuccess = true;
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		if (loginSuccess) {
			/* Make some toast, but butter it this time */
			Toast.makeText(this, "Welcome " + user.getRealName(),
					Toast.LENGTH_LONG).show();
			this.startScreen(Screen.CourseSelectScreen);
			finish();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}