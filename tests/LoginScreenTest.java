package com.bitsplease.courseconfessions.test;

import activities.main.LoginScreen;
import activities.main.MainMenu;
import android.content.Intent;
import android.test.*;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreenTest extends ActivityInstrumentationTestCase2<LoginScreen>
{
	private LoginScreen LoginScreenTester;
	private EditText userText;
	private EditText passText;
	private Button login;
	private Button forgot;
	private Button signup;
	
	public LoginScreenTest() {
		super(LoginScreen.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		//for the main menu
		LoginScreenTester = getActivity();		
		userText = (EditText) LoginScreenTester.getUserText().getEditText();
		passText = (EditText) LoginScreenTester.getPassText().getEditText();
		login = (Button) LoginScreenTester.getLoginButton();
		forgot = (Button) LoginScreenTester.getForgotButton();
		signup = (Button) LoginScreenTester.getSignupButton();
	}
	
	public void testPreconditions() {
		assertNotNull("Forgot Screen is null",LoginScreenTester);
		assertNotNull("EditText is null",userText);
		assertNotNull("EditText is null",passText);
		assertNotNull("Login button is null",login);
		assertNotNull("forgot button is null",forgot);
		assertNotNull("signup button is null",signup);
	}
	@UiThreadTest
	public void testRequestingFocus()
	{
		assertTrue(userText.requestFocus());
		assertTrue(userText.hasFocus());
		assertTrue(passText.requestFocus());
		assertTrue(passText.hasFocus());
	}
	@UiThreadTest
	public void testEditText() {
		userText.requestFocus();
		userText.setText("testing");
		passText.requestFocus();
		passText.setText("testing123");
		assertEquals("the text in the username field is incorrect.", "testing", userText.getText().toString());
		assertEquals("the text in the pass field is incorrect.", "testing123",passText.getText().toString());
	}
}
