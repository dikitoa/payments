package es.unileon.ulebank.command;

import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.Handler;

/**
 * @author Israel
 */
public interface Command {
	/**
	 * Realiza la ejecucion del comando
	 * @throws InvalidFeeException 
	 * @throws es.unileon.ulebank.history.TransactionException 
	 * @throws TransactionException 
	 * @throws PaymentException 
	 */
	public void execute() throws InvalidFeeException, PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException;
	/**
	 * Deshace los cambios realizados
	 */
	public void undo();
	/**
	 * Rehace los cambios deshechos
	 */
	public void redo();
	/**
	 * Devuelve el identificador del comando
	 * @return
	 */
	public Handler getId();
}
