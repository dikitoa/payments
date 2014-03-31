package es.unileon.ulebank.exceptions;

/**
 * @author Israel
 */
public class ExcesiveLimitException extends Exception {
	private static final long serialVersionUID = 1L;

	public ExcesiveLimitException(String message) {
		super(message);
	}
}
