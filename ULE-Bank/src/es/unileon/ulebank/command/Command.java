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
	
	public void undo();
	
	public void redo();
	
	public Handler getId();
}
