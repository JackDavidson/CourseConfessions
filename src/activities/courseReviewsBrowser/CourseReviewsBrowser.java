package activities.courseReviewsBrowser;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.bitsplease.courseconfessions.R;

import user.User;
import util.XMLStringObject;
import util.phpInteractions;
import activities.SideMenuScene;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/* contact Leovao/Orrozco for questions on this file */
public class CourseReviewsBrowser extends SideMenuScene {
	public static String XML_COURSES_NAME = "course";

	@Override
	// ran when the screen is created
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== TODO set background ===== */
		// copy and paste code from HomeScreen.java here and change it a
		// little
		// maybe
		getWindow().getDecorView().setBackgroundColor(
				Color.parseColor("#1d3c58"));
		/* === END set background ==== */

		/* ====== reloading the User object ======== */
		User user = new User(this);
		/* ===== end reloading the user object ==== */

		/* ==== how to make the view scrollable === */
		// there are 3 objects here.

		/*
		 * ===== Create the scroll view, set up the table layout params =====
		 */

		ScrollView scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.TRANSPARENT);
		RelativeLayout.LayoutParams scrollLayoutParams = new RelativeLayout.LayoutParams(
				(int) ((width - 60) * nativeToPxRatio),
				(int) ((height - HEIGHT_OF_TITLE_PIC) * nativeToPxRatio));
		/*
		 * scrollLayoutParams.setMargins((int) (50 * nativeToPxRatio), (int) (50
		 * * nativeToPxRatio), (int) (50 * nativeToPxRatio), (int) (50 *
		 * nativeToPxRatio));
		 */

		scroll.setX(30 * nativeToPxRatio);
		scroll.setY(HEIGHT_OF_TITLE_PIC * nativeToPxRatio);

		/* ========= Set up table ============ */
		TableLayout tableLayout = new TableLayout(this);

		TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.WRAP_CONTENT,
				TableLayout.LayoutParams.WRAP_CONTENT);
		tableLayout.setLayoutParams(tableParams);

		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
				(int) ((width - 175) * nativeToPxRatio),
				TableRow.LayoutParams.WRAP_CONTENT);

		rowParams.setMargins((int) (50 * nativeToPxRatio), 0,
				(int) (50 * nativeToPxRatio), 0);
		/* ====== End Set up table ========== */

		/* ====== Add reviews to the table ====== */
		XMLStringObject pageData = null;
		if (user.getScreen() == Screen.CourseReviewsBrowser)
			pageData = user.getPageData();
		else
			finish();

		ArrayList<XMLStringObject> coursesToLoad = pageData.getChildren();
		for (XMLStringObject course : coursesToLoad) {
			String[] courseData = course.getValue().split(" ");
			ArrayList<String> reviews = phpInteractions.getReviewsOfCourses(
					courseData[0], courseData[1]);
			Log.e("ReviewBrowser", reviews.toString());

			if (!reviews.get(0).equals("fail")) {
				addReviewsToTable(tableLayout, reviews, tableParams, rowParams);
			} else {
				TableRow tableRowFailure = new TableRow(this);// create a new
																// row
				// tableRowFailure.setLayoutParams(rowParams);
				TextView failureTextView = new TextView(this);// add txt
				failureTextView.setTypeface(null, Typeface.BOLD);
				failureTextView.setTextColor(Color.BLACK); // add txt
				failureTextView.setText("\nThere are no reviews for: "
						+ course.getValue() + "\n");
				failureTextView.setLayoutParams(rowParams); // add txt
				GradientDrawable failureBackground = new GradientDrawable();
				failureBackground.setColor(Color.WHITE);
				failureBackground.setCornerRadius(8);

				tableRowFailure.setBackgroundDrawable(failureBackground);
				tableRowFailure.addView(failureTextView); // add txt
				tableLayout.addView(tableRowFailure); // add txt
			}

		}

		/* ==== End Add reviews to the table ==== */

		/*** ==== Add list of reviews ==== ***/

		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);

		/*** ==== END add list of reviews ==== ***/

	}

	static final int REVIEW_ID_POSITION = 5;

	private void addReviewsToTable(TableLayout tableLayout,
			ArrayList<String> reviews,
			android.widget.TableLayout.LayoutParams tableParams,
			android.widget.TableRow.LayoutParams rowParams) {

		// create return arraylist of integers
		for (int i = 0; i < reviews.size(); i++) {
			// for each time the ID is returned, add to the return array
			if ((i + 1) % 6 == 0) {
				// reviewIDs.add(Integer.parseInt(reviews.get(i)));
			}
			// otherwise output to screen with proper formatting
			else {
				TableRow tableRowName = new TableRow(this);// create a new row
				tableRowName.setLayoutParams(tableParams); // set the params
				TextView textViewName = new TextView(this);// add txt

				GradientDrawable nameBackground = new GradientDrawable();
				nameBackground.setColor(Color.WHITE);
				GradientDrawable valueBackground = new GradientDrawable();
				valueBackground.setColor(Color.WHITE);
				// shape.setCornerRadius( 8 );

				String name = null;
				switch (i % 6) {
				case 0:
					name = "\nSection";

					nameBackground.setCornerRadii(new float[] {
							10 * nativeToPxRatio, 10 * nativeToPxRatio,
							10 * nativeToPxRatio, 10 * nativeToPxRatio, 0, 0,
							0, 0 });
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
				tableRowName.setBackgroundDrawable(nameBackground);
				tableRowName.addView(textViewName); // add txt

				/* === Value pair === */
				TableRow tableRowValue = new TableRow(this);// create a new row
				tableRowValue.setBackgroundDrawable(valueBackground);
				tableRowValue.setLayoutParams(tableParams); // set the params
				TextView textViewValue = new TextView(this);// add txt
				String value = reviews.get(i);
				textViewValue.setText("\t\t\t" + value + "\n");
				textViewValue.setLayoutParams(rowParams); // add txt
				textViewValue.setTextColor(Color.BLACK); // add txt
				tableRowValue.addView(textViewValue);
				/* === END Value pair === */

				tableLayout.addView(tableRowName); // add txt
				tableLayout.addView(tableRowValue); // add txt

				/* ==== add a space between each review ==== */
				if (i % 6 == 4) {
					int reviewId = Integer.parseInt(reviews.get(i - (i + 1) % 6
							+ 6));
					addButtonsToTable(tableLayout, reviewId);

					TableRow tableRowSpace = new TableRow(this);// create a new
																// row
					tableRowSpace.setLayoutParams(tableParams); // set the
																// params
					TextView textViewSpace = new TextView(this);// add txt
					textViewSpace.setText("\n\n");
					textViewSpace.setLayoutParams(rowParams); // add txt
					textViewSpace.setTextColor(Color.BLACK); // add txt
					tableRowSpace.addView(textViewSpace);
					tableLayout.addView(tableRowSpace);
				}
				/* ==== END add a space between each review ==== */
			}
		}
		// return array of IDs
	}

	private void addButtonsToTable(TableLayout tableLayout, final int reviewId) {
		GradientDrawable lastBackground = new GradientDrawable();
		lastBackground.setColor(Color.WHITE);

		TableRow buttonsRow = new TableRow(this);// create a new
		lastBackground.setCornerRadii(new float[] { 0, 0, 0, 0,
				10 * nativeToPxRatio, 10 * nativeToPxRatio,
				10 * nativeToPxRatio, 10 * nativeToPxRatio });
		/* ========= How to add a button ======================= */
		RelativeLayout images = new RelativeLayout(this);
		RelativeLayout.LayoutParams imagesParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		images.setLayoutParams(imagesParams);

		Button flagBtn = new Button(this);

		flagBtn.setBackgroundResource(R.raw.flagposts);
		flagBtn.setX(30 * nativeToPxRatio);
		// flagBtn.setY(40 * nativeToPxRatio);
		// flagBtn.setText("report");

		TableRow.LayoutParams flagLP = new TableRow.LayoutParams(
				// TableLayout.LayoutParams.WRAP_CONTENT,
				// TableLayout.LayoutParams.WRAP_CONTENT);
				(int) (50 * nativeToPxRatio), (int) (50 * nativeToPxRatio),
				0.0f);
		flagBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					// toggleSideMenu();

					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
								nameValuePairs.add(new BasicNameValuePair(
										"report", "hihi"));
								nameValuePairs.add(new BasicNameValuePair(
										"reviewid", String.valueOf(reviewId)));
								phpInteractions.httpPost(nameValuePairs,
										"http://www.courseconfessions.com/androidgetcoursereviews.php");
								
								AlertDialog.Builder builder = new AlertDialog.Builder(
										getContext());
								builder.setMessage("Refiew flagged! ID:" + reviewId).setNegativeButton("OK", this).show();
								
								break;

							case DialogInterface.BUTTON_NEGATIVE:
								// No button clicked
								break;
							}
						}
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(
							getContext());
					builder.setMessage("Flag Review? ID:" + reviewId)
							.setPositiveButton("Yes", dialogClickListener)
							.setNegativeButton("No", dialogClickListener)
							.show();
					return true;
				}

				return false;
			}
		});
		flagBtn.setLayoutParams(flagLP);
		images.addView(flagBtn);
		images.setBackgroundDrawable(lastBackground);
		tableLayout.addView(images);
		// addContentView(flagBtn, menuLP);
		/* ========= End How to add a button =================== */
	}
}
