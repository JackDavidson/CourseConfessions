package user;

import util.SDCardWriter;
import util.XMLEOFException;
import util.XMLStringObject;
import util.XMLStringParser;
import activities.BaseScene.Screen;
import android.content.Context;

public class User {

	public static String SAVE_FILE = "userInfo.xml";
	private String userName;
	private String password;
	private String realName;
	@SuppressWarnings("unused")
	private Screen activeScreene; //the active screen 

	public User(String userName, String password, String realName,
			Context context) {
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.activeScreene = Screen.HomeScreen; 
		writeUserToFile(context);
		// TODO: write to file.
	}
	/*"assume we're reading from the file, and go load up the old
	username and pass. the file will be in XML format."*/
	public User(Context context) {
		// TODO: assume we're reading from the file, and go load up the old
		// username and pass. the file will be in XML format.
		//
		setupUserFromFile(context);
	}
	//write user to an xml file...I think
	void writeUserToFile(Context context) {
		XMLStringParser xmlRoot = new XMLStringParser();
		XMLStringObject xmlUserName = new XMLStringObject("userName", userName);
		XMLStringObject xmlUserPassword = new XMLStringObject("password",
				password);
		xmlRoot.addItem(xmlUserName);
		xmlRoot.addItem(xmlUserPassword);

		String userInfoString = xmlRoot.toString();
		SDCardWriter.writeFile(context.getFilesDir().toString(), SAVE_FILE,
				userInfoString);

	}
	//read user from an xml file...I think.
	void setupUserFromFile(Context context) {
		
		String userInfoString = SDCardWriter.readFile(context.getFilesDir().toString()
				+ User.SAVE_FILE);
		XMLStringParser xmlRoot = null;
		try {
			xmlRoot = new XMLStringParser(userInfoString);
		} catch (XMLEOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlRoot.toString();
		this.userName = xmlRoot.getChildsValue("userName");
		this.password = xmlRoot.getChildsValue("password");
		// TODO: userName =
		// TODO: password =
	}
	//return the username
	public String getUserName() {
		return userName;
	}
	//return the password
	public String getPassword() {
		return password;
	}
	//return the realname
	public String getRealName() {
		return realName;
	}
	//attempt login, and if its successful the username and password will be passed to an XML file.
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
