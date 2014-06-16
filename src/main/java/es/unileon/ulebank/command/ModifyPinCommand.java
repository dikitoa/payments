package es.unileon.ulebank.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.exceptions.PaymentException;

/**
 * @author Israel
 * Comando para modificar el codigo PIN de la tarjeta
 */
public class ModifyPinCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ModifyPinCommand.class.getName());
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Tarjeta cuyo PIN vamos a modificar
	 */
	private Card card;
	/**
	 * PIN que se va a modificar
	 */
	private String newPin;
	/**
	 * PIN antes de modificarlo
	 */
	private String oldPin;
//	private SimpleCardManager cM;
	
	/**
	 * Constructor de la clase
	 * @param cardManager 
	 * @param cardId
	 * @param newPin
	 */
	public ModifyPinCommand(Card card, String pin) {
		this.id = card.getId();
		this.card = card;
		this.newPin = pin;
//		this.cM = cardManager;
	}
	
	/**
	 * Realiza la modificacion del PIN
	 * @throws IOException 
	 * @throws PaymentException 
	 */
	public void execute() throws IOException, PaymentException {
			//Almacenamos el antiguo PIN
			this.oldPin = card.getPin();
			//Cambiamos el PIN por el nuevo
			this.card.setPin(newPin);
			LOG.info("Command executed.");
	}

	/**
	 * Deshace la modificacion del PIN restaurando el anterior
	 * @throws PaymentException 
	 */
	public void undo() throws PaymentException {
		//Restaura el PIN al valor anterior
		this.card.setPin(oldPin);
		LOG.info("Command undoned.");
	}

	/**
	 * Rehace la modificacion del PIN
	 * @throws PaymentException 
	 */
	public void redo() throws PaymentException {
		//Recuperamos la modificacion del PIN
		this.card.setPin(newPin);
		LOG.info("Command re-done.");
	}

	/**
	 * Devuelve el identificador del comando
	 */
	public Handler getID() {
		return this.id;
	}
}
