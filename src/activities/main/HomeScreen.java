package activities.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.SDCardWriter;
import visuals.PlacementEditText;
import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class HomeScreen extends BaseScene {
	// ===========================================================
	// Constants
	// ===========================================================
	// private static final int height = 1280;
	// private static int width;

	// ===========================================================
	// Fields
	// ===========================================================
	private PlacementEditText placeUserText;
	private PlacementEditText placePassText;
	private TextView loginResultTextView;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* ==== settings ==== */
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* ==== END settings ==== */

		/* ==== Set background ===== */
		PlacementImage image = new PlacementImage(this,
				(int)(widthPx / (2*nativeToPxRatio)- 1000/2),0,1000,1280);
		//PlacementImage image = new PlacementImage(this,(widthPx/2)- (1000/ 2) +
			//	(int)((1000/2)*nativeToPxRatio),0,1000, 1280);
		//text.setX((context.widthPx / 2) -  x* context.nativeToPxRatio);
		//LinearLayout linearLayout = new LinearLayout(this);
		//linearLayout.setOrientation(LinearLayout.VERTICAL);
		//Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.background);
		//DTImageView imageView = new DTImageView(this);
		//imageView.imageBitmap = Bitmap.createScaledBitmap(bitmap,(int)(1000*nativeToPxRatio),(int)(1280*nativeToPxRatio),true);
		//imageView.setX(widthPx/2 - (1000/2)*nativeToPxRatio);
		//LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(
				//LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//linearLayout.setClipChildren(false);
		//linearLayout.addView(imageView);
		//addContentView(linearLayout, vp);
		image.attachToScene();
		/* ==== END set background ===== */

		/* ====== how to display text ====== */
		loginResultTextView = new TextView(this);
		loginResultTextView.setX(50 * nativeToPxRatio);
		loginResultTextView.setY((height / 2 + 100) * nativeToPxRatio);
		RelativeLayout.LayoutParams loginResultParams = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		loginResultTextView.setTextColor(Color.WHITE);
		loginResultTextView.setText("Ready");
		addContentView(loginResultTextView, loginResultParams);
		/* ==== END how to display text ==== */

		/* ========= How to do text entry ====================== */
		placeUserText = new PlacementEditText(this,34,-104,500,70,"Username");
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (70 * nativeToPxRatio));
		placePassText = new PlacementEditText(this,34,30,500,70,"Password");
		

		/********
		 * notice!!!!! we may need to change to honeycomb (api 11/android3.0)for
		 * this!!! TODO
		 *****/
		placeUserText.attachToScene();
		placePassText.attachToScene();
		/* ========= End How to do text entry ================== */

		/* ========= How to add a button ======================= */
		Button bt = new Button(this);
		bt.setBackgroundDrawable(getResources().getDrawable(
				R.raw.placeholderlogin));
		bt.setX(widthPx / 2 - lp.width / 2);
		bt.setY((height - 200) * nativeToPxRatio);

		RelativeLayout.LayoutParams login = new RelativeLayout.LayoutParams(
				(int) (500 * nativeToPxRatio), (int) (100 * nativeToPxRatio));
		bt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					attemptLogin();
					return true;
				}

				return false;
			}
		});
		addContentView(bt, login);
		/* ========= End How to add a button =================== */
		
		
		/* ======== Quick how to write/read files ============== */
		SDCardWriter.writeFile(getFilesDir().toString(),"placeholderName", "placeholderComment");
		String read = SDCardWriter.readFile(getFilesDir().toString() + "placeholderName");
		Toast.makeText(this, "Read file, result is: " + read, Toast.LENGTH_LONG).show();
		/* ========== END how to write/read files ============== */

	}

	protected void attemptLogin() {
		String stringResultUserName = null; // where the username will be put,
											// if we're successful.
		// TODO Auto-generated method stub
		String userName = placeUserText.getEditText().getText().toString();
		String userPass = placePassText.getEditText().getText().toString();
		Log.i("HomeScreen Attempt login username:", userName);
		Log.i("HomeScreen Attempt login pass:", userPass);

		String result = "";
		// the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", userName));
		nameValuePairs.add(new BasicNameValuePair("password", userPass));
		InputStream is = null;

		// http post
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://www.courseconfessions.com/androidlogin.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			result = sb.toString();
			Log.e("result", result);
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		// parse json data
		try {
			JSONArray jArray = new JSONArray(result);
			Log.e("log_tag", "made it here");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				Log.i("", "made it to second");
				Log.i("", json_data.getString("USER"));
				stringResultUserName = json_data.getString("USER");
				// Log.i("log_tag", "Length: " + json_data.length() + " "
				// + "USER: " + json_data.getString("USER"));
			}
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		// startCourseSelectScreen();
		
		
		if(stringResultUserName != null){
			if(stringResultUserName.equals("fail")){
				loginResultTextView.setText("Incorrect login username or password!");
			} else {
				loginResultTextView.setText("Success! Logged in as: " + stringResultUserName);
			}
		} else {
			loginResultTextView.setText("Failed to log in! connection error");
		}
	}

	private void startCourseSelectScreen() {
		Intent courseSelectScreen = new Intent(this,
				activities.courseSelect.CourseSelectScreen.class);
		this.startActivity(courseSelectScreen);
	}
	
}