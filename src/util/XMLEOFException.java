package util;
//prints out success or failure depending on whether it reaches EOF in the XML file or not
public class XMLEOFException extends Exception {
	
	public static String SUCCESS_STRING = "Reached EOF successfully";
	public static String FAIL_STRING = "Unexpected EOF encountered!";
	public XMLEOFException(String error) {
		super(error);
	}

}
