package activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class BaseScene extends Activity {

	/* native size = 1280x800, or 1280x??? */
	public float nativeToPxRatio;
	public int widthPx;
	public int heightPx;
	protected int width;
	protected static final int height = 1280;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = getWindowManager().getDefaultDisplay();
		// get the width and height of the screen in pixels for our ratio
		// calculations
		widthPx = display.getWidth();
		heightPx = display.getHeight();
		// calculate what the height needs to be. width stays 1280.
		float widthHeightRatio = (float) widthPx / (float) heightPx;
		width = (int) (height * widthHeightRatio);
		nativeToPxRatio = (float)(float)heightPx/height;
		Log.e("ratio", "ratio: " + nativeToPxRatio);
	}
}
