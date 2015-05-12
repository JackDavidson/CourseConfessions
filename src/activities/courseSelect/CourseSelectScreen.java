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
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
		/* === END how to set background ==== */
		
		
		/* ==== how to make the view scrollable === */
		
		// there are 3 objects here. 
		
		ScrollView scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.TRANSPARENT);
		LayoutParams scrollLayoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
		                                             LayoutParams.FILL_PARENT);
		
		
		TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setLayoutParams(tableParams);

		// first row
		TableRow tableRow1 = new TableRow(this);// create a new row
		tableRow1.setLayoutParams(tableParams); // set the params
		TextView textView = new TextView(this);// add txt
		textView.setText("this is a test \n\n\nn\n\n\n\n\n\n\n\n\n\\n\\\n\n\n\n\n\n\n\n\n\nhhhhh");
		textView.setLayoutParams(rowParams);   // add txt
		textView.setTextColor(Color.WHITE);    // add txt
		tableRow1.addView(textView);           // add txt
		CheckBox cb = new CheckBox(this);  // add a check box
		cb.setBackgroundColor(Color.WHITE);// add a check box
		tableRow1.addView(cb);             // add a check box
		tableLayout.addView(tableRow1);        // add txt
		
		
		// second row
		TableRow tableRow2 = new TableRow(this);
		tableRow2.setLayoutParams(tableParams);
		TextView textView2 = new TextView(this);
		textView2.setText("this is another test \n\n\nn\n\n\n\n\n\n\n\n\n\\sadfdn\\\n\n\n\n\n\n\n\n\n\njjjjj");
		textView2.setTextColor(Color.WHITE);
		textView2.setLayoutParams(rowParams);
		tableRow2.addView(textView2);
		CheckBox cb2 = new CheckBox(this);  // add a check box
		cb2.setBackgroundColor(Color.WHITE);// add a check box
		tableRow2.addView(cb2);             // add a check box
		tableLayout.addView(tableRow2);
		
		
		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);
		/* == END how to make the view scrollable == */
		
	}
}