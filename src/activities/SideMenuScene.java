package activities;

import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene.Screen;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SideMenuScene extends BaseScene {

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
		
		/* ==== Set background ===== */
		PlacementImage image = new PlacementImage(this, R.raw.titleplaceholder,
				(int) (widthPx / (2 * nativeToPxRatio) - 1000 / 2), 0, 1000, (int) (90*nativeToPxRatio));
		image.attachToScene();
		/* ==== END set background ===== */
		
		/* ========= How to add a button ======================= */
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
		/* ========= End How to add a button =================== */

		/* ====== Add a view for sliding in/out ========== */
		sideMenu = new RelativeLayout(this);
		RelativeLayout.LayoutParams menuViewLP = new RelativeLayout.LayoutParams(
				(int) (490 * nativeToPxRatio), (int) (1200 * nativeToPxRatio));
		sideMenu.setBackgroundColor(Color.parseColor("#0C1E2A"));
		sideMenu.setX(-500 * nativeToPxRatio);
		sideMenu.setY(134 * nativeToPxRatio);
		addContentView(sideMenu, menuViewLP);
		/* ===== End Add a view for sliding in/out ======== */

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
	 * The Home function from the side menu
	 * When called from Home, don't do anything
	 */
	public void home() {
		startScreen(Screen.MainMenuScreen);
		finish();
	}
	
	/**
	 * The course that the user is trying to find
	 * When pressed, he will be directed to the CSE courses page
	 */
	public void courses() {
		startScreen(Screen.CourseSelectScreen);
		finish();
	}
	
	/**
	 * Write a review for a course
	 */
	public void writeReview() {
		startScreen(Screen.WriteReviewScreen);
		finish();
	}
	
	/**
	 * The Logout function, self-explanatory
	 * Logs the user out and takes him back to the HomeScreen 
	 */
	private void logout() {
		startScreen(Screen.HomeScreen);
		finish();
	}

	
	@Override
	// calls android.activity's onStart() method, and brings the menu button
	// and the logout button to the front
	protected void onStart() {
		super.onStart();
		menuBtn.bringToFront();
		//sideMenuHome.bringToFront();
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

