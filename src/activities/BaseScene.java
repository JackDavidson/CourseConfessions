package activities;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.view.Display;

public abstract class BaseScene extends SimpleBaseGameActivity{
	// ===========================================================
	protected static final int height = 1280;
	protected static int width;
	
	protected static int screenHeightPx;
	protected static int screenWidthPx;
	// ===========================================================
	// Fields
	// ===========================================================
	protected BitmapTextureAtlas mBitmapTextureAtlas;
	protected Scene mScene;
	
	
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
	screenWidthPx = display.getWidth();
	screenHeightPx = display.getHeight();
	// calculate what the height needs to be. width stays 1280.
	float widthHeightRatio = (float) screenWidthPx / (float) screenHeightPx;
	width = (int) (height * widthHeightRatio);
	// declare the camera, we want something thats 1280 by whatever.
	final Camera camera = new Camera(0, 0, width, height);
	// EngineOptions are used by Andengine. Shouldn't need to play around
	// with this too much
	EngineOptions engineOptions = new EngineOptions(true,
	ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(
	width, height), camera);
	// TODO: if we need sounds:
	// engineOptions.getAudioOptions().setNeedsMusic(true);
	return engineOptions;
	}
	
	
	
}
