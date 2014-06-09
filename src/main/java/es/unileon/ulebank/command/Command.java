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
	public void execute() throws Exception;
	/**
	 * Deshace los cambios realizados
	 * @throws TransferException 
	 * @throws TransactionException 
	 * @throws IOException 
	 */
	public void undo() throws Exception;

	/**
	 * Rehace los cambios deshechos
	 * @throws TransactionException 
	 * @throws PaymentException 
	 */
	public void redo() throws Exception;

	/**
	 * Devuelve el identificador del comando
	 * @return
	 */
	public Handler getId();

}
