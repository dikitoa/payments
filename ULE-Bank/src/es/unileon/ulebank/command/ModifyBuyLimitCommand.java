package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.AccountHandler;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.IdDNI;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Office;

/**
 * @author Israel
 */
public class ModifyBuyLimitCommand implements Command {
	//Identificador del comando
	private Handler id;
	//Identificador de la tarjeta
	private CardHandler cardId;
	//Cuenta a la que esta asociada la tarjeta
	private Account account;
	//Objeto tarjeta del que se modificaran los datos
	private Card card;
	//Cantidad nueva a modificar
	private double newAmount;
	//Cantidad antes de realizar la modificacion
	private double oldAmount;
	//Tipo de limite a modificar (diario o mensual)
	private String type;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 * @param amount
	 * @param type
	 */
	public ModifyBuyLimitCommand(CardHandler cardId, Office office, IdDNI dni, AccountHandler accountHandler, double amount, String type) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.account = office.searchClient(dni).searchAccount(accountHandler);
			this.newAmount = amount;
			this.type = type;
		} catch (ClientNotFoundException e) {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}
	
	/**
	 * Realiza la modificacion del limite de compra ya sea diario o mensual
	 */
	@Override
	public void execute() {
		//Buscamos la tarjeta con el identificador de la misma en la lista de tarjetas de la cuenta
		this.card = account.searchCard(cardId);
		
		//Si el limite a modificar es diario
		if (type.equalsIgnoreCase("diary")) {
			try {
				//Guardamos la cantidad anterior para poder deshacer la operacion
				this.oldAmount = this.card.getBuyLimitDiary();
				//Cambiamos el limite por el indicado
				this.card.setBuyLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Diary limit cannot be greater than monthly", e);
			}
		//Si el limite a modificar es mensual
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				//Guardamos la cantidad anterior para poder deshacer la operacion
				this.oldAmount = this.card.getBuyLimitMonthly();
				//Cambiamos el limite por el indicado
				this.card.setBuyLimitMonthly(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
		//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
		}
	}

	/**
	 * Deshace la modificacion del limite de compra dejandolo como estaba
	 */
	@Override
	public void undo() {
		//Si el tipo es diario
		if (type.equalsIgnoreCase("diary")) {
			try {
				//Recuperamos el limite anterior
				this.card.setBuyLimitDiary(oldAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Diary limit cannot be greater than monthly", e);
			}
		//Si el tipo es mensual
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				//Recuperamos el limite anterior
				this.card.setBuyLimitMonthly(oldAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
			//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
		}
	}

	/**
	 * Rehace la modificacion del limite de compra despues de haberlo deshecho
	 */
	@Override
	public void redo() {
		//Si el tipo es diario
		if (type.equalsIgnoreCase("diary")) {
			try {
				//Volvemos a cambiar el limite por el que lo habiamos cambiado anteriormente
				this.card.setBuyLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Diary limit cannot be greater than monthly", e);
			}
		//Si el tipo es mensual
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				//Volvemos a cambiar el limite por el que lo habiamos cambiado anteriormente
				this.card.setBuyLimitMonthly(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Monthly limit cannot be smaller than diary", e);
			}
			//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
		} else {
			Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
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
