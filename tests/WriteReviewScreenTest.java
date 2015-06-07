package com.bitsplease.courseconfessions.test;

import activities.writeReview.WriteReviewScreen;
import activities.main.MainMenu;
import android.content.Intent;
import android.test.*;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WriteReviewScreenTest extends ActivityInstrumentationTestCase2<WriteReviewScreen>
{
	private WriteReviewScreen WriteReviewScreenTester;
	private EditText reviewText;
	private Button submit;
	
	public WriteReviewScreenTest() {
		super(WriteReviewScreen.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		//for the main menu
		WriteReviewScreenTester = getActivity();		
		reviewText = (EditText) WriteReviewScreenTester.getReviewText().getEditText();
		submit = (Button) WriteReviewScreenTester.getSubmitButton();
	}
	
	public void testPreconditions() {
		assertNotNull("Write Review Screen is null",WriteReviewScreenTester);
		assertNotNull("EditText is null",reviewText);
		assertNotNull("Submit button is null",submit);
	}
	@UiThreadTest
	public void testRequestingFocus()
	{
		assertTrue(reviewText.requestFocus());
		assertTrue(reviewText.hasFocus());
	}
	@UiThreadTest
	public void testEditText() {
		reviewText.requestFocus();
		reviewText.setText("testing writing a review");
		assertEquals("the text in the username field is incorrect.", 
				"testing writing a review", reviewText.getText().toString());
	}
}
