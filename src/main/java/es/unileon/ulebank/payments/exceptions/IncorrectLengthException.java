package es.unileon.ulebank.payments.exceptions;

/**
 * Class of Exception for Incorrect Length
 * @author Rober dCR
 * @brief Exception which is thrown when the length of some attribute of card is incorrect
 */
public class IncorrectLengthException extends PaymentException {
    
	/**
	 * Version
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     * @param msg
     */
    public IncorrectLengthException(String msg) {
        super(msg);
    }
}
