package activities;

import com.bitsplease.courseconfessions.R;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BaseScene extends Activity {


	public float nativeToPxRatio;
	public int widthPx;
	public int heightPx;
	protected int width;
	protected static final int height = 1280;

	RelativeLayout sideMenu;
	Button menuBtn;
	Button sideMenuIcon;
	Button sideMenuHome;
	Button sideMenuCourses;
	Button sideMenuRatings;
	Button sideMenuSettings;
	Button sideMenuLogout;

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

		/* ========= How to add a button ======================= */
		menuBtn = new Button(this);

		menuBtn.setBackgroundResource(R.raw.menubtn);
		menuBtn.setX(15 * nativeToPxRatio);
		menuBtn.setY(20 * nativeToPxRatio);

		RelativeLayout.LayoutParams menuLP = new RelativeLayout.LayoutParams(
				(int) (50 * nativeToPxRatio), (int) (50 * nativeToPxRatio));
		menuBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					toggleSideMenu();
					return true;
				}

				return false;
			}
		});
		addContentView(menuBtn, menuLP);
		/* ========= End How to add a button =================== */

		/* ====== Add a view for sliding in/out ========== */
		sideMenu = new RelativeLayout(this);
		RelativeLayout.LayoutParams menuViewLP = new RelativeLayout.LayoutParams(
				(int) (490 * nativeToPxRatio), (int) (1200 * nativeToPxRatio));
		sideMenu.setBackgroundColor(Color.parseColor("#0C1E2A"));
		sideMenu.setX(-500 * nativeToPxRatio);
		sideMenu.setY(100 * nativeToPxRatio);
		addContentView(sideMenu, menuViewLP);
		/* ===== End Add a view for sliding in/out ======== */

		/* ========= Add Menu icon ========= */
		sideMenuIcon = new Button(this);
		sideMenuIcon.setBackgroundResource(R.raw.slidemenuicon);
		sideMenuIcon.setX(50 * nativeToPxRatio);
		sideMenuIcon.setY(50 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenBtnLP = new RelativeLayout.LayoutParams(
				(int) (350 * nativeToPxRatio), (int) (350 * nativeToPxRatio));
		sideMenuIcon.setLayoutParams(offscreenBtnLP);
		sideMenu.addView(sideMenuIcon);
		/* ========= END Menu Icon ========= */

		/* ========= Add Home button ========= */
		sideMenuHome = new Button(this);
		sideMenuHome.setBackgroundResource(R.raw.slidemenuhome);
		sideMenuHome.setX(100 * nativeToPxRatio);
		sideMenuHome.setY(380 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenForgotBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuHome.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					logout();
					return true;
				}

				return true;
			}
		});
		sideMenuHome.setLayoutParams(offscreenForgotBtn);
		sideMenu.addView(sideMenuHome);
		/* ========== END Home button ========= */

		/* ========= Add Courses button ========= */
		sideMenuCourses = new Button(this);
		sideMenuCourses.setBackgroundResource(R.raw.slidemenucourses);
		sideMenuCourses.setX(100 * nativeToPxRatio);
		sideMenuCourses.setY(440 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenCoursesBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuCourses.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					logout();
					return true;
				}

				return true;
			}
		});
		sideMenuCourses.setLayoutParams(offscreenCoursesBtn);
		sideMenu.addView(sideMenuCourses);
		/* ========== END Courses button ========= */

		/* ========= Add Ratings button ========= */
		sideMenuRatings = new Button(this);
		sideMenuRatings.setBackgroundResource(R.raw.slidemenuratings);
		sideMenuRatings.setX(100 * nativeToPxRatio);
		sideMenuRatings.setY(500 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenRatingsBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuRatings.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					logout();
					return true;
				}

				return true;
			}
		});
		sideMenuRatings.setLayoutParams(offscreenRatingsBtn);
		sideMenu.addView(sideMenuRatings);
		/* ========== END Ratings button ========= */

		/* ========= Add Settings button ========= */
		sideMenuSettings = new Button(this);
		sideMenuSettings.setBackgroundResource(R.raw.slidemenusettings);
		sideMenuSettings.setX(100 * nativeToPxRatio);
		sideMenuSettings.setY(560 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenSettingsBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuSettings.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					logout();
					return true;
				}

				return true;
			}
		});
		sideMenuSettings.setLayoutParams(offscreenSettingsBtn);
		sideMenu.addView(sideMenuSettings);
		/* ========== END Settings button ========= */

		/* ========= Add Logout button ========= */
		sideMenuLogout = new Button(this);
		sideMenuLogout.setBackgroundResource(R.raw.slidemenulogout);
		sideMenuLogout.setX(100 * nativeToPxRatio);
		sideMenuLogout.setY(620 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenLogoutBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuLogout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					logout();
					return true;
				}

				return true;
			}
		});
		sideMenuLogout.setLayoutParams(offscreenLogoutBtn);
		sideMenu.addView(sideMenuLogout);
		/* ========== END Logout button ========= */

		/* ====how to print out a debug statement in logcat===== */
		Log.i("ratio", "ratio: " + nativeToPxRatio);
		/* ====how to print out a debug statement in logcat===== */
	}

	// logout. pretty self explanatory.
	private void logout() {

	}

	@Override
	// calls android.activity's onStart() method, and brings the menu button
	// and the logout button to the front
	protected void onStart() {
		super.onStart();
		menuBtn.bringToFront();
		sideMenuHome.bringToFront();
		sideMenu.bringToFront();
	}

	boolean sideMenuIsOut = false; // whether the side menu is out or not

	// brings the side menu in and out of view.
	private void toggleSideMenu() {
		ObjectAnimator mover = null;
		if (sideMenuIsOut) {
			// slide from right to left
			mover = ObjectAnimator.ofFloat(sideMenu, "translationX", 0, -500
					* nativeToPxRatio);
		} else {
			// slide in from left
			mover = ObjectAnimator.ofFloat(sideMenu, "translationX", -500
					* nativeToPxRatio, 0);

		}
		sideMenuIsOut = !sideMenuIsOut;
		mover.start();
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

		}

	}

	// defines the different screens
	public enum Screen {
		HomeScreen, SignupScreen, ForgotScreen, CourseSelectScreen, WriteReviewScreen, CourseReviewsBrowser, MainMenuScreen
	}

	protected BaseScene getContext() {
		return this;
	}
}
