package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import user.User;
import visuals.PlacementEditText;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class phpInteractions {

	/* contact Eric/Jack/Nolen for questions on this file */

	/**
	 * TODO: comments, explaining method
	 * 
	 * @param username
	 *            TODO
	 * @param password
	 *            TODO
	 * @return TODO
	 */
	public static User attemptLoginAndCrateUser(String userName,
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

		stringResultUserName = parseJSON(convertRespToString(httpPost(
				nameValuePairs,
				"http://www.courseconfessions.com/androidlogin.php")));

		if(stringResultUserName == null){
			throw new LoginException("Failed to connext to server");
		} else if (stringResultUserName.equals("fail")) {
			throw new LoginException("Incorrect Username or password");
		}	else {
			return new User(userName, userPass, stringResultUserName, context);
		}

	}

	public static InputStream httpPost(ArrayList<NameValuePair> pairs,
			String url) {
		InputStream is = null;

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = httpclient.execute(httppost);
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

	public static String parseJSON(String result) {
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
	 * TODO: comments
	 * 
	 * @param couseSection
	 *            ex. CSE, BIO, etc.
	 * @param lowNumber
	 *            pass 0 for no minimum
	 * @param highNumber
	 *            pass 0 for no upper limit
	 * @return TODO
	 */
	public static String[] getListOfCourses(String couseSection, int lowNumber,
			int highNumber) {
		/*
		 * TODO: create a string array with all the couse sections and numbers
		 * avaiable. ex. CSE 100, CSE 110. should only include results listed
		 * under the courseSection. Note: courseSection is included to ensure
		 * that this code can be expanded at a later date. for now, always pass
		 * this function "CSE, 0, 0"
		 */
		return null;
	}
}