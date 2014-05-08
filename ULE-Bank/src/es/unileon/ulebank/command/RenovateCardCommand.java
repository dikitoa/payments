package es.unileon.ulebank.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.exception.AccountNotFoundException;
import es.unileon.ulebank.exceptions.CardNotFoundException;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 * Comando para la renovacion de la tarjeta
 */
public class RenovateCardCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(RenovateCardCommand.class.getName());
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Identificador de la tarjeta a renovar
	 */
	private Handler cardId;
	/**
	 * Cuenta a la que esta asociada la tarjeta que se va a renovar
	 */
	private Account account;
	/**
	 * Tarjeta que se va a renovar
	 */
	private Card card;
	/**
	 * Antiguo CVV antes de realizar la renovacion
	 */
	private String oldCvv;
	/**
	 * Nuevo CVV que se va a asignar
	 */
	private String newCvv;
	/**
	 * Antigua fecha de caducidad de la tarjeta
	 */
	private String oldExpirationDate;
	/**
	 * Nueva fecha de caducidad de la tarjeta
	 */
	private String newExpirationDate;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 */
	public RenovateCardCommand(Handler cardId, Office office, Handler dni, Handler accountHandler) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.account = office.searchClient(dni).searchAccount(accountHandler);
		} catch (ClientNotFoundException e) {
			LOG.info("Client with dni " + dni.toString() + " does not exists");
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		} catch (AccountNotFoundException e) {
			LOG.info("Account with number " + accountHandler.toString() + " does not exists");
		}
	}
	
	/**
	 * Realiza la renovacion de la tarjeta
	 */
	@Override
	public void execute() {
		try {
			//Buscamos la tarjeta en la cuenta con el identificador de la misma
			this.card = this.account.searchCard(cardId);
			//Guardamos el CVV para poder deshacer la operacion
			this.oldCvv = this.card.getCvv();
			//Guardamos la fecha de caducidad para poder deshacer la operacion
			this.oldExpirationDate = card.getExpirationDate();
			//Generamos la nueva fecha de caducidad
			this.newExpirationDate = card.generateExpirationDate();
			//Cambiamos la fecha de caducidad por la nueva
			this.card.setExpirationDate(newExpirationDate);
			//Generamos el nuevo CVV y lo guardamos
			this.newCvv = card.generateCVV();
			//Cambiamos el CVV por el nuevo que se genera
			this.card.setCvv(newCvv);
		} catch (IOException e) {
			LOG.info(e.getMessage());
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		} catch (CardNotFoundException e) {
			LOG.info("Card with number " + cardId.toString() + " does not exists");
		}
	}

	/**
	 * Deshace la renovacion de la tarjeta
	 */
	@Override
	public void undo() {
		try {
			//Restaura el antiguo CVV
			this.card.setCvv(oldCvv);
			//Restaura la antigua fecha de caducidad
			this.card.setExpirationDate(oldExpirationDate);
		} catch (IOException e) {
			LOG.info(e.getMessage());
		}
	}

	/**
	 * Rehace la renovacion de la tarjeta despues de haber deshecho la operacion
	 */
	@Override
	public void redo() {
		try {
			//Vuelve a cambiar el CVV por el que se habia generado
			this.card.setCvv(newCvv);
			//Vuelve a cambiar la fecha de caducidad por la nueva
			this.card.setExpirationDate(newExpirationDate);
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
