package util;
//prints out success or failure depending on whether it reaches EOF in the XML file or not
public class XMLEOFException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 757909583258489256L;
	public static String SUCCESS_STRING = "Reached EOF successfully";
	public static String FAIL_STRING = "Unexpected EOF encountered!";
	public XMLEOFException(String error) {
		super(error);
	}

}
