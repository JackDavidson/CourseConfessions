package activities;

import com.bitsplease.courseconfessions.R;

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

}

