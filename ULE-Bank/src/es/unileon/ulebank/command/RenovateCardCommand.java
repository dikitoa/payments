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
 * Comando para la renovacion de la tarjeta
 */
public class RenovateCardCommand implements Command {
	//Identificador del comando
	private Handler id;
	//Identificador de la tarjeta a renovar
	private Handler cardId;
	//Cuenta a la que esta asociada la tarjeta que se va a renovar
	private Account account;
	//Tarjeta que se va a renovar
	private Card card;
	//Antiguo CVV antes de realizar la renovacion
	private String oldCvv;
	//Nuevo CVV que se va a asignar
	private String newCvv;
	//Antigua fecha de caducidad de la tarjeta
	private String oldExpirationDate;
	//Nueva fecha de caducidad de la tarjeta
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
		} catch (Exception e) {
			Logger.getLogger(RenovateCardCommand.class.toString()).log(Level.SEVERE, null, e);
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
			Logger.getLogger(RenovateCardCommand.class.toString()).log(Level.SEVERE, null, e);
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
			Logger.getLogger(RenovateCardCommand.class.toString()).log(Level.SEVERE, null, e);
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
			Logger.getLogger(RenovateCardCommand.class.toString()).log(Level.SEVERE, null, e);
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
