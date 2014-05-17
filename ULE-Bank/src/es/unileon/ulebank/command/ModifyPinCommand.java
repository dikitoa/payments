package es.unileon.ulebank.command;

import java.io.IOException;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 * Comando para modificar el codigo PIN de la tarjeta
 */
public class ModifyPinCommand implements Command {
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Identificador de la tarjeta
	 */
	private Handler cardId;
	/**
	 * Tarjeta cuyo PIN vamos a modificar
	 */
	private Card card;
	/**
	 * Cuenta a la que esta asociada la tarjeta
	 */
	private Account account;
	/**
	 * PIN que se va a modificar
	 */
	private String newPin;
	/**
	 * PIN antes de modificarlo
	 */
	private String oldPin;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 * @param newPin
	 * @throws ClientNotFoundException 
	 */
	public ModifyPinCommand(Handler cardId, Office office, Handler dni, Handler accountHandler, String newPin) throws ClientNotFoundException {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = office.searchClient((DNIHandler) dni).searchAccount((AccountHandler) accountHandler);
		this.newPin = newPin;
	}
	
	/**
	 * Realiza la modificacion del PIN
	 * @throws IOException 
	 */
	@Override
	public void execute() throws IOException {
		//Buscamos la tarjeta en la cuenta
		this.card = account.searchCard((CardHandler) cardId);
		//Almacenamos el antiguo PIN
		this.oldPin = card.getPin();
		//Cambiamos el PIN por el nuevo
		this.card.setPin(newPin);
	}

	/**
	 * Deshace la modificacion del PIN restaurando el anterior
	 * @throws IOException 
	 */
	@Override
	public void undo() throws IOException {
		//Restaura el PIN al valor anterior
		this.card.setPin(oldPin);
	}

	/**
	 * Rehace la modificacion del PIN
	 * @throws IOException 
	 */
	@Override
	public void redo() throws IOException {
		//Recuperamos la modificacion del PIN
		this.card.setPin(newPin);
	}

	/**
	 * Devuelve el identificador del comando
	 */
	@Override
	public Handler getId() {
		return this.id;
	}
}
