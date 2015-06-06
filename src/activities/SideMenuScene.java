package activities;

import user.User;

import com.bitsplease.courseconfessions.R;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * This is the main file for the sliding side menu
 * 
 * Implements the Home, Courses, Write Review, and Logout buttons 
 * for the side menu
 * 
 * @author Jack - jack.davidson38@gmail.com
 * @author Byrdor - byrdor@gmail.com
 * 
 */
public class SideMenuScene extends BaseScene {

	public static final int HEIGHT_OF_TITLE_PIC = 130;

	RelativeLayout sideMenu;
	Button menuBtn;
	Button sideMenuIcon;
	Button sideMenuHome;
	Button sideMenuCourses;
	Button sideMenuRatings;
	Button sideMenuSettings;
	Button sideMenuLogout;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	// set some settings and apply them to the window, rest is explained in the
	// method
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set placeholder background ===== */
		ImageView image = new ImageView(this);
		LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
				(int) (widthPx), (int) (HEIGHT_OF_TITLE_PIC * nativeToPxRatio));
		image.setBackgroundResource(R.raw.titleplaceholder);
		image.setX((width / 2) * nativeToPxRatio - (widthPx / 2));
		image.setY(0);
		addContentView(image, linearLayout);
		/* ==== END set placeholder background ===== */

		/* ============ Add Menu Button ============ */
		menuBtn = new Button(this);

		menuBtn.setBackgroundResource(R.raw.slidemenubtn);
		menuBtn.setX(30 * nativeToPxRatio);
		menuBtn.setY(40 * nativeToPxRatio);

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
		/* ============ End Add Menu Button ============ */
		
		/* ============ Add a view for sliding in/out ============ */
		sideMenu = new RelativeLayout(this);
		RelativeLayout.LayoutParams menuViewLP = new RelativeLayout.LayoutParams(
				(int) (490 * nativeToPxRatio), (int) (1200 * nativeToPxRatio));
		sideMenu.setBackgroundColor(Color.parseColor("#0C1E2A"));
		sideMenu.setX(-500 * nativeToPxRatio);
		sideMenu.setY(130 * nativeToPxRatio);
		addContentView(sideMenu, menuViewLP);
		/* ============ End add a view for sliding in/out ============ */
		
		/* ========= Add Menu icon ========= */
		sideMenuIcon = new Button(this);
		sideMenuIcon.setBackgroundResource(R.raw.slidemenuicon);
		sideMenuIcon.setX(90 * nativeToPxRatio);
		sideMenuIcon.setY(90 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenBtnLP = new RelativeLayout.LayoutParams(
				(int) (300 * nativeToPxRatio), (int) (300 * nativeToPxRatio));
		sideMenuIcon.setLayoutParams(offscreenBtnLP);
		sideMenu.addView(sideMenuIcon);
		/* ========= END Menu Icon ========= */

		/* ========= Add Home button ========= */
		sideMenuHome = new Button(this);
		sideMenuHome.setBackgroundResource(R.raw.slidemenuhomescreen);
		sideMenuHome.setX(125 * nativeToPxRatio);
		sideMenuHome.setY(420 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenForgotBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuHome.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					home();
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
		sideMenuCourses.setX(125 * nativeToPxRatio);
		sideMenuCourses.setY(500 * nativeToPxRatio); // 440

		RelativeLayout.LayoutParams offscreenCoursesBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuCourses.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					courses();
					return true;
				}

				return true;
			}
		});
		sideMenuCourses.setLayoutParams(offscreenCoursesBtn);
		sideMenu.addView(sideMenuCourses);
		/* ========== END Courses button ========= */

		/* ========= Add Write Review button ========= */
		sideMenuSettings = new Button(this);
		sideMenuSettings.setBackgroundResource(R.raw.slidemenuwritereview);
		sideMenuSettings.setX(125 * nativeToPxRatio);
		sideMenuSettings.setY(580 * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenSettingsBtn = new RelativeLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		sideMenuSettings.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					writeReview();
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
		sideMenuLogout.setX(125 * nativeToPxRatio);
		sideMenuLogout.setY(660 * nativeToPxRatio);

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
	}

	/**
	 * The Home function from the side menu When called from Home
	 */
	public void home() {
		User user = new User(this);
		user.setScreen(Screen.MainMenuScreen);
		user.save(this);
		startScreen(Screen.MainMenuScreen);
		finish();
	}

	/**
	 * The course that the user is trying to find When pressed, he will be
	 * directed to the CSE courses page
	 */
	public void courses() {
		User user = new User(this);
		user.setScreen(Screen.CourseSelectScreen);
		user.save(this);
		startScreen(Screen.CourseSelectScreen);
		finish();
	}

	/**
	 * Write a review for a course
	 */
	public void writeReview() {
		User user = new User(this);
		user.setScreen(Screen.WriteReviewScreen);
		user.save(this);
		startScreen(Screen.WriteReviewScreen);
		finish();
	}

	/**
	 * The Logout function, self-explanatory Logs the user out and takes him
	 * back to the HomeScreen
	 */
	public void logout() {
		
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					User user = new User(getContext());
					user.logout();
					user.save(getContext());
					startScreen(Screen.LoginScreen);
					setResult(RESULT_OK);
					finish();
					break;

				case DialogInterface.BUTTON_NEGATIVE:
					// No button clicked
					break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setMessage("Log out?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("Cancel", dialogClickListener).show();

	}

	@Override
	// calls android.activity's onStart() method, and brings the menu button
	// and the logout button to the front
	protected void onStart() {
		super.onStart();
		menuBtn.bringToFront();
		// sideMenuHome.bringToFront();
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

}
