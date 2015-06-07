package com.bitsplease.courseconfessions.test;

import activities.main.LoginScreen;
import activities.main.MainMenu;
import activities.main.SignupScreen;
import android.content.Intent;
import android.test.*;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupScreenTest extends ActivityInstrumentationTestCase2<SignupScreen>
{
	private SignupScreen SignupScreenTester;
	private EditText userText;
	private EditText emailText;
	private EditText passText;
	private EditText confirmText;
	private Button login;
	private Button forgot;
	private Button signup;
	
	public SignupScreenTest() {
		super(SignupScreen.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		//for the main menu
		SignupScreenTester = getActivity();		
		userText = (EditText) SignupScreenTester.getUserText().getEditText();
		passText = (EditText) SignupScreenTester.getPassText().getEditText();
		emailText = (EditText) SignupScreenTester.getEmailText().getEditText();
		confirmText = (EditText) SignupScreenTester.getConfirmText().getEditText();
		login = (Button) SignupScreenTester.getLoginButton();
		forgot = (Button) SignupScreenTester.getForgotButton();
		signup = (Button) SignupScreenTester.getSignupButton();
	}
	
	public void testPreconditions() {
		assertNotNull("Forgot Screen is null",SignupScreenTester);
		assertNotNull("EditText is null",userText);
		assertNotNull("EditText is null",emailText);
		assertNotNull("EditText is null",passText);
		assertNotNull("EditText is null",confirmText);
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
		assertTrue(passText.requestFocus());
		assertTrue(passText.hasFocus());
		assertTrue(confirmText.requestFocus());
		assertTrue(confirmText.hasFocus());
	}
	@UiThreadTest
	public void testEditText() {
		userText.requestFocus();
		userText.setText("testing");
		emailText.requestFocus();
		emailText.setText("hello@hello.com");
		passText.requestFocus();
		passText.setText("password");
		confirmText.requestFocus();
		confirmText.setText("password");
		assertEquals("the text in the username field is incorrect.", "testing", userText.getText().toString());
		assertEquals("the text in the email field is incorrect.", "hello@hello.com",emailText.getText().toString());
		assertEquals("the text in the password field is incorrect.", "password", passText.getText().toString());
		assertEquals("the text in the confirm field is incorrect.", "password",confirmText.getText().toString());
	}
}
