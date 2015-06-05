package activities.main;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import user.User;
import util.phpInteractions;

import com.bitsplease.courseconfessions.R;

import activities.SideMenuScene;
import activities.BaseScene.Screen;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainMenu extends SideMenuScene {

	@Override
	/** 
	 * Main Menu, tell the user what Course Confessions is all about.
	 * Contact EricK about this file 
	 * */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set placeholder home title ===== */
		ImageView image = new ImageView(this);
		LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		image.setBackgroundResource(R.raw.slidemenuhomescreen);
		image.setX((width / 2) * nativeToPxRatio - (220 / 2) * nativeToPxRatio);
		image.setY(35 * nativeToPxRatio);
		addContentView(image, linearLayout);
		/* ==== END set placeholder home title ===== */

		/* === Set up background ==== */
		getWindow().getDecorView().setBackgroundColor(
				Color.parseColor("#1d3c58"));
		/* === END set background ==== */

		ScrollView scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.TRANSPARENT);
		RelativeLayout.LayoutParams scrollLayoutParams = new RelativeLayout.LayoutParams(
				(int) ((width - 40) * nativeToPxRatio),
				(int) ((height - 20) * nativeToPxRatio));

		scroll.setX(20 * nativeToPxRatio);
		scroll.setY(150 * nativeToPxRatio);

		/* ========= Set up table ============ */
		TableLayout tableLayout = new TableLayout(this);

		TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.WRAP_CONTENT,
				TableLayout.LayoutParams.WRAP_CONTENT);
		tableLayout.setLayoutParams(tableParams);

		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT);
		TableRow.LayoutParams rowParamsDescription = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT);

		rowParams.setMargins((int) (50 * nativeToPxRatio),
				(int) (50 * nativeToPxRatio), (int) (50 * nativeToPxRatio),
				(int) (50 * nativeToPxRatio));

		/* ========= Set up title row in table ========= */
		TableRow ccTitle = new TableRow(this);
		TextView courseTitle = new TextView(this);
		courseTitle.setTypeface(Typeface.DEFAULT_BOLD);
		courseTitle.setTextColor(Color.BLACK);
		courseTitle.setTextSize(28);
		courseTitle.setText("Course Confessions");
		courseTitle.setPadding(0, (int) (20 * nativeToPxRatio), 0, 0);
		courseTitle.setLayoutParams(rowParams);
		/* ========= End set up title row in table ========= */

		/* ========= Set up description row in table ========= */
		TableRow ccDescription = new TableRow(this);
		TextView courseDescription = new TextView(this);
		courseDescription.setTypeface(Typeface.DEFAULT_BOLD);
		courseDescription.setTextColor(Color.BLACK);
		courseDescription.setTextSize(13);
		courseDescription.setPadding((int) (70 * nativeToPxRatio), 0,
				(int) (70 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		courseDescription
				.setText("CourseConfessions is the largest online "
						+ "destination for professor and Computer Science course "
						+ "ratings from actual UCSD students. Users have contributed "
						+ "to more than 6 course ratings and over 2 professor ratings "
						+ "from UCSD. User-generated content makes CourseConfessions "
						+ "the highest trafficked app for quickly researching and "
						+ "rating professors and courses located across the University "
						+ "of California, San Diego. More than 7 college students each "
						+ "month are using CourseConfessions - join the fun and begin "
						+ "rating professors and courses today!\n\n\n"
						+ "CourseConfessions is built for college students, "
						+ "by college students. Choosing the best courses and "
						+ "professors is a rite of passage for every student, "
						+ "and connecting with peers on the site has become a "
						+ "key way for millions of students to navigate this "
						+ "process. The site does what students have been doing "
						+ "forever - checking in with each other: their friends, "
						+ "their brothers, their sisters, their classmates - to "
						+ "figure out who's a great professor and who's one you "
						+ "might want to avoid. ");
		courseDescription.setLayoutParams(rowParamsDescription);
		/* ========= End set up description row in table ========= */

		/* ========= Set up gradient for each row ========= */
		GradientDrawable courseTitleBackground = new GradientDrawable();
		courseTitleBackground.setColor(Color.WHITE);
		courseTitleBackground.setCornerRadii(new float[] {
				10 * nativeToPxRatio, 10 * nativeToPxRatio,
				10 * nativeToPxRatio, 10 * nativeToPxRatio, 0, 0, 0, 0 });
		GradientDrawable courseDescriptionBackground = new GradientDrawable();
		courseDescriptionBackground.setColor(Color.WHITE);
		courseDescriptionBackground.setCornerRadii(new float[] { 0, 0, 0, 0,
				10 * nativeToPxRatio, 10 * nativeToPxRatio,
				10 * nativeToPxRatio, 10 * nativeToPxRatio });
		/* ========= End set up gradient for each row ========= */

		/* ========= Draw background and addViews ========= */
		ccTitle.setBackgroundDrawable(courseTitleBackground);
		ccTitle.addView(courseTitle);
		ccDescription.setBackgroundDrawable(courseDescriptionBackground);
		ccDescription.addView(courseDescription);
		/* ========= End draw background and addViews ========= */

		/* ========= Add the table and scroll layout ========= */
		tableLayout.addView(ccTitle);
		tableLayout.addView(ccDescription);
		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);
		/* ========= End add the table and scroll layout ========= */

	}

	@Override
	/**
	 * Override the SideMenuScene home function so the current 
	 * page is not reloaded.
	 */
	public void home() {
		/** Do nothing, on purpose */
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
