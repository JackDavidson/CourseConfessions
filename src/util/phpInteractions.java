package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import user.User;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public abstract class phpInteractions {

	/* contact Eric/Jack/Nolan for questions on this file */

	/**
	 * TODO: comments, explaining method
	 * 
	 * @param username
	 *            TODO
	 * @param password
	 *            TODO
	 * @return TODO
	 */
	public static User attemptLoginAndCreateUser(String userName,
			String userPass, Context context) throws LoginException {
		/*
		 * TODO: contact server, return success or failure. handle exceptions as
		 * appropriate
		 */
		// if we're successful.
		// TODO Auto-generated method stub
		String stringResultUserName = null; // where the username will be put,

		Log.i("HomeScreen Attempt login username:", userName);
		Log.i("HomeScreen Attempt login pass:", userPass);

		// the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", userName));
		nameValuePairs.add(new BasicNameValuePair("password", userPass));

		stringResultUserName = parseLoginJSON(convertRespToString(httpPost(
				nameValuePairs,
				"http://www.courseconfessions.com/androidlogin.php")));

		if (stringResultUserName == null) {
			throw new LoginException("Failed to connect to server");
		} else if (stringResultUserName.equals("fail")) {
			throw new LoginException("Incorrect Username or Password");
		} else {
			return new User(userName, userPass, stringResultUserName, context);
		}

	}

	/** TODO: still working on this part */
	public static boolean attemptForgotPassword(String userName,
			String userEmail, Context context) throws LoginException {
		/*
		 * TODO: contact server, return success or failure. handle exceptions as
		 * appropriate
		 */
		// if we're successful.
		// TODO Auto-generated method stub
		String stringResultUserEmail = null;

		Log.i("ForgotScreen Attempt username:", userName);
		Log.i("ForgotScreen Attempt email:", userEmail);

		// the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", userName));
		nameValuePairs.add(new BasicNameValuePair("email", userEmail));

		stringResultUserEmail = trimJSONString(convertRespToString(httpPost(
				nameValuePairs,
				"http://www.courseconfessions.com/androidforgotpassword.php")));

		if (stringResultUserEmail == null) {
			throw new LoginException("Failed to connect to server");
		} else if (stringResultUserEmail.equals("success")) {
			Toast.makeText(context, "Password Reset Email has been sent",
					Toast.LENGTH_LONG).show();
			return true;
		} else {
			Toast.makeText(context, stringResultUserEmail, Toast.LENGTH_LONG).show();
			return false;
		}
	}

	/** TODO: still working on this part */
	public static boolean attemptSignup(String userName, String userEmail,
			String userPass, String userConfirmPass, Context context)
			throws LoginException {
		/*
		 * TODO: contact server, return success or failure. handle exceptions as
		 * appropriate
		 */
		// if we're successful.
		// TODO Auto-generated method stub
		ArrayList<String> stringResultUserEmail = null;

		Log.i("SignupScreen Attempt login username:", userName);
		Log.i("SignupScreen Attempt email:", userEmail);
		Log.i("SignupScreen Attempt pass:", userPass);
		Log.i("SignupScreen Attempt confirmPass:", userConfirmPass);

		// the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", userName));
		nameValuePairs.add(new BasicNameValuePair("email", userEmail));
		nameValuePairs.add(new BasicNameValuePair("password", userPass));
		nameValuePairs.add(new BasicNameValuePair("cpassword", userConfirmPass));

		stringResultUserEmail = Splitter
				.splitStringArray((convertRespToString(httpPost(nameValuePairs,
						"http://www.courseconfessions.com/androidcreateaccount.php"))));

		if (stringResultUserEmail.size() == 0) {
			throw new LoginException("Failed to connect to server");
		} else if (stringResultUserEmail.get(0).equals("success")) {
			Toast.makeText(context, "Signup successful", Toast.LENGTH_LONG).show();
			return true;
		} else {
			for (String a : stringResultUserEmail) {
				Toast.makeText(context, a, Toast.LENGTH_LONG).show();
			}
			return false;
		}
	}

	/**
	 * takes off the [" and "]
	 * 
	 * @param input
	 * @return
	 */
	private static String trimJSONString(String input) {
		if (input == null || input.length() == 0) {
			return null;
		}
		Log.e("", input);
		String result = input.substring(2, input.length() - 3);
		Log.e("", result);
		return result;
	}

	@SuppressLint("NewApi")
	public static ArrayList<String> parseListJSON(String result, String name) {
		// return object for all courses to be added to
		// ArrayList<String> resultCourses = new ArrayList<String>();
		// placeholder string to take in JSON object
		String jsonParse = "";
		try {
			JSONArray jArray = new JSONArray(result);
			Log.e("log_tag", "made it here");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				Log.i("", "made it to second");
				Log.i("", json_data.getString(name));
				jsonParse = json_data.getString(name);
				// Log.i("log_tag", "Length: " + json_data.length() + " "
				// + "USER: " + json_data.getString("USER"));
			}
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		// parse string and create array out of it
		ArrayList<String> resultCourses = Splitter.splitStringArray(jsonParse);

		return resultCourses;
	}

	public static InputStream httpPost(ArrayList<NameValuePair> pairs,
			String url) {
		InputStream is = null;

		try {
			// HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(pairs));
			// HttpResponse response = httpclient.execute(httppost);

			HttpPoster httpPoster = (HttpPoster) new HttpPoster()
					.execute(httppost);

			HttpResponse response = (HttpResponse) httpPoster.get(3000,
					TimeUnit.MILLISECONDS);

			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		return is;
	}

	public static String convertRespToString(InputStream is) {
		String result = "";
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
		return result;
	}

	public static String parseLoginJSON(String result) {
		String resultUserName = null; // where the username will be put,
		try {
			JSONArray jArray = new JSONArray(result);
			Log.e("log_tag", "made it here");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				Log.i("", "made it to second");
				Log.i("", json_data.getString("USER"));
				resultUserName = json_data.getString("USER");
				// Log.i("log_tag", "Length: " + json_data.length() + " "
				// + "USER: " + json_data.getString("USER"));
			}
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		return resultUserName;
	}

	/**
	 * create a string array with all the course sections and numbers avaiable.
	 * ex. CSE 100, CSE 110. should only include results listed under the
	 * courseSection. Note: courseSection is included to ensure that this code
	 * can be expanded at a later date. for now, always pass this function
	 * "CSE, 0, 0"
	 * 
	 * @param couseSection
	 *            ex. CSE, BIO, etc.
	 * @param lowNumber
	 *            pass 0 for no minimum
	 * @param highNumber
	 *            pass 0 for no upper limit
	 * @return TODO
	 */
	public static ArrayList<String> getListOfCourses(String courseSection,
			int lowNumber, int highNumber) {

		// course.add(new BasicNameValuePair("CourseSection", courseSection));
		// nameValuePairs.add(new BasicNameValuePair("password", userPass));
		// String page = new
		// Communicator().executeHttpGet("http://www.courseconfessions.com/androidgetcourselist.php");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", null));

		ArrayList<String> courseList = parseListJSON(
				convertRespToString(httpPost(nameValuePairs,
						"http://www.courseconfessions.com/androidgetcourselist.php")),
				"COURSES");

		if (courseList.size() == 0) {
			Log.e("phpInteractions", "Failed to connect to server");
		}
		return courseList;
	}

	public static ArrayList<String> getReviewsOfCourses(String courseSection,
			String courseNum) {

		// course.add(new BasicNameValuePair("CourseSection", courseSection));
		// nameValuePairs.add(new BasicNameValuePair("password", userPass));
		// String page = new
		// Communicator().executeHttpGet("http://www.courseconfessions.com/androidgetcourselist.php");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("dept", courseSection));
		nameValuePairs.add(new BasicNameValuePair("num", courseNum));

		ArrayList<String> courseList = parseListJSON(
				convertRespToString(httpPost(nameValuePairs,
						"http://www.courseconfessions.com/androidgetcoursereviews.php")),
				"REVIEWS");

		if (courseList.size() == 0) {
			Log.e("phpInteractions", "Failed to connect to server");
		}
		return courseList;
	}

	private static class HttpPoster extends
			AsyncTask<HttpPost, Void, HttpResponse> {

		@Override
		protected HttpResponse doInBackground(HttpPost... params) {
			HttpClient httpclient = new DefaultHttpClient();
			try {
				return httpclient.execute(params[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/*
		 * @Override protected void onPostExecute(String result) { // might want
		 * to change "executed" for the returned string passed // into
		 * onPostExecute() but that is upto you }
		 */

		@Override
		// TODO?
		protected void onPreExecute() {
		}

		@Override
		// TODO?
		protected void onProgressUpdate(Void... values) {
		}
	}
}