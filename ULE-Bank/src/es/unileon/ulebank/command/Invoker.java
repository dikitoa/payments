package es.unileon.ulebank.command;

public class Invoker {
	private Command command;
	
	/**
	 * Constructor de la clase
	 * @param command
	 */
	public Invoker(Command command) {
		this.command = command;
	}
	
	/**
	 * Ejecuta el comando recibido
	 */
	public void run() {
		this.command.execute();
	}
	
	/**
	 * Cambia el comando actual por el indicado
	 * @param command
	 */
	public void setCommand(Command command) {
		this.command = command;
	}
}
