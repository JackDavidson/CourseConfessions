package activities.courseSelect;

import java.util.ArrayList;

import com.bitsplease.courseconfessions.R;

import user.User;
import util.XMLStringObject;
import util.phpInteractions;
import visuals.PlacementImage;
import activities.BaseScene;
import activities.SideMenuScene;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CourseSelectScreen extends SideMenuScene {

	@SuppressWarnings("deprecation")
	@Override
	// ran when the screen is created, aka after startScreen(); most of it is
	// explained inside the method.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* ==== Set placeholder courses title ===== */		
		ImageView image = new ImageView(this);
		LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
				(int) (220*nativeToPxRatio), (int) (68*nativeToPxRatio));
		image.setBackgroundResource(R.raw.slidemenucourses);
		image.setX((width / 2)*nativeToPxRatio - (220 / 2)*nativeToPxRatio);
		image.setY(35*nativeToPxRatio);
		addContentView(image, linearLayout);
		/* ==== END set placeholder courses title ===== */
		
		/* ====== reloading the User object ======== */
		final User user = new User(this);
		/* ===== end reloading the user object ==== */

		/* ==== how to make the view scrollable === */
		final ArrayList<String> courses = phpInteractions.getListOfCourses(
				"any", 0, 10);
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

		/*** ==== Add list of reviews ==== ***/
		
		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setLayoutParams(tableParams);
		tableLayout.setY(160*nativeToPxRatio);

		for (int i = 0; i < courses.size(); i++) {
			TableRow tableRow = new TableRow(this);
			tableRow.setLayoutParams(tableParams); 
			TextView textView = new TextView(this);
			textView.setText(courses.get(i).replaceAll("[^a-zA-Z0-9\\s]", ""));
			textView.setLayoutParams(rowParams);
			textView.setTextColor(Color.BLACK);
			textView.setX(35*nativeToPxRatio);
			tableRow.addView(textView);
			checkBoxes[i] = new CheckBox(this);
			checkBoxes[i].setX((width - 200) * nativeToPxRatio);
			tableRow.addView(checkBoxes[i]);
			tableLayout.addView(tableRow);
		}

		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);

		/*** ==== END add list of reviews ==== ***/

		/* ========= Add continue button ============ */
		Button viewCoursesBt = new Button(this);
		viewCoursesBt.setBackgroundResource(R.raw.placeholdersearch);
		viewCoursesBt.setX((widthPx / 2) - (416/2)*nativeToPxRatio);
		viewCoursesBt.setY((height - 220) * nativeToPxRatio);

		RelativeLayout.LayoutParams searchBtnLayout = new RelativeLayout.LayoutParams(
				(int) (416 * nativeToPxRatio), (int) (126 * nativeToPxRatio));
		viewCoursesBt.setOnTouchListener(new OnTouchListener() {
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
					user.writeUserToFile(getContext());
					startScreen(Screen.CourseReviewsBrowser);
					return true;
				}

				return true;
			}
		});
		addContentView(viewCoursesBt, searchBtnLayout);
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
	
}