package activities;

import user.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class BaseScene extends Activity {

	public float nativeToPxRatio;
	public int widthPx;
	public int heightPx;
	protected int width;
	protected static final int height = 1280;

	RelativeLayout sideMenu;

	@SuppressLint("ClickableViewAccessibility")
	@SuppressWarnings("deprecation")
	@Override
	// set some settings and apply them to the window, rest is explained in the
	// method
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== settings ==== */
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		/* ==== END settings ==== */

		Display display = getWindowManager().getDefaultDisplay();
		// get the width and height of the screen in pixels for our ratio
		// calculations
		widthPx = display.getWidth();
		heightPx = display.getHeight();
		// calculate what the height needs to be. width stays 1280.
		float widthHeightRatio = (float) widthPx / (float) heightPx;
		width = (int) (height * widthHeightRatio);
		nativeToPxRatio = (float) (float) heightPx / height;

	}

	private boolean preventOpenNewScreen = false;

	@Override
	protected void onResume() {
		super.onResume();
		preventOpenNewScreen = false;
	}

	// start a certain screen, the screen which is going to be started is passed
	// into the method
	public void startScreen(Screen screen) {
		if (preventOpenNewScreen) {
			Log.e("", "prevent open");
			return;
		}
		preventOpenNewScreen = true;
		switch (screen) {
		case LoginScreen:
			Intent homeScreen = new Intent(this,
					activities.main.LoginScreen.class);
			this.startActivity(homeScreen);
			break;
		case SignupScreen:
			Intent signupScreen = new Intent(this,
					activities.main.SignupScreen.class);
			this.startActivity(signupScreen);
			break;
		case ForgotScreen:
			Intent forgotScreen = new Intent(this,
					activities.main.ForgotScreen.class);
			this.startActivity(forgotScreen);
			break;
		case MainMenuScreen:
			Intent mainMenuScreen = new Intent(this,
					activities.main.MainMenu.class);
			this.startActivity(mainMenuScreen);
			break;
		case CourseSelectScreen:
			Intent courseSelectScreen = new Intent(this,
					activities.courseSelect.CourseSelectScreen.class);
			this.startActivity(courseSelectScreen);
			break;
		case WriteReviewScreen:
			Intent writeReviewScreen = new Intent(this,
					activities.writeReview.WriteReviewScreen.class);
			this.startActivity(writeReviewScreen);
			break;
		case CourseReviewsBrowser:
			Intent CourseReviewsBrowser = new Intent(this,
					activities.courseReviewsBrowser.CourseReviewsBrowser.class);
			this.startActivity(CourseReviewsBrowser);
			break;
		default:
			Log.e("BaseScene", "Could not find screen ID: " + screen);
			break;

		}

	}

	// defines the different screens
	public enum Screen {
		LoginScreen, SignupScreen, ForgotScreen, CourseSelectScreen, WriteReviewScreen, CourseReviewsBrowser, MainMenuScreen
	}

	protected BaseScene getContext() {
		return this;
	}

}
