package activities.courseSelect;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import activities.BaseScene;

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
public class CourseSelectScreen extends BaseScene {
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

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * this is where loading happens. really, we can load wherever we like
	 * though, so ignore this function.
	 */
	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(
				this.getTextureManager(), 64, 128, TextureOptions.BILINEAR);
		this.mBitmapTextureAtlas.load();
	}

	/**
	 * entry point!!!!!!!! this is where most of your code will be. basically
	 * everything that goes on screen belongs on here, or in helper functions
	 * that this function uses
	 * 
	 * @author: Jack - jack.davidson38@gmail.com
	 */
	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		this.mScene = new Scene();
		this.mScene.setBackground(new Background(0f, 0f, 1f));
		/* TODO: contact server through phpInteractions for list of courses */
		/* TODO: add the ability to scroll through the list, and check boxes in the list */
		
		return this.mScene;
	}
}