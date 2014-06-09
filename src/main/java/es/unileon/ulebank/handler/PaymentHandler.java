package es.unileon.ulebank.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.unileon.ulebank.exceptions.MalformedHandlerException;

/**
 * Class of Payment Handler
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Payment formed by the 4 last numbers of Card Handler,
 * and 11 numbers of the month, year and complete hour.
 */
public class PaymentHandler implements Handler {

	/**
	 * Length of the command identifier
	 */
	private final int LENGTH = 15;
	/**
	 * Posicion del numero de tarjeta a partir de la cual obtenemos la subcadena 
	 */
	private final int POSITION_CARD = 15;
	/**
	 * Longitud de los numeros obtenidos del handler de la tarjeta
	 */
	private final int NUMBER_INITIALS = 4;
	/**
	 * Command Identifier
	 */
	private String id;

	/**
	 * Class constructor
	 * @param handler
	 * @throws MalformedHandlerException
	 * @throws PaymentHandlerException 
	 */
	public PaymentHandler(Handler handler, Date date) throws MalformedHandlerException{
		this.id = this.obtainInitials(handler) + this.obtainFinals(date);
		if (this.id.length() != this.LENGTH)
			throw new MalformedHandlerException("Length of payment handler incorrect.");
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
	 * @brief Method that obtains the first 4 numbers of the handler
	 * @param cardNumber
	 * @return 4 initials
	 * @throws MalformedHandlerException
	 */
	private String obtainInitials(Handler cardNumber) throws MalformedHandlerException{
		if (cardNumber.toString().substring(this.POSITION_CARD).length() == this.NUMBER_INITIALS)
			return cardNumber.toString().substring(this.POSITION_CARD);
		else
			throw new MalformedHandlerException("Longitud de los numeros iniciales incorrecta");
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
	 * @return this handler in string
	 */
	public String toString() {
		return this.id;
	}

	/**
	 * Getter of id
	 * @return id
	 */
	public String getId() {
		return id;
	}

}
