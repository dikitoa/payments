package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.Office;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.CardNotFoundException;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 */
public class ModifyCashLimitCommand implements Command {
	//Identificador del comando
	private Handler id;
	//Objeto tarjeta cuyos limites se van a modificar
	private Card card;
	//Cuenta a la que esta asociada la tarjeta que vamos a modificar
	private Account account;
	//Identificador de la tarjeta que se va a modificar
	private Handler cardId;
	//Nueva cantidad a modificar
	private double newAmount;
	//Cantidad antes de la modificacion
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
	public ModifyCashLimitCommand(Handler cardId, Office office, Handler dni, Handler accountHandler, double amount, String type) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.account = office.searchClient(dni).searchAccount(accountHandler);
			this.newAmount = amount;
			this.type = type;
		} catch (Exception e) {
			Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}
	
	/**
	 * Realiza la modificacion del limite de extraccion en cajero ya sea diario o mensual
	 */
	@Override
	public void execute() {
		//Buscamos la tarjeta con el identificador de la misma en la lista de tarjetas de la cuenta
		try {
			this.card = account.searchCard(cardId);
			
			//Si el limite a modificar es diario
			if (type.equalsIgnoreCase("diary")) {
					//Guardamos la cantidad anterior para poder deshacer la operacion
					this.oldAmount = this.card.getCashLimitDiary();
					//Cambiamos el limite por el indicado
					this.card.setCashLimitDiary(newAmount);
				//Si el limite a modificar es mensual
			} else if (type.equalsIgnoreCase("monthly")) {
					//Guardamos la cantidad anterior para poder deshacer la operacion
					this.oldAmount = this.card.getCashLimitMonthly();
					//Cambiamos el limite por el indicado
					this.card.setCashLimitMonthly(newAmount);
				//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
			} else {
				Logger.getLogger(ModifyBuyLimitCommand.class.toString()).log(Level.SEVERE, "Limit type not defined");
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CardNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectLimitException e) {
			// TODO: handle exception
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
				this.card.setCashLimitDiary(oldAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
			}
			//Si el tipo es mensual
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				//Recuperamos el limite anterior
				this.card.setCashLimitMonthly(oldAmount);
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
				this.card.setCashLimitDiary(newAmount);
			} catch (IncorrectLimitException e) {
				Logger.getLogger(ModifyCashLimitCommand.class.toString()).log(Level.SEVERE, null, e);
			}
			//Si el tipo es mensual
		} else if (type.equalsIgnoreCase("monthly")) {
			try {
				//Volvemos a cambiar el limite por el que lo habiamos cambiado anteriormente
				this.card.setCashLimitMonthly(newAmount);
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
