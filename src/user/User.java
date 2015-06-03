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
	public static String XML_SCREEN_DATA_NAME = "screenData";
	private static String USER_NAME = "username";
	private static String USER_PASSWORD = "password";
	private static String USER_SCREEN = "screen";
	private XMLStringParser xmlRoot;
	private String userName;
	private String password;
	private String realName;
	private Screen activeScreen; // the active screen
	private boolean ready = false;
	/* ==== End for every user ===== */

	/* ===== For page-speciffic data ===== */
	private XMLStringObject xmlPageSpecifficData;

	/* === End For page-speciffic data === */

	public User(String userName, String password, String realName,
			Context context) {
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.activeScreen = Screen.LoginScreen;
		save(context);
		// TODO: write to file.
	}

	/**
	 * "assume we're reading from the file, and go load up the old username and
	 * pass. the file will be in XML format."
	 */
	public User(Context context) {
		setupUserFromFile(context);
	}

	public void addXmlPageData(XMLStringObject xmlPageSpecifficData) {
		this.xmlPageSpecifficData = xmlPageSpecifficData;
	}

	// write user to an xml file...I think
	public void save(Context context) {
		XMLStringParser xmlRoot = new XMLStringParser();
		XMLStringObject xmlUserName = new XMLStringObject(USER_NAME, userName);
		XMLStringObject xmlUserPassword = new XMLStringObject(USER_PASSWORD,
				password);
		XMLStringObject xmlActiveScreen = new XMLStringObject(USER_SCREEN,
				String.valueOf(activeScreen));
		xmlRoot.addItem(xmlUserName);
		xmlRoot.addItem(xmlUserPassword);
		xmlRoot.addItem(xmlActiveScreen);
		if (xmlPageSpecifficData != null)
			xmlRoot.addItem(xmlPageSpecifficData);

		String userInfoString = xmlRoot.toString();
		SDCardWriter.writeFile(context.getFilesDir().toString(), SAVE_FILE,
				userInfoString);
		ready = true;

	}

	// read user from an xml file...I think.
	void setupUserFromFile(Context context) {

		String userInfoString = SDCardWriter.readFile(context.getFilesDir()
				.toString() + User.SAVE_FILE);
		if (userInfoString.equals(""))
			return;
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
		this.xmlPageSpecifficData = xmlRoot.getChild(XML_SCREEN_DATA_NAME);
		// TODO: userName =
		// TODO: password =
		ready = true;
	}

	public XMLStringObject getPageData() {
		return xmlPageSpecifficData;
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

	// TODO: attempt login, and if its successful then return true
	boolean checkCredentials() {
		return true;
	}

	public Screen getScreen() {
		return activeScreen;
	}

	public void setScreen(Screen screen) {
		activeScreen = screen;
	}

	/**
	 * delete the users page specific data, and
	 */
	public void logout() {
		this.userName = "";
		this.password = "";
		this.realName = "";
		this.activeScreen = Screen.LoginScreen;
		this.xmlPageSpecifficData = null;
	}

	public boolean ready() {
		return ready;
	}

}
