package com.bitsplease.courseconfessions.test;

import activities.BaseScene.Screen;
import activities.main.ForgotScreen;
import activities.main.MainMenu;
import android.content.Intent;
import android.test.*;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotScreenTest extends ActivityInstrumentationTestCase2<ForgotScreen>
{
	private ForgotScreen forgotScreenTester;
	private EditText userText;
	private EditText emailText;
	private Button login;
	private Button forgot;
	private Button signup;
	
	public ForgotScreenTest() {
		super(ForgotScreen.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		//for the main menu
		forgotScreenTester = getActivity();		
		userText = (EditText) forgotScreenTester.getUserText().getEditText();
		emailText = (EditText) forgotScreenTester.getEmailText().getEditText();
		login = (Button) forgotScreenTester.getLoginButton();
		forgot = (Button) forgotScreenTester.getForgotButton();
		signup = (Button) forgotScreenTester.getSignupButton();
	}
	
	public void testPreconditions() {
		assertNotNull("Forgot Screen is null",forgotScreenTester);
		assertNotNull("EditText is null",userText);
		assertNotNull("EditText is null",emailText);
		assertNotNull("Login button is null",login);
		assertNotNull("forgot button is null",forgot);
		assertNotNull("signup button is null",signup);
	}
	@UiThreadTest
	public void testRequestingFocus()
	{
		assertTrue(userText.requestFocus());
		assertTrue(userText.hasFocus());
		assertTrue(emailText.requestFocus());
		assertTrue(emailText.hasFocus());
	}
	@UiThreadTest
	public void testEditText() {
		userText.requestFocus();
		userText.setText("testing");
		emailText.requestFocus();
		emailText.setText("testing123");
		assertEquals("the text in the username field is incorrect.", "testing", userText.getText().toString());
		assertEquals("the text in the email field is incorrect.", "testing123",emailText.getText().toString());
	}
}
