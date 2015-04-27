package activities.courseSelect;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;

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
	}
}