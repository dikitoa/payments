package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 * Comando para la sustitucion de la tarjeta
 */
public class ReplacementCardCommand implements Command {
	//Identificador del comando
	private Handler id;
	//Tarjeta que vamos a sustituir
	private Card card;
	//Identificador de la tarjeta a sustituir
	private Handler cardId;
	//Cuenta a la que esta asociada la tarjeta
	private Account account;
	//PIN antes de la sustitucion
	private String oldPin;
	//PIN nuevo que es generado
	private String newPin;
	//CVV antes de la sustitucion
	private String oldCvv;
	//CVV nuevo que es generado
	private String newCvv;
	//Anterior fecha de caducidad
	private String oldExpirationDate;
	//Nueva fecha de caducidad
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
			this.account = office.searchClient(dni).searchAccount(accountHandler);
		} catch (ClientNotFoundException e) {
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}
	
	/**
	 * Realiza la sustitucion de la tarjeta
	 */
	@Override
	public void execute() {
		try {
			//Buscamos la tarjeta en la cuenta a la que esta asociada a traves del identificador
			this.card = account.searchCard(cardId);
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
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, "Incorrect Pin");
		}
		
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
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, null, e);
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
			Logger.getLogger(ReplacementCardCommand.class.toString()).log(Level.SEVERE, null, e);
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
