package es.unileon.ulebank.command;

import es.unileon.ulebank.handler.Handler;

/**
 * @author Israel
 */
public interface Command {
	/**
	 * Realiza la ejecucion del comando
	 */
	public void execute();
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
