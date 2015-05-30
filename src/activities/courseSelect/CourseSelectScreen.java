package activities.courseSelect;

import java.util.ArrayList;

import user.User;
import util.XMLStringObject;
import util.phpInteractions;
import activities.BaseScene;
import activities.courseReviewsBrowser.CourseReviewsBrowser;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CourseSelectScreen extends BaseScene {

	@SuppressWarnings("deprecation")
	@Override
	// ran when the screen is created, aka after startScreen(); most of it is
	// explained inside the method.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== TODO set background ===== */
		// copy and paste code from HomeScreen.java here and change it a little
		// maybe
		/* === END set background ==== */

		/* ====== reloading the User object ======== */
		final User user = new User(this);
		/* ===== end reloading the user object ==== */

		/* ==== how to make the view scrollable === */
		// there are 3 objects here.
		final ArrayList<String> courses = phpInteractions.getListOfCourses(
				"any", 0, 10);
		final CheckBox[] checkBoxes = new CheckBox[courses.size()];
		Log.e("Select screen", courses.toString());
		// [{"COURSES":["CSE 100","CSE 110","CSE 140","ECE 30"]}]

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

		/*** ==== Add list of reviews ==== ***/

		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setLayoutParams(tableParams);

		for (int i = 0; i < courses.size(); i++) {
			TableRow tableRow = new TableRow(this);// create a new row
			tableRow.setLayoutParams(tableParams); // set the params
			TextView textView = new TextView(this);// add txt
			textView.setText("Course " + (i + 1) + ": "
					+ courses.get(i).replaceAll("[^a-zA-Z0-9\\s]", "")
					+ "\t\t\t");
			textView.setLayoutParams(rowParams); // add txt
			textView.setTextColor(Color.BLACK); // add txt
			tableRow.addView(textView); // add txt

			checkBoxes[i] = new CheckBox(this); // add a check box
			checkBoxes[i].setBackgroundColor(Color.DKGRAY);// add a check box
			tableRow.addView(checkBoxes[i]); // add a check box
			tableLayout.addView(tableRow); // add txt
		}

		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);

		/*** ==== END add list of reviews ==== ***/

		/* ========= Add continue button ============ */
		Button viewCoursesBt = new Button(this);
		viewCoursesBt.setBackgroundColor(Color.parseColor("#1A3754"));
		viewCoursesBt.setX((width / 2 - 200) * nativeToPxRatio);
		viewCoursesBt.setY((height - 150) * nativeToPxRatio);

		RelativeLayout.LayoutParams offscreenBtnLP = new RelativeLayout.LayoutParams(
				(int) (400 * nativeToPxRatio), (int) (1200 * nativeToPxRatio));
		viewCoursesBt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					XMLStringObject xmlPageSpecifficData = new XMLStringObject(
							BaseScene.XML_SCREEN_DATA_NAME, "");
					for (int i = 0; i < courses.size(); i++)
						if (checkBoxes[i].isChecked())
							xmlPageSpecifficData.addItem(
									CourseReviewsBrowser.XML_COURSES_NAME,
									courses.get(i));
					user.addXmlPageData(xmlPageSpecifficData);
					user.writeUserToFile(getContext());
					startScreen(Screen.CourseReviewsBrowser);
					return true;
				}

				return true;
			}
		});
		addContentView(viewCoursesBt, offscreenBtnLP); // add the button to the
														// side
														// menu
		/* ======== End add continue BTN ======= */

	}

}