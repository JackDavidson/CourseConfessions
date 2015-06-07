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
		assertEquals("Text in course description is wrong","CourseConfessions is the largest online "
				+ "destination for professor and Computer Science course "
				+ "ratings from actual UCSD students. Users have contributed "
				+ "to more than 6 course ratings and over 2 professor ratings "
				+ "from UCSD. User-generated content makes CourseConfessions "
				+ "the highest trafficked app for quickly researching and "
				+ "rating professors and courses located across the University "
				+ "of California, San Diego. More than 7 college students each "
				+ "month are using CourseConfessions - join the fun and begin "
				+ "rating professors and courses today!\n\n\n"
				+ "CourseConfessions is built for college students, "
				+ "by college students. Choosing the best courses and "
				+ "professors is a rite of passage for every student, "
				+ "and connecting with peers on the site has become a "
				+ "key way for millions of students to navigate this "
				+ "process. The site does what students have been doing "
				+ "forever - checking in with each other: their friends, "
				+ "their brothers, their sisters, their classmates - to "
				+ "figure out who's a great professor and who's one you "
				+ "might want to avoid. ",courseDescription.getText().toString());
	}
	public void testForgotScreen()
	{
		
	}
}
