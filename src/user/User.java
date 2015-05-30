package user;

import util.SDCardWriter;
import util.XMLEOFException;
import util.XMLStringObject;
import util.XMLStringParser;
import activities.BaseScene.Screen;
import android.content.Context;

public class User {

	/* ====== For every user ====== */
	public static String SAVE_FILE = "userInfo.xml";
	private static String USER_NAME = "username";
	private static String USER_PASSWORD = "password";
	private static String USER_SCREEN = "screen";
	private XMLStringParser xmlRoot;
	private String userName;
	private String password;
	private String realName;
	private Screen activeScreen; // the active screen
	/* ==== End for every user ===== */

	/* ===== For page-speciffic data ===== */
	private XMLStringObject xmlPageSpecifficData;
	/* === End For page-speciffic data === */

	public User(String userName, String password, String realName,
			Context context) {
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.activeScreen = Screen.HomeScreen;
		writeUserToFile(context);
		// TODO: write to file.
	}

	/*
	 * "assume we're reading from the file, and go load up the old username and
	 * pass. the file will be in XML format."
	 */
	public User(Context context) {
		// TODO: assume we're reading from the file, and go load up the old
		// username and pass. the file will be in XML format.
		//
		setupUserFromFile(context);
	}

	public void addXmlPageData(XMLStringObject xmlPageSpecifficData){
		this.xmlPageSpecifficData = xmlPageSpecifficData;
	}
	// write user to an xml file...I think
	public void writeUserToFile(Context context) {
		XMLStringParser xmlRoot = new XMLStringParser();
		XMLStringObject xmlUserName = new XMLStringObject(USER_NAME, userName);
		XMLStringObject xmlUserPassword = new XMLStringObject(USER_PASSWORD,
				password);
		XMLStringObject xmlActiveScreen = new XMLStringObject(USER_SCREEN,
				String.valueOf(activeScreen));
		xmlRoot.addItem(xmlUserName);
		xmlRoot.addItem(xmlUserPassword);
		xmlRoot.addItem(xmlActiveScreen);
		if(xmlPageSpecifficData != null)
			xmlRoot.addItem(xmlPageSpecifficData);

		String userInfoString = xmlRoot.toString();
		SDCardWriter.writeFile(context.getFilesDir().toString(), SAVE_FILE,
				userInfoString);

	}

	// read user from an xml file...I think.
	void setupUserFromFile(Context context) {

		String userInfoString = SDCardWriter.readFile(context.getFilesDir()
				.toString() + User.SAVE_FILE);
		xmlRoot = null;
		try {
			xmlRoot = new XMLStringParser(userInfoString);
		} catch (XMLEOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlRoot.toString();
		this.userName = xmlRoot.getChildsValue(USER_NAME);
		this.password = xmlRoot.getChildsValue(USER_PASSWORD);
		this.activeScreen = Screen.valueOf(xmlRoot.getChildsValue(USER_SCREEN));
		// TODO: userName =
		// TODO: password =
	}

	// return the username
	public String getUserName() {
		return userName;
	}

	// return the password
	public String getPassword() {
		return password;
	}

	// return the realname
	public String getRealName() {
		return realName;
	}

	// attempt login, and if its successful the username and password will be
	// passed to an XML file.
	boolean attemptLogin() {
		// TODO: use the php stuff to try to do a login,
		// TODO: if login successful, write username and pass to a file in XML
		// format. ex:
		// <xml>
		// <userName>jsdavids</userName>
		// <password>Amorphous43$</password>
		// <rememberInfo>false</rememberInfo>
		// </xml>

		// SDCardWriter.writeFile(getFilesDir().toString(),saveFile,
		// "<xml>....</xml>");
		return true;
	}

}
