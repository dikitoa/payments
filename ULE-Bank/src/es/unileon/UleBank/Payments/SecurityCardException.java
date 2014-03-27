package es.unileon.UleBank.Payments;

/**
 * SecurityCardException Class
 * @author Rober dCR
 * @date 26/03/2014
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
