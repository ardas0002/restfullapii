package pl.ardas.restfullapi.exceptions;

public class UserServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 12345235L;
	
	public UserServiceException(String message) {
		super(message);
	}
}
