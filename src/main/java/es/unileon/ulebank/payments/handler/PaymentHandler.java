package es.unileon.ulebank.payments.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;

/**
 * Class of Payment Handler
 * 
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Payment formed by the 4 last numbers of Card Handler,
 *        and 11 numbers of the month, year and complete hour.
 */
public class PaymentHandler implements Handler {

	/**
	 * Posicion del numero de tarjeta a partir de la cual obtenemos la subcadena
	 */
	private static final int POSITION_CARD = 15;

	/**
	 * Command Identifier
	 */
	private final String id;

	/**
	 * Class constructor
	 * @param handler
	 * @param date
	 * @throws MalformedHandlerException
	 */
	public PaymentHandler(Handler handler, Date date) {
		this.id = this.obtainInitials(handler) + this.obtainFinals(date);
	}

	/**
	 * Compara el identificador actual con el que se indica
	 * @param another
	 * @return devuelve un 0 si son iguales
	 * @return devuelve otro numero si son distintos
	 */
	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}
	
	/**
	 * Comprueba que dos handler son iguales
	 * @param another
	 * @return true si son iguales, false en cualquier otro caso
	 */
	@Override
	public boolean equals(Handler another) {
		if (another == null) {
			return false;
		}
		
		if (another.getClass() != getClass()) {
			return false;
		}
		
		Handler other = (Handler) another;
		
		if (this.toString().equals(other.toString())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.hashCode();
	}

	/**
	 * @brief Method that obtains the first 4 numbers of the handler
	 * @param cardNumber
	 * @return 4 initials
	 */
	private String obtainInitials(Handler cardNumber) {
		return cardNumber.toString().substring(PaymentHandler.POSITION_CARD);
	}

	/**
	 * Method that obtains the 11 final numbers
	 * @param date
	 * @return final id of the handler
	 */
	private String obtainFinals(Date date) {
		final SimpleDateFormat format = new SimpleDateFormat("MMyyHHmmSS");
		if (format.format(date).length() < 11) {
			return "0" + format.format(date);
		} else {
			return format.format(date);
		}
	}

	/**
	 * To String class method
	 * @return this handler in string
	 */
	@Override
	public String toString() {
		return this.id;
	}

	/**
	 * Getter of id
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

}