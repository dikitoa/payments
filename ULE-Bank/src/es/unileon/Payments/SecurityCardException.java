package es.unileon.Payments;

/**
 * SecurityCardException Class
 * @author Rober dCR
 * @brief Exception that is thrown if the security conditions are not accepted 
 */
public class SecurityCardException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Class constructor
	 */
	SecurityCardException(){
		
	}
	
	/**
	 * Class constructor
	 * @param message
	 */
	SecurityCardException(String message){
		super(message);
	}
	

}
