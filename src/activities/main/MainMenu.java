package activities.main;

import activities.SideMenuScene;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class MainMenu extends SideMenuScene {
	
	@Override
	/** 
	 * Main Menu, tell the user what Course Confessions is all about.
	 * */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Layout Params of the current text */
		LayoutParams courseConfessionsParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		/* Text View for the Course Confessions title */
		TextView courseConfessions = new TextView(this);
		courseConfessions.setText("Course Confessions");
		courseConfessions.setTextColor(Color.BLACK);
		courseConfessions.setTextSize(28);
		courseConfessions.setX(35*nativeToPxRatio);
		courseConfessions.setY(160*nativeToPxRatio);
		
		/* Text View for the Course Confessions Description */
		TextView CCDescription = new TextView(this);
		CCDescription.setText("This is the description of Course Confessions. " +
				"Basically we are a rate my professor clone with less cool features" +
				" because we just want a grade for this stupid project");
		CCDescription.setTextColor(Color.BLACK);
		CCDescription.setTextSize(16);
		CCDescription.setX(35*nativeToPxRatio);
		CCDescription.setY(300*nativeToPxRatio);
		
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
