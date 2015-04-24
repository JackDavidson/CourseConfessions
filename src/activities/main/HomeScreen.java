package activities.main;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

import activities.BaseScene;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
public class HomeScreen extends BaseScene implements IOnSceneTouchListener {
	// ===========================================================
	// Constants
	// ===========================================================
	// private static final int height = 1280;
	// private static int width;

	// ===========================================================
	// Fields
	// ===========================================================
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private Scene mScene;
	private EditText usernameEditText;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onSetContentView() {
		super.onSetContentView();
		/* ========= How to do text entry ====================== */
		usernameEditText = new EditText(this);
		usernameEditText.setTextColor(Color.WHITE);

		/********
		 * notice!!!!! we may need to change to honeycomb (api 11/android3.0)for
		 * this!!! TODO
		 *****/
		usernameEditText.setX(screenWidthPx * 1 / 4);
		usernameEditText.setY(screenHeightPx * 1 / 4);

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				screenWidthPx * 2 / 4, 100);

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
		/* ========= End How to do text entry ================== */
	}

	/**
	 * this is where loading happens. really, we can load wherever we like
	 * though, so ignore this function.
	 */
	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(
				this.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
		this.mBitmapTextureAtlas.load();
	}

	/**
	 * entry point!!!!!!!! this is where most of your code will be. basically
	 * everything that goes on screen belongs on here, or in helper functions
	 * that this uses
	 * 
	 * @author: Jack - jack.davidson38@gmail.com
	 */
	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		this.mScene = new Scene();
		this.mScene.setBackground(new Background(0f, 1f, 0f));
		this.mScene.setOnSceneTouchListener(this);
		// mScene.setTouchAreaBindingOnActionDownEnabled(true);

		/* ========= How To Create a image on scene ============ */
		// using area 0x0 to 640x1136
		final ITextureRegion backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this,
						"background.png", 0, 0);

		int centerScreenX = width / 2;
		int topOfBackground = 0; // since height = 1280 always, the top of
									// background always belongs at 0
		int leftOfBackground = centerScreenX - (640 / 2); // 640 is the width of
															// the background
		final Sprite background = new Sprite(leftOfBackground, topOfBackground,
				backgroundTextureRegion, this.getVertexBufferObjectManager());
		mScene.attachChild(background);
		/* ========= End How To Create a image on scene ========= */

		/* ========= How To Make an image a button ============== */
		// using area 640x0 to 1140x90
		final ITextureRegion loginTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this,
						"placeholderLogin.png", 640, 0);
		final Sprite touchLogoSprite = new Sprite(width / 2 - 500 / 2,
				height * 13 / 16, loginTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/* this is an example of how to open another scene */
				if (pSceneTouchEvent.isActionUp()) {
					attemptLogin();
				}
				return true;
			}
		};
		mScene.registerTouchArea(touchLogoSprite);
		mScene.attachChild(touchLogoSprite);
		/* ========= End How To Make that image a button ======== */

		// this.mBitmapTextureAtlas.load();

		return this.mScene;
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

	/**
	 * this is just an example to show you how to handle screen touches. The way
	 * it works is that screen touches are first caught by buttons, then if you
	 * return "false" (i think its false) they are passed along to the lower
	 * buttons or the screen. So, the below function is used for scrolling, but
	 * not much else.
	 * 
	 * @author: Jack - jack.davidson38@gmail.com
	 */
	@Override
	public boolean onSceneTouchEvent(final Scene pScene,
			final TouchEvent pSceneTouchEvent) {
		/* TODO: actually do something.... */
		if (pSceneTouchEvent.isActionDown()) {
			Log.i("HomeScreen", "x:" + pSceneTouchEvent.getX() + " y:"
					+ pSceneTouchEvent.getY());
			return true;
		}
		return false;
	}

	/**
	 * the below is to make a note of functions we will be implementing much
	 * later
	 */
	/*
	 * @Override public void onResumeGame() { super.onResumeGame();
	 * 
	 * }
	 * 
	 * @Override public void onPauseGame() { super.onPauseGame(); }
	 * 
	 * @Override public void onWindowFocusChanged(boolean pHasWindowFocus) {
	 * super.onWindowFocusChanged(pHasWindowFocus); }
	 * 
	 * @Override public void onDestroy() { super.onDestroy(); }
	 */
}