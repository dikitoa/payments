package es.unileon.ulebank.exceptions;

/**
 * @author Israel
 */
public class IncorrectLimitException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorrectLimitException(String message) {
		super(message);
	}
}
