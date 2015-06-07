package com.bitsplease.courseconfessions.test;

import activities.main.ForgotScreen;
import activities.main.MainMenu;
import android.test.*;
import android.widget.EditText;
import android.widget.TextView;

public class MainMenuTest extends ActivityInstrumentationTestCase2<MainMenu>
{
	private MainMenu mainMenuTester;
	private ForgotScreen forgotScreenTester;
	private TextView courseTitle;
	private TextView courseDescription;
	
	public MainMenuTest() {
		super(MainMenu.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		//for the main menu
		mainMenuTester = getActivity();
		courseTitle = (TextView) mainMenuTester.getCourseTitle();
		courseDescription = (TextView) mainMenuTester.getCourseDescription();
	}
	
	public void testPreconditions() {
		assertNotNull("Main Menu is null",mainMenuTester);
		assertNotNull("TextView is null",courseTitle);
		assertNotNull("TextView is null",courseDescription);
	}
	public void testMainMenu() {
		assertEquals("Text in course title is wrong","Course Confessions",courseTitle.getText().toString());
		assertEquals("CourseConfessions is the largest online "
				+ "destination for UCSD course "
				+ "ratings from actual UCSD students. Users have contributed "
				+ "to more than 10 course ratings "
				+ "from UCSD. User-generated content makes CourseConfessions "
				+ "a high traffic app for quickly researching and "
				+ "rating courses located across the University "
				+ "of California, San Diego. More than 7 college students each "
				+ "month are using CourseConfessions - join the fun and begin "
				+ "rating professors and courses today!\n\n\n"
				+ "CourseConfessions is built for college students, "
				+ "by college students. Choosing the best courses "
				+ "is a necessity for every student to graduate, "
				+ "and connecting with peers on the site has become a "
				+ "key way for millions of students to navigate this "
				+ "process. The site does what students have been doing "
				+ "forever - checking in with each other: their friends, "
				+ "their brothers, their sisters, their classmates - to "
				+ "figure out what's a great class and which ones you "
				+ "might want to wait for another quarter for. ",courseDescription.getText().toString());
	}
}
