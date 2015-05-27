package activities.courseSelect;

import java.util.ArrayList;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import user.User;
import util.SDCardWriter;
import util.phpInteractions;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
//this is the course select screen, where you select courses.
public class CourseSelectScreen extends BaseScene {
	@SuppressWarnings("deprecation")
	@Override
	//ran when the screen is created, aka after startScreen(); most of it is explained inside the method.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== TODO set background ===== */
				//copy and paste code from HomeScreen.java here and change it a little maybe
		
		/* === END how to set background ==== */

		/* ==== how to make the view scrollable === */

		// there are 3 objects here.

		ScrollView scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.TRANSPARENT);
		LayoutParams scrollLayoutParams = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

		TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.WRAP_CONTENT,
				TableLayout.LayoutParams.WRAP_CONTENT);
		TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT);

		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setLayoutParams(tableParams);

		// first row
		TableRow tableRow1 = new TableRow(this);// create a new row
		tableRow1.setLayoutParams(tableParams); // set the params
		TextView textView = new TextView(this);// add txt
		textView.setText("this is a test \n\n\nn\n\n\n\n\n\n\n\n\n\\n\\\n\n\n\n\n\n\n\n\n\nhhhhh");
		textView.setLayoutParams(rowParams); // add txt
		textView.setTextColor(Color.BLACK); // add txt
		tableRow1.addView(textView); // add txt
		CheckBox cb = new CheckBox(this); // add a check box
		cb.setBackgroundColor(Color.BLACK);// add a check box
		tableRow1.addView(cb); // add a check box
		tableLayout.addView(tableRow1); // add txt

		// second row
		TableRow tableRow2 = new TableRow(this);
		tableRow2.setLayoutParams(tableParams);
		TextView textView2 = new TextView(this);
		textView2
				.setText("this is another test \n\n\nn\n\n\n\n\n\n\n\n\n\\sadfdn\\\n\n\n\n\n\n\n\n\n\njjjjj");
		textView2.setTextColor(Color.BLACK);
		textView2.setLayoutParams(rowParams);
		tableRow2.addView(textView2);
		CheckBox cb2 = new CheckBox(this); // add a check box
		cb2.setBackgroundColor(Color.BLACK);// add a check box
		tableRow2.addView(cb2); // add a check box
		tableLayout.addView(tableRow2);

		scroll.addView(tableLayout);
		addContentView(scroll, scrollLayoutParams);
		/* == END how to make the view scrollable == */

		// example code reading the ser file
		String read = SDCardWriter.readFile(getFilesDir().toString()
				+ User.SAVE_FILE);
		Toast.makeText(this, "Read file, result is:\n" + read,
				Toast.LENGTH_LONG).show();
		// end example code reading the user file
		/* ====== reloading the User object ======== */
		User user = new User(this);
		String userNamePass = "username: " + user.getUserName() + "\n"
				+ "password: " + user.getPassword();
		Toast.makeText(this, "Recreated user:\n" + userNamePass,
				Toast.LENGTH_LONG).show();
		/* ===== end reloading the user object ==== */

	}
}