package activities.writeReview;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.bitsplease.courseconfessions.R;

import user.User;
import util.phpInteractions;
import visuals.PlacementEditText;
import activities.SideMenuScene;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

/* contact TODO for questions on this file */
/**
 * This is the main file, the entry point into the program. the REAL entry point
 * is onCreateScene and in what you define there.
 * 
 * Andengine provides a "SimpleBaseGameActivity from which your game activity
 * (in this case AndengineHello) should always be extended
 * 
 * implements IOnSceneTouchListener is for touch interface directly through the
 * screen, not through the buttons. if all interface goes through your buttons,
 * that shouldn't be necessary.
 * 
 * @author Jack - jack.davidson38@gmail.com
 * 
 */
// this will be the class that holds the write review screen.
public class WriteReviewScreen extends SideMenuScene {

	Spinner courseNamesSpinner;
	Spinner sectionNumSpinner;
	private PlacementEditText placeReviewText; // the textbox where we're
												// putting the review in

	// TODO: cap the textbox at 1000 characters?

	@Override
	// when the method is created this is ran. Most of the implementation
	// is explained in the method itself.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== Set placeholder write review title ===== */
		ImageView image = new ImageView(this);
		LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
				(int) (220 * nativeToPxRatio), (int) (68 * nativeToPxRatio));
		image.setBackgroundResource(R.raw.slidemenuwritereview);
		image.setX((width / 2) * nativeToPxRatio - (220 / 2) * nativeToPxRatio);
		image.setY(35 * nativeToPxRatio);
		addContentView(image, linearLayout);
		/* ==== END set placeholder write review title ===== */

		/* ===== Text Entry ===== */
		int reviewTextWidth = 800 - 80;
		int reviewTextX = width / 2 - reviewTextWidth / 2;
		int reviewTextHeight = (int) (height * .6);
		placeReviewText = new PlacementEditText(this, reviewTextX, 270,
				reviewTextWidth, reviewTextHeight, "Review");
		placeReviewText.attachToScene();
		/* === End Text Entry === */

		/* ===== Create two drop down menus ======= */
		LinearLayout.LayoutParams spinnerParams = new LinearLayout.LayoutParams(
				(int) (300 * nativeToPxRatio), (int) (80 * nativeToPxRatio));

		/* course num */
		String courseNames[] = { "CSE", "ECE" };
		String CSEcourseNumbers[] = { "21", "100", "110", "120", "140", "140L" };
		String ECEcourseNumbers[] = { "30", "35" };

		ArrayAdapter<String> spinnerArrayAdapterNames = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, courseNames);
		spinnerArrayAdapterNames
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		final ArrayAdapter<String> spinnerCSEAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, CSEcourseNumbers);
		spinnerCSEAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		final ArrayAdapter<String> spinnerECEAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, ECEcourseNumbers);
		spinnerECEAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Selection of the spinner
		courseNamesSpinner = new Spinner(this);
		courseNamesSpinner.setX(50 * nativeToPxRatio);
		courseNamesSpinner.setY(175 * nativeToPxRatio);

		// Application of the Array to the Spinner
		courseNamesSpinner.setAdapter(spinnerArrayAdapterNames);
		courseNamesSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						// Object item = parentView.getItemAtPosition(position);

						// Depend on first spinner value set adapter to 2nd
						// spinner
						if (position == 0) {
							sectionNumSpinner.setAdapter(spinnerCSEAdapter);
						} else {
							sectionNumSpinner.setAdapter(spinnerECEAdapter);
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {// do
																		// nothing
					}

				});
		/* end course num */

		/* course num */

		// Selection of the spinner
		sectionNumSpinner = new Spinner(this);
		sectionNumSpinner.setX((width - 350) * nativeToPxRatio);
		sectionNumSpinner.setY(175 * nativeToPxRatio);

		// Application of the Array to the Spinner

		sectionNumSpinner.setAdapter(spinnerCSEAdapter);
		/* end course num */

		addContentView(sectionNumSpinner, spinnerParams);
		addContentView(courseNamesSpinner, spinnerParams);
		/* === End Create a drop down menu ===== */

		/* ========= Submit button ========= */

		RelativeLayout.LayoutParams login = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (100 * nativeToPxRatio));
		Button submitBtn = new Button(this);
		submitBtn.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholderwritereview));
		submitBtn.setX(widthPx / 2 - login.width / 2);
		submitBtn.setY((height - 175) * nativeToPxRatio);

		submitBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					String result = attemptSubmit();
					Toast.makeText(getContext(), result, Toast.LENGTH_LONG)
							.show();
					if (!result.equals("fail")) {
						finish();
						startScreen(Screen.MainMenuScreen);
					}
					return true;
				}

				return false;
			}
		});
		addContentView(submitBtn, login);
		/* ========= End Submit Button ========= */

	}

	private String attemptSubmit() {

		/* ====== reloading the User object ======== */
		User user = new User(this);
		/* ===== end reloading the user object ==== */

		String author = user.getUserName();
		String review = placeReviewText.getEditText().getText().toString();
		String dept = courseNamesSpinner.getSelectedItem().toString();
		String courseNum = sectionNumSpinner.getSelectedItem().toString();

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("author", author));
		nameValuePairs.add(new BasicNameValuePair("review", review));
		nameValuePairs.add(new BasicNameValuePair("dept", dept));
		nameValuePairs.add(new BasicNameValuePair("courseNum", courseNum));

		String result = phpInteractions
				.trimJSONString(phpInteractions.parseStringJSON(
						phpInteractions
								.convertRespToString(phpInteractions
										.httpPost(nameValuePairs,
												"http://www.courseconfessions.com/androidreviewsubmit.php")),
						"REVIEW_STATUS"));

		return result;
	}

	@Override
	/**
	 * Override the SideMenuScene writeReview function so the current 
	 * page is not reloaded.
	 */
	public void writeReview() {
		/** Do nothing, on purpose */
	}

}