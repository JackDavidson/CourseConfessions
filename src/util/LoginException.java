package util;
//handles login exceptions
public class LoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4553840655678195936L;

	public LoginException(String error){
		super(error);
	}
}
