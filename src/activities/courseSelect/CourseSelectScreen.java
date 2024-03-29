package activities.courseSelect;

import java.util.ArrayList;

import com.bitsplease.courseconfessions.R;

import user.User;
import util.XMLStringObject;
import util.phpInteractions;
import visuals.PlacementImage;
import activities.BaseScene;
import activities.SideMenuScene;
import activities.BaseScene.Screen;
import activities.courseReviewsBrowser.CourseReviewsBrowser;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CourseSelectScreen extends SideMenuScene {
	private Button searchBtn; 
	@SuppressWarnings("deprecation")
	@Override
	// ran when the screen is created, aka after startScreen(); most of it is
	// explained inside the method
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set placeholder courses title ===== */
		ImageView image = new ImageView(this);
		LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		image.setBackgroundResource(R.raw.slidemenucourses);
		image.setX((width / 2) * nativeToPxRatio - (220 / 2) * nativeToPxRatio);
		image.setY(35 * nativeToPxRatio);
		addContentView(image, linearLayout);
		/* ==== END set placeholder courses title ===== */

		/* ====== reloading the User object ======== */
		final User user = new User(this);
		/* ===== end reloading the user object ==== */

		/* ==== Populate array of courses === */
		final ArrayList<String> courses = phpInteractions.getListOfCourses(
				"any", 0, 10);
		if(courses.size() == 0 || (courses.size() == 1 && courses.get(0).equals(""))){
			Log.e("Course reviews browser", "Could not connect! finishing.");
			Toast.makeText(this, "Failed to connect to server!", Toast.LENGTH_LONG).show();
			user.setScreen(Screen.MainMenuScreen);
			user.save(this);
			startScreen(Screen.MainMenuScreen);
			finish();
		}
		/* ==== End Populate array of courses === */
		
		final CheckBox[] checkBoxes = new CheckBox[courses.size()];
		Log.e("Select screen", courses.toString());

		/* ===== Create the scroll view, set up the table layout params ===== */
		ScrollView scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.TRANSPARENT);
		LayoutParams scrollLayoutParams = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

		TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.WRAP_CONTENT,
				TableLayout.LayoutParams.WRAP_CONTENT);
		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT);
		/* ==== END Create the scroll view, set up layout params ==== */

		/* ==== Add list of reviews ==== */
		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setLayoutParams(tableParams);
		tableLayout.setY(160 * nativeToPxRatio);

		for (int i = 0; i < courses.size(); i++) {
			TableRow tableRow = new TableRow(this);
			tableRow.setLayoutParams(tableParams);
			final TextView textView = new TextView(this);
			textView.setText(courses.get(i).replaceAll("[^a-zA-Z0-9\\s]", ""));
			textView.setLayoutParams(rowParams);
			textView.setTextColor(Color.BLACK);
			textView.setX(35 * nativeToPxRatio);
			textView.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						XMLStringObject xmlPageSpecifficData = new XMLStringObject(
								User.XML_SCREEN_DATA_NAME, "");
						xmlPageSpecifficData.addItem(
								CourseReviewsBrowser.XML_COURSES_NAME,
								(String) textView.getText());
						user.addXmlPageData(xmlPageSpecifficData);
						user.setScreen(Screen.CourseReviewsBrowser);
						user.save(getContext());
						startScreen(Screen.CourseReviewsBrowser);
						return true;
					}

					return true;
				}
			});
			tableRow.addView(textView);
			checkBoxes[i] = new CheckBox(this);
			checkBoxes[i].setX((width - 200) * nativeToPxRatio);
			tableRow.addView(checkBoxes[i]);
			tableLayout.addView(tableRow);
		}

		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);
		/* ==== END add list of reviews ==== */

		/* ========= Add continue button ============ */
		searchBtn = new Button(this);
		searchBtn.setBackgroundResource(R.raw.placeholdersearch);
		searchBtn.setX((widthPx / 2) - (416 / 2) * nativeToPxRatio);
		searchBtn.setY((height - 200) * nativeToPxRatio);

		RelativeLayout.LayoutParams searchBtnLayout = new RelativeLayout.LayoutParams(
				(int) (416 * nativeToPxRatio), (int) (126 * nativeToPxRatio));
		searchBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					XMLStringObject xmlPageSpecifficData = new XMLStringObject(
							User.XML_SCREEN_DATA_NAME, "");
					for (int i = 0; i < courses.size(); i++)
						if (checkBoxes[i].isChecked())
							xmlPageSpecifficData.addItem(
									CourseReviewsBrowser.XML_COURSES_NAME,
									courses.get(i));
					user.addXmlPageData(xmlPageSpecifficData);
					user.setScreen(Screen.CourseReviewsBrowser);
					user.save(getContext());
					startScreen(Screen.CourseReviewsBrowser);
					//finish();
					return true;
				}

				return true;
			}
		});
		addContentView(searchBtn, searchBtnLayout);
		/* ======== End add continue BTN ======= */

	}

	@Override
	/**
	 * Override the SideMenuScene courses function so the current 
	 * page is not reloaded.
	 */
	public void courses() {
		/** Do Nothing, on purpose */
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			User user = new User(this);
			user.setScreen(Screen.MainMenuScreen);
			user.save(this);
			startScreen(Screen.MainMenuScreen);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == Screen.CourseReviewsBrowser.ordinal()) {
			if (resultCode == CourseReviewsBrowser.FINISH_PARENT) {
				finish(); // we switched to another activity. time to be done
						  // with this one.
			}
		}
	}
	public Button getContButton()
	{
		return searchBtn;
	}
}