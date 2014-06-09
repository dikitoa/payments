/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.exceptions;

/**
 * @author Israel Garcia Centeno
 * Excepcion que se lanza cuando el handler no se forma correctamente
 */
public class MalformedHandlerException extends RuntimeException{
	
    /**
     * Version
     */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase
	 * @param msg
	 */
	public MalformedHandlerException(String msg) {
        super(msg);
    }
}
