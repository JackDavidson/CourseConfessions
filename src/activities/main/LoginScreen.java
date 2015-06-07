package activities.main;

import user.User;
import util.LoginException;
import util.phpInteractions;
import visuals.PlacementEditText;
import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * This is the main file, the entry point into the program. the REAL entry point
 * is onCreateScene and in what you define there.
 * 
 * implements IOnSceneTouchListener is for touch interface directly through the
 * 
 * @author Jack - jack.davidson38@gmail.com
 * @author Byrdor - byrdor@gmail.com
 *  
 */
public class LoginScreen extends BaseScene {

	// ===========================================================
	// Fields
	// ===========================================================
	private PlacementEditText placeUserText;
	private PlacementEditText placePassText;
	private Button forgotButton;
	private Button loginBtn;
	private Button signupButton;
	private boolean log;
	private boolean sign;
	private boolean forgot;
	// ===========================================================
	@SuppressLint("ClickableViewAccessibility")
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@SuppressWarnings("deprecation")
	@Override
	// set background display text and other stuff, mostly explained in method
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ===== Create a new user ===== */
		User user = new User(this);
		if (user.ready()) {
			if (user.getScreen() != Screen.LoginScreen) {
				startScreen(user.getScreen());
				finish();
				return;
			}
		}
		/* ===== End Create a new user ===== */

		/* ==== Set background ===== */
		PlacementImage image = new PlacementImage(this, R.raw.background,
				(int) (widthPx / (2 * nativeToPxRatio) - 1000 / 2), 0, 1000,
				1280);
		image.attachToScene();
		/* ==== END set background ===== */

		/* ========= Text Entry ========= */
		placeUserText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 - 104, 500, 70, "Username");
		placePassText = new PlacementEditText(this, width / 2 - 500 / 2 + 34,
				height / 2 + 30, 500, 70, "Password");
		/* ========= END Text Entry ========= */

		/* ========= Set Layout Params for screen ========= */
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		/* ========= END Layout Params ========= */

		
		/*** Notice: we may need to change to honeycomb ***/
		placeUserText.attachToScene();
		placePassText.attachToScene();

		
		/* ========= Login button ========= */
		loginBtn = new Button(this);
		loginBtn.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholderlogin));
		loginBtn.setX(widthPx / 2 - lp.width / 2);
		loginBtn.setY((height - 350) * nativeToPxRatio);

		RelativeLayout.LayoutParams login = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (100 * nativeToPxRatio));
		loginBtn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					log = true;
					attemptLogin();
					return true;
				}
				log = false;
				return false;
			}
		});
		addContentView(loginBtn, login);
		/* ========= End Login Button ========= */

		/* ========= Signup Button ========= */
		signupButton = new Button(this);
		signupButton.setBackgroundResource(R.raw.signupbtn);
		signupButton.setX((widthPx / 2) - 170 * nativeToPxRatio);
		signupButton.setY((height - 70) * nativeToPxRatio);

		RelativeLayout.LayoutParams signupBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (28 * nativeToPxRatio));
		signupButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					sign = true;
					startScreen(Screen.SignupScreen);
					finish();
					return true;
				}
				sign = false;
				return false;
			}
		});
		addContentView(signupButton, signupBtnLayout);
		/* ========= End Signup Button ========= */

		/* ========= Forgot Button ========= */
		forgotButton = new Button(this);
		forgotButton.setBackgroundResource(R.raw.forgotbtn);
		forgotButton.setX((widthPx / 2) + 30 * nativeToPxRatio);
		forgotButton.setY((height - 70) * nativeToPxRatio);

		RelativeLayout.LayoutParams forgotBtnLayout = new RelativeLayout.LayoutParams(
				(int) (150 * nativeToPxRatio), (int) (32 * nativeToPxRatio));
		forgotButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					forgot = true;
					startScreen(Screen.ForgotScreen);
					finish();
					return true;
				}
				forgot = false;
				return false;
			}
		});
		addContentView(forgotButton, forgotBtnLayout);
		/* ========= End Forgot Button ========= */
	}

	@Override
	/**
	 *  call baseScene.onResume(); 
	 *  */
	protected void onResume() {
		super.onResume();
	}

	/* Attempt login when user click on the login screen */
	private void attemptLogin() {
		boolean loginSuccess = false;
		User user = null;
		String userName = placeUserText.getEditText().getText().toString();
		String userPass = placePassText.getEditText().getText().toString();

		try {
			user = phpInteractions.attemptLoginAndCreateUser(userName,
					userPass, this);
			loginSuccess = true;
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		if (loginSuccess) {
			/* Make some toast, but butter it this time */
			Toast.makeText(this, "Welcome " + user.getRealName(),
					Toast.LENGTH_LONG).show();
			user.setScreen(Screen.MainMenuScreen);
			user.save(this);
			this.startScreen(Screen.MainMenuScreen);
			finish();
		}
	}
	/* ===== End Attempt Login ===== */
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	/*USED FOR TESTING*/
	public PlacementEditText getUserText()
	{
		return placeUserText;
	}
	public PlacementEditText getPassText()
	{
		return placePassText;
	}
	public Button getSignupButton()
	{
		return signupButton;
	}
	public Button getForgotButton()
	{
		return forgotButton;
	}
	public Button getLoginButton()
	{
		return loginBtn;
	}
	public boolean getForgot() { return forgot; }
	public boolean getLogin() { return log; } 
	public boolean getSignup() { return sign; }
	
}