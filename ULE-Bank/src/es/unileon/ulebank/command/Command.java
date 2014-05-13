package es.unileon.ulebank.command;

import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.exceptions.TransferException;
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
	 * @throws TransferException 
	 */
	public void execute() throws InvalidFeeException, PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException, TransferException;
	/**
	 * Deshace los cambios realizados
	 * @throws TransferException 
	 * @throws es.unileon.ulebank.history.TransactionException 
	 */
	public void undo() throws TransferException, es.unileon.ulebank.history.TransactionException;
	/**
	 * Rehace los cambios deshechos
	 * @throws es.unileon.ulebank.history.TransactionException 
	 * @throws TransactionException 
	 * @throws PaymentException 
	 */
	public void redo() throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException;
	/**
	 * Devuelve el identificador del comando
	 * @return
	 */
	public Handler getId();
}
