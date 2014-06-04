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
 * Comando para la renovacion de la tarjeta
 */
public class RenovateCardCommand implements Command {
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Identificador de la tarjeta a renovar
	 */
	private String cardId;
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
	 * @throws ClientNotFoundException 
	 */
	public RenovateCardCommand(String cardId, Office office, String dni, String accountHandler) throws ClientNotFoundException {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = office.searchClient(dni).searchAccount(accountHandler);
	}
	
	/**
	 * Realiza la renovacion de la tarjeta
	 * @throws IOException 
	 */
	@Override
	public void execute() throws IOException {
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
	}

	/**
	 * Deshace la renovacion de la tarjeta
	 * @throws IOException 
	 */
	@Override
	public void undo() throws IOException {
		//Restaura el antiguo CVV
		this.card.setCvv(oldCvv);
		//Restaura la antigua fecha de caducidad
		this.card.setExpirationDate(oldExpirationDate);
	}

	/**
	 * Rehace la renovacion de la tarjeta despues de haber deshecho la operacion
	 * @throws IOException 
	 */
	@Override
	public void redo() throws IOException {
		//Vuelve a cambiar el CVV por el que se habia generado
		this.card.setCvv(newCvv);
		//Vuelve a cambiar la fecha de caducidad por la nueva
		this.card.setExpirationDate(newExpirationDate);
	}

	/**
	 * Devuelve el identificador del comando
	 */
	@Override
	public Handler getId() {
		return this.id;
	}
}
