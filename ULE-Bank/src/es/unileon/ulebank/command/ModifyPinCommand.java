package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 * Comando para modificar el codigo PIN de la tarjeta
 */
public class ModifyPinCommand implements Command {
	//Identificador del comando
	private Handler id;
	//Identificador de la tarjeta
	private Handler cardId;
	//Tarjeta cuyo PIN vamos a modificar
	private Card card;
	//Cuenta a la que esta asociada la tarjeta
	private Account account;
	//PIN que se va a modificar
	private String newPin;
	//PIN antes de modificarlo
	private String oldPin;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 * @param newPin
	 */
	public ModifyPinCommand(Handler cardId, Office office, Handler dni, Handler accountHandler, String newPin) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.account = office.searchClient(dni).searchAccount(accountHandler);
			this.newPin = newPin;
		} catch (Exception e) {
			Logger.getLogger(ModifyPinCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}
	
	/**
	 * Realiza la modificacion del PIN
	 */
	@Override
	public void execute() {
		try {
			//Buscamos la tarjeta en la cuenta
			this.card = account.searchCard(cardId);
			//Almacenamos el antiguo PIN
			this.oldPin = card.getPin();
			//Cambiamos el PIN por el nuevo
			this.card.setPin(newPin);
		} catch (IOException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin", e);
		}
	}

	/**
	 * Deshace la modificacion del PIN restaurando el anterior
	 */
	@Override
	public void undo() {
		try {
			//Restaura el PIN al valor anterior
			this.card.setPin(oldPin);
		} catch (IOException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin", e);
		}
	}

	/**
	 * Rehace la modificacion del PIN
	 */
	@Override
	public void redo() {
		try {
			//Recuperamos la modificacion del PIN
			this.card.setPin(newPin);
		} catch (IOException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin", e);
		}
	}

	/**
	 * Devuelve el identificador del comando
	 */
	@Override
	public Handler getId() {
		return this.id;
	}
}
