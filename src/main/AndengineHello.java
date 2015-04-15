package main;


import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.Log;
import android.view.Display;

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
 * @author jack - jack.davidson38@gmail.com
 *
 */
public class AndengineHello extends SimpleBaseGameActivity implements
		IOnSceneTouchListener {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final int width = 1280;
	private static int height;
	// ===========================================================
	// Fields
	// ===========================================================
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private Scene mScene;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	/**
	 * this is for setting up the camera (just setting up a area thats going to
	 * be displayed) you need to set up the height and width. below is a setup
	 * that detects the height and width of your screen, and adjusts the camera
	 * to use the entire height. The result is a screen that has a width of 1280
	 * and a height of whatever is necessary to use the entire size of the
	 * screen.
	 */
	@Override
	public EngineOptions onCreateEngineOptions() {
		Display display = getWindowManager().getDefaultDisplay();
		// get the width and height of the screen in pixels for our ratio
		// calculations
		int tempWidth = display.getWidth(); // deprecated
		height = display.getHeight(); // deprecated
		// calculate what the height needs to be. width stays 1280.
		float heightWidthRatio = (float) height / (float) tempWidth;
		height = (int) (width * heightWidthRatio);
		// declare the camera, we want something thats 1280 by whatever.
		final Camera camera = new Camera(0, 0, width, height);
		// EngineOptions are used by Andengine. Shouldn't need to play around
		// with this too much
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						width, height), camera);
		// if we need sounds:
		// engineOptions.getAudioOptions().setNeedsMusic(true);
		return engineOptions;
	}

	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(
				this.getTextureManager(), 64, 128, TextureOptions.BILINEAR);
		this.mBitmapTextureAtlas.load();
	}

	/**
	 * entry point!!!!!!!! this is where most of your code will be. basically
	 * everything that goes on screen (thats not declared in xml instead)
	 * belongs on here
	 */
	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		this.mScene = new Scene();
		this.mScene.setBackground(new Background(0f, 1f, 0f));
		this.mScene.setOnSceneTouchListener(this);
		mScene.setTouchAreaBindingOnActionDownEnabled(true);
		return this.mScene;
	}

	@Override
	public boolean onSceneTouchEvent(final Scene pScene,
			final TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.isActionDown()) {
			Log.i("AndengineHello", "x:" + pSceneTouchEvent.getX() + " y:"
					+ pSceneTouchEvent.getY());
			return true;
		}
		return false;
	}
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