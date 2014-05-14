package es.unileon.ulebank.command;

import java.io.IOException;

import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.TransactionException;

/**
 * @author Israel
 */
public interface Command {
	/**
	 * Realiza la ejecucion del comando
	 * @throws InvalidFeeException 
	 * @throws TransactionException 
	 * @throws PaymentException 
	 * @throws TransferException 
	 */
	public void execute() throws InvalidFeeException, PaymentException, TransactionException, TransferException;
	/**
	 * Deshace los cambios realizados
	 * @throws TransferException 
	 * @throws TransactionException 
	 * @throws IOException 
	 */
	public void undo() throws TransferException, TransactionException, IOException;
	/**
	 * Rehace los cambios deshechos
	 * @throws TransactionException 
	 * @throws PaymentException 
	 */
	public void redo() throws PaymentException, TransactionException;
	/**
	 * Devuelve el identificador del comando
	 * @return
	 */
	public Handler getId();
}
