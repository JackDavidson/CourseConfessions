package activities.main;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* contact Jack for questions on this file. look up andengine examples on github for examples of how to do stuff */
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
public class HomeScreen extends BaseScene {
	// ===========================================================
	// Constants
	// ===========================================================
	// private static final int height = 1280;
	// private static int width;

	// ===========================================================
	// Fields
	// ===========================================================
	private EditText usernameEditText;
	private EditText passwordEditText;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== how to set background ===== */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		int id = R.raw.background;
		ImageView imageView = new ImageView(this);
		LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		imageView.setAdjustViewBounds(true);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		imageView.setImageResource(id);
		imageView.setLayoutParams(vp);
		linearLayout.addView(imageView);
		addContentView(linearLayout, vp);
		/* ==== END how to set background ===== */

		/* ========= How to do text entry ====================== */
		/** Username placeholder initializer */
		usernameEditText = new EditText(this);
		usernameEditText.setTextColor(Color.rgb(12, 26, 38));
		usernameEditText.setHint("Username");
		
		/** Password placeholder initializer */
		passwordEditText = new EditText(this);
		passwordEditText.setHint("Password");
		passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		passwordEditText.setTextColor(Color.rgb(12, 26, 38));
		
		/********
		 * notice!!!!! we may need to change to honeycomb (api 11/android3.0)for
		 * this!!! TODO
		 *****/

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int)(500*nativeToPxRatio), (int)(70*nativeToPxRatio));
		RelativeLayout.LayoutParams login = new RelativeLayout.LayoutParams(
				(int) (500*nativeToPxRatio), (int) (100*nativeToPxRatio));
		
		usernameEditText.setX((widthPx/2) - (lp.width/2)+34*nativeToPxRatio);
		usernameEditText.setY((heightPx * 1 / 2)-104*nativeToPxRatio);
		
		passwordEditText.setX((widthPx/2) -(lp.width/2)+34*nativeToPxRatio);
		passwordEditText.setY((heightPx * 1 / 2)+30*nativeToPxRatio);

		InputFilter filter = new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				for (int i = start; i < end; i++)
					if (!Character.isLetter(source.charAt(i)))
						return "";

				return null;
			}
		};

		usernameEditText.setFilters(new InputFilter[] { filter });

		this.addContentView(usernameEditText, lp);
		this.addContentView(passwordEditText, lp);
		/* ========= End How to do text entry ================== */

		/* ========= How to add a button ======================= */
		Button bt = new Button(this);
		bt.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholderlogin));
		bt.setX(widthPx / 2 - lp.width / 2);
		bt.setY((height - 200) * nativeToPxRatio);
		bt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT));
		bt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					attemptLogin();
					return true;
				}

				return false;
			}
		});
		addContentView(bt, login);
		/* ========= End How to add a button =================== */

	}

	protected void attemptLogin() {
		// TODO Auto-generated method stub
		String userName = usernameEditText.getText().toString();
		Log.i("HomeScreen Attempt login username:", userName);
		startCourseSelectScreen();
	}

	private void startCourseSelectScreen() {
		Intent courseSelectScreen = new Intent(this,
				activities.courseSelect.CourseSelectScreen.class);
		this.startActivity(courseSelectScreen);
	}
}