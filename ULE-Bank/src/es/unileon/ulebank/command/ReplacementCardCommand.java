package es.unileon.ulebank.command;

import java.io.IOException;

import org.apache.log4j.Logger;

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
 * Comando para la sustitucion de la tarjeta
 */
public class ReplacementCardCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ReplacementCardCommand.class.getName());
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Tarjeta que vamos a sustituir
	 */
	private Card card;
	/**
	 * Identificador de la tarjeta a sustituir
	 */
	private Handler cardId;
	/**
	 * Cuenta a la que esta asociada la tarjeta
	 */
	private Account account;
	/**
	 * PIN antes de la sustitucion
	 */
	private String oldPin;
	/**
	 * PIN nuevo que es generado
	 */
	private String newPin;
	/**
	 * CVV antes de la sustitucion
	 */
	private String oldCvv;
	/**
	 * CVV nuevo que es generado
	 */
	private String newCvv;
	/**
	 * Anterior fecha de caducidad
	 */
	private String oldExpirationDate;
	/**
	 * Nueva fecha de caducidad
	 */
	private String newExpirationDate;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 */
	public ReplacementCardCommand(Handler cardId, Office office, Handler dni, Handler accountHandler) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.account = office.searchClient((DNIHandler) dni).searchAccount((AccountHandler) accountHandler);
		} catch (ClientNotFoundException e) {
			LOG.info("Client with DNI " + dni.toString() + " does not exists");
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}/* catch (AccountNotFoundException e) {
			LOG.info("Account with number " + accountHandler.toString() + " does not exists");
		}*/
	}
	
	/**
	 * Realiza la sustitucion de la tarjeta
	 */
	@Override
	public void execute() {
		try {
			//Buscamos la tarjeta en la cuenta a la que esta asociada a traves del identificador
			this.card = account.searchCard((CardHandler) cardId);
			//Guardamos el PIN anterior
			this.oldPin = card.getPin();
			//Generamos el nuevo PIN y lo almacenamos
			this.newPin = card.generatePinCode();
			//Cambiamos el PIN por el nuevo
			this.card.setPin(newPin);
			//Guardamos el anterior CVV
			this.oldCvv = card.getCvv();
			//Generamos un CVV nuevo y lo guardamos
			this.newCvv = card.generateCVV();
			//Cambiamos el CVV por el nuevo
			this.card.setCvv(newCvv);
			//Guardamos la anterior fecha de caducidad
			this.oldExpirationDate = card.getExpirationDate();
			//Generamos la nueva fecha de caducidad y la almacenamos
			this.newExpirationDate = card.generateExpirationDate();
			//Cambiamos la fecha de caducidad por la nueva
			this.card.setExpirationDate(newExpirationDate);
		} catch (IOException e) {
			LOG.info(e.getMessage());
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}/* catch (CardNotFoundException e) {
			LOG.info("Card with number " + cardId.toString() + " does not exists");
		}*/
		
	}

	/**
	 * Restaura los valores antes de la sustitucion
	 */
	@Override
	public void undo() {
		try {
			//Restaura el CVV
			this.card.setCvv(oldCvv);
			//Restaura el codigo PIN
			this.card.setPin(oldPin);
			//Restaura la fecha de caducidad
			this.card.setExpirationDate(oldExpirationDate);
		} catch (IOException e) {
			LOG.info(e.getMessage());
		}
	}

	/**
	 * Vuelve a modificar los valores de la sustitucion
	 */
	@Override
	public void redo() {
		try {
			//Vuelve a cambiar el CVV por el nuevo
			this.card.setCvv(newCvv);
			//Vuelve a cambiar el PIN por el nuevo
			this.card.setPin(newPin);
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
