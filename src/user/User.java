package user;

public class User {

	private static String saveFile = "userInfo.xml";
	private String userName;
	private String password;

	User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	User() {
		// TODO: assume we're reading from the file, and go load up the old
		// username and pass. the file will be in XML format.
		//
		setupUserFromFile();
	}
	
	void setupUserFromFile(){
		//TODO: userName = 
		//TODO: password = 
	}
	
	String getUserName(){
		if(userName == null){
			// load from file
			return userName;
		} else {
			return userName;
		}
	}
	
	String getPassword(){
		if(password == null){
			// load from file
			return password;
		} else {
			return password;
		}
	}

	boolean attemptLogin() {
		// TODO: use the php stuff to try to do a login,
		// TODO: if login successful, write username and pass to a file in XML
		// format. ex:
		// <xml>
		//   <userName>jsdavids</userName>
		//   <password>Amorphous43$</password>
		//   <rememberInfo>false</rememberInfo>
		// </xml>
		
		//SDCardWriter.writeFile(getFilesDir().toString(),saveFile, "<xml>....</xml>");
		return true;
	}

}
