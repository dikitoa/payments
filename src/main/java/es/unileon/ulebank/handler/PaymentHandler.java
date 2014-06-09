package es.unileon.ulebank.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.unileon.ulebank.exceptions.PaymentHandlerException;

/**
 * Class of Payment Handler
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Payment formed by the 4 last numbers of Card Handler,
 * and 11 numbers of the month, year and complete hour.
 */
public class PaymentHandler implements Handler {

	private final int LENGTH = 15;
	private final int POSITION_CARD = 15;
	private final int NUMBER_INITIALS = 4;
	private String id;
	
	/**
	 * Class constructor
	 * @param handler
	 * @throws PaymentHandlerException
	 */
	public PaymentHandler(Handler handler, Date date) throws PaymentHandlerException{
		this.id = this.obtainInitials(handler) + this.obtainFinals(date);
		if (this.id.length() != this.LENGTH)
			throw new PaymentHandlerException("Length of payment handler incorrect.");
	}

	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}

	/**
	 * @brief Method that obtains the first 4 numbers of the handler
	 * @param cardNumber
	 * @return 4 initials
	 * @throws PaymentHandlerException
	 */
	private String obtainInitials(Handler cardNumber) throws PaymentHandlerException{
		if (cardNumber.toString().substring(this.POSITION_CARD).length() == this.NUMBER_INITIALS)
			return cardNumber.toString().substring(this.POSITION_CARD);
		else
			throw new PaymentHandlerException("Longitud de los numeros iniciales incorrecta");
	}
	
	/**
	 * Method that obtains the 11 final numbers
	 * @param date
	 * @return
	 */
	private String obtainFinals(Date date){
		SimpleDateFormat format = new SimpleDateFormat("MMyyHHmmSS");
		return format.format(date);
	}
	
	/**
	 * To String class method
	 */
	public String toString() {
		return this.id;
	}
	
	/**
	 * Getter of id
	 * @return
	 */
	public String getId() {
		return id;
	}

}
