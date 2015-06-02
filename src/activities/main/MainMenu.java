package activities.main;

import visuals.PlacementImage;

import com.bitsplease.courseconfessions.R;

import activities.SideMenuScene;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainMenu extends SideMenuScene {
	
	@Override
	/** 
	 * Main Menu, tell the user what Course Confessions is all about.
	 * */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/** TODO FIX THIS */
		/* ==== Set placeholder home title ===== */		
		ImageView image = new ImageView(this);
		LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
				(int) (220*nativeToPxRatio), (int) (68*nativeToPxRatio));
		image.setBackgroundResource(R.raw.slidemenuhomescreen);
		image.setX((width / 2)*nativeToPxRatio - (220 / 2)*nativeToPxRatio);
		image.setY(35*nativeToPxRatio);
		addContentView(image, linearLayout);
		/* ==== END set placeholder home title ===== */
		
		/* Layout Params of the current text */
		LayoutParams courseConfessionsParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		/* Text View for the Course Confessions title */
		TextView courseConfessions = new TextView(this);
		courseConfessions.setText("Course Confessions");
		courseConfessions.setTextColor(Color.BLACK);
		courseConfessions.setTextSize(28);
		courseConfessions.setX(35*nativeToPxRatio);
		courseConfessions.setY(170*nativeToPxRatio);
		
		/* Text View for the Course Confessions Description */
		TextView CCDescription = new TextView(this);
		CCDescription.setPadding((int) (45*nativeToPxRatio), (int) (20*nativeToPxRatio), 
				(int) (35*nativeToPxRatio), (int) (20*nativeToPxRatio));
		CCDescription.setText("This is the description of Course Confessions. " +
				"Basically we are a rate my professor clone with less cool features" +
				" because we just want a grade for this stupid project. "); // TODO ADD Description
		CCDescription.setTextColor(Color.BLACK);
		CCDescription.setTextSize(16);
		CCDescription.setY(260*nativeToPxRatio);
		
		addContentView(courseConfessions, courseConfessionsParams);
		addContentView(CCDescription, courseConfessionsParams);
		
	}
	
	@Override
	/**
	 * Override the SideMenuScene home function so the current 
	 * page is not reloaded.
	 */
	public void home() {
		/** Do nothing, on purpose */
	}
	
}
