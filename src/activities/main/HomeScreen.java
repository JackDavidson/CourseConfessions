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
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;import android.view.ViewGroup;
import android.widget.EditText;

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
public class HomeScreen extends BaseScene implements
		IOnSceneTouchListener, TextWatcher {
	// ===========================================================
	// Constants
	// ===========================================================
	//private static final int height = 1280;
	//private static int width;

	// ===========================================================
	// Fields
	// ===========================================================
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private Scene mScene;
	private EditText editTextExample;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onSetContentView() {
		super.onSetContentView();
		/* ========= How to do text entry ====================== */
		editTextExample = new EditText(this);
		editTextExample.addTextChangedListener(this);
		this.addContentView(editTextExample, new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
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
				this.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
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
		final ITextureRegion logoTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this,
						"badge_large.png", 0, 0);
		final Sprite logoSprite = new Sprite(50, 50, logoTextureRegion,
				this.getVertexBufferObjectManager());
		mScene.attachChild(logoSprite);
		/* ========= End How To Create a image on scene ========= */

		/* ========= How To Make an image a button ============== */
		final Sprite touchLogoSprite = new Sprite(50, 500, logoTextureRegion,
				this.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/* this is an example of how to open another scene */
				if (pSceneTouchEvent.isActionUp())
					startCourseSelectScreen();
				return true;
			}
		};
		mScene.registerTouchArea(touchLogoSprite);
		mScene.attachChild(touchLogoSprite);
		/* ========= End How To Make that image a button ======== */

		// this.mBitmapTextureAtlas.load();

		return this.mScene;
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

	@Override
	public void afterTextChanged(final Editable pEditable) {
		// TODO stuff
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

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