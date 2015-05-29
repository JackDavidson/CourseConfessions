package activities;

import com.bitsplease.courseconfessions.R;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BaseScene extends Activity {

	/* native size = 1280x800, or 1280x??? */
	public float nativeToPxRatio;
	public int widthPx;
	public int heightPx;
	protected int width;
	protected static final int height = 1280;

	
	RelativeLayout sideMenu;
	Button menuBt;
	Button logoutBt;

	@SuppressLint("ClickableViewAccessibility")
	@SuppressWarnings("deprecation")
	@Override
	// set some settings and apply them to the window, rest is explained in the
	// method
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== settings ==== */
		/*
		 * StrictMode.ThreadPolicy policy = new
		 * StrictMode.ThreadPolicy.Builder() .permitAll().build();
		 * StrictMode.setThreadPolicy(policy);
		 */

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

		/* ========= How to add a button ======================= */
		menuBt = new Button(this);

		menuBt.setBackgroundDrawable(getResources().getDrawable(R.raw.menubtn));
		menuBt.setX(20);
		menuBt.setY(30);

		RelativeLayout.LayoutParams menuLP = new RelativeLayout.LayoutParams(
				(int) (50 * nativeToPxRatio), (int) (50 * nativeToPxRatio));
		menuBt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					toggleSideMenu();
					return true;
				}

				return false;
			}
		});
		addContentView(menuBt, menuLP);
		/* ========= End How to add a button =================== */

		/* ====== Add a view for sliding in/out ========== */
		sideMenu = new RelativeLayout(this);
		RelativeLayout.LayoutParams menuViewLP = new RelativeLayout.LayoutParams(
				(int) (490 * nativeToPxRatio), (int) (1200 * nativeToPxRatio));
		//sideMenu.setLayoutParams(menuViewLP);
		sideMenu.setX(-500*nativeToPxRatio);
		sideMenu.setY(150);
		addContentView(sideMenu, menuViewLP);
		/* ===== End Add a view for sliding in/out ======== */

		/* ========= Add logout button to the sideMenu ============ */
		logoutBt = new Button(this);
		logoutBt.setBackgroundColor(Color.parseColor("#071017"));
		logoutBt.setX(0);
		logoutBt.setY(0);

		RelativeLayout.LayoutParams offscreenBtnLP = new RelativeLayout.LayoutParams(
				(int) (490 * nativeToPxRatio), (int) (1200 * nativeToPxRatio));
		logoutBt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					logout();
					return true;
				}

				return true;
			}
		});
		logoutBt.setLayoutParams(offscreenBtnLP);
		sideMenu.addView(logoutBt); // add the button to the side menu
		/* ======== End Off screen example log out BTN ======= */

		/* ====how to print out a debug statement in logcat===== */
		Log.i("ratio", "ratio: " + nativeToPxRatio);
		/* ====how to print out a debug statement in logcat===== */
	}

	@Override
	// calls android.activity's onStart() method, and brings the menu button
	// and the logout button to the front
	protected void onStart() {
		super.onStart();
		menuBt.bringToFront();
		logoutBt.bringToFront();
		sideMenu.bringToFront();
	}

	boolean sideMenuIsOut = false; // whether the side menu is out or not

	// brings the side menu in and out of view.
	private void toggleSideMenu() {
		ObjectAnimator mover = null; 
		if (sideMenuIsOut) {
			// slide from right to left
			mover = ObjectAnimator.ofFloat(sideMenu, "translationX", 0, -500*nativeToPxRatio);
		} else {
			// slide in from left
			mover = ObjectAnimator.ofFloat(sideMenu, "translationX", -500*nativeToPxRatio, 0);
			
		}
		sideMenuIsOut = !sideMenuIsOut;
		mover.start();
	}

	// logout. pretty self explanatory.
	private void logout() {

	}

	private boolean preventOpenNewScreen = true;

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
		case HomeScreen:
			Intent homeScreen = new Intent(this,
					activities.main.HomeScreen.class);
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

		}

	}

	// defines the different screens
	public enum Screen {
		HomeScreen, SignupScreen, ForgotScreen, CourseSelectScreen, WriteReviewScreen
	}
}
