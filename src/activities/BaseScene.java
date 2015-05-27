package activities;

import com.bitsplease.courseconfessions.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
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

	Button menuBt;
	Button logoutBt;

	@SuppressLint("ClickableViewAccessibility")
	@SuppressWarnings("deprecation")
	@Override
	//set some settings and apply them to the window, rest is explained in the method
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
		menuBt = new Button(this);

		menuBt.setBackgroundDrawable(getResources().getDrawable(R.raw.menubtn));
		menuBt.setX(10);
		menuBt.setY(10);

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

		/* ========= Off screen example log out BTN ============ */
		logoutBt = new Button(this);

		logoutBt.setBackgroundDrawable(getResources().getDrawable(R.raw.logoutbtn));
		logoutBt.setX(0);
		logoutBt.setY(75);

		RelativeLayout.LayoutParams offscreenBtnLP = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (75 * nativeToPxRatio));
		logoutBt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					logout();
					return true;
				}

				return false;
			}
		});
		addContentView(logoutBt, offscreenBtnLP);
		/* ======== End Off screen example log out BTN ======= */
		
		
		 /* ====how to print out a debug statement in logcat=====*/
		Log.i("ratio", "ratio: " + nativeToPxRatio);
		/* ====how to print out a debug statement in logcat=====*/
	}

	@Override
	//calls android.activity's onStart() method, and brings the menu button 
	//and the logout button to the front
	protected void onStart() {
		super.onStart();
		menuBt.bringToFront();
		logoutBt.bringToFront();
	}

	boolean sideMenuIsOut = false; //whether the side menu is out or not
	//brings the side menu in and out of view.
	private void toggleSideMenu() {
		// TODO: slide in the menu buttons
		TranslateAnimation animation = null;
		if(sideMenuIsOut){
			animation = new TranslateAnimation(-500*nativeToPxRatio, 0, 0, 0);
		} else {
			animation = new TranslateAnimation(0, -500*nativeToPxRatio, 0, 0);
		}
		sideMenuIsOut = !sideMenuIsOut;
		animation.setDuration(300); // duartion in ms
		animation.setFillAfter(true);
		logoutBt.startAnimation(animation);
	}
	//logout. pretty self explanatory.
	private void logout() {
		
	}
	//start a certain screen, the screen which is going to be started is passed into the method
	public void startScreen(Screen screen) {
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
	//defines the different screens
	public enum Screen {
		HomeScreen, SignupScreen, ForgotScreen, CourseSelectScreen, WriteReviewScreen
	}
}
