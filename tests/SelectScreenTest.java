package com.bitsplease.courseconfessions.test;

import activities.main.LoginScreen;
import activities.main.MainMenu;
import activities.courseSelect.CourseSelectScreen;
import android.content.Intent;
import android.test.*;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SelectScreenTest extends ActivityInstrumentationTestCase2<CourseSelectScreen>
{
	private CourseSelectScreen SelectScreenTester;
	private Button cont;
	
	public SelectScreenTest() {
		super(CourseSelectScreen.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		//for the main menu
		SelectScreenTester = getActivity();		
		cont = (Button) SelectScreenTester.getContButton();
	}
	
	public void testPreconditions() {
		assertNotNull("Select Screen is null",SelectScreenTester);
		assertNotNull("continue button is null",cont);
	}
}
