package activities.courseReviewsBrowser;

import java.util.ArrayList;

import user.User;
import util.phpInteractions;
import activities.BaseScene;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/* contact Leovao/Orrozco for questions on this file */
public class CourseReviewsBrowser extends BaseScene {
	public static String XML_COURSES_NAME = "course";
	
	@Override
	// ran when the screen is created
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== TODO set background ===== */
		// copy and paste code from HomeScreen.java here and change it a
		// little
		// maybe

		/* === END set background ==== */

		/* ====== reloading the User object ======== */
		User user = new User(this);
		/* ===== end reloading the user object ==== */

		/* ==== how to make the view scrollable === */
		// there are 3 objects here.

		ArrayList<String> reviews = phpInteractions.getReviewsOfCourses("CSE",
				"110");
		Log.e("ReviewBrowser", reviews.toString());
		/*
		 * ===== Create the scroll view, set up the table layout params =====
		 */
		ScrollView scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.TRANSPARENT);
		LayoutParams scrollLayoutParams = new LayoutParams(
				(int) ((width - 150) * nativeToPxRatio),
				(int) ((height - 150) * nativeToPxRatio));

		scroll.setX(75 * nativeToPxRatio);
		scroll.setY(75 * nativeToPxRatio);

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

		for (int i = 0; i < reviews.size(); i++) {
			TableRow tableRowName = new TableRow(this);// create a new row
			tableRowName.setLayoutParams(tableParams); // set the params
			TextView textViewName = new TextView(this);// add txt

			String name = null;
			switch (i % 5) {
			case 0:
				name = "Section";
				break;
			case 1:
				name = "Course Number";
				break;
			case 2:
				name = "Title";
				break;
			case 3:
				name = "Review";
				break;
			case 4:
				name = "Rating";
				break;
			}
			textViewName.setText(name + ":");
			textViewName.setTypeface(null, Typeface.BOLD);
			textViewName.setLayoutParams(rowParams); // add txt
			textViewName.setTextColor(Color.BLACK); // add txt
			tableRowName.addView(textViewName); // add txt

			/* === Value pair === */
			TableRow tableRow = new TableRow(this);// create a new row
			tableRow.setLayoutParams(tableParams); // set the params
			TextView textView = new TextView(this);// add txt
			String value = reviews.get(i);
			textView.setText("\t\t\t" + value + "\n");
			textView.setLayoutParams(rowParams); // add txt
			textView.setTextColor(Color.BLACK); // add txt
			tableRow.addView(textView);
			/* === END Value pair === */

			tableLayout.addView(tableRowName); // add txt
			tableLayout.addView(tableRow); // add txt

			/* ==== add a space between each review ==== */
			if (i % 5 == 4) {
				TableRow tableRowSpace = new TableRow(this);// create a new row
				tableRowSpace.setLayoutParams(tableParams); // set the params
				TextView textViewSpace = new TextView(this);// add txt
				textViewSpace.setText("\n\n");
				textViewSpace.setLayoutParams(rowParams); // add txt
				textViewSpace.setTextColor(Color.BLACK); // add txt
				tableRowSpace.addView(textViewSpace);
				tableLayout.addView(tableRowSpace);
			}
			/* ==== END add a space between each review ==== */
		}

		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);

		/*** ==== END add list of reviews ==== ***/

	}
}
