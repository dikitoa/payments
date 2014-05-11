package es.unileon.ulebank.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.CardNotFoundException;
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
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ModifyPinCommand.class.getName());
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
	 */
	public ModifyPinCommand(Handler cardId, Office office, Handler dni, Handler accountHandler, String newPin) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.account = office.searchClient((DNIHandler) dni).searchAccount((AccountHandler) accountHandler);
			this.newPin = newPin;
		} catch (ClientNotFoundException e) {
			LOG.info("Client with dni " + dni.toString() + " is not found");
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}/* catch (AccountNotFoundException e) {
			LOG.info("Account with number " + accountHandler.toString() + " is not found");
		}*/
	}
	
	/**
	 * Realiza la modificacion del PIN
	 */
	@Override
	public void execute() {
		try {
			//Buscamos la tarjeta en la cuenta
			this.card = account.searchCard((CardHandler) cardId);
			//Almacenamos el antiguo PIN
			this.oldPin = card.getPin();
			//Cambiamos el PIN por el nuevo
			this.card.setPin(newPin);
		} catch (IOException e) {
			LOG.info(e.getMessage());
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}/* catch (CardNotFoundException e) {
			LOG.info("Card with number " + cardId.toString() + " is not found");
		}*/
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
			LOG.info(e.getMessage());
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
			LOG.info(e.getMessage());
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
