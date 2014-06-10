package es.unileon.ulebank.command;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.log4j.Logger;

import es.unileon.ulebank.exceptions.CardNotFoundException;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 */
public class ModifyBuyLimitCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ModifyBuyLimitCommand.class.getName());
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Objeto tarjeta del que se modificaran los datos
	 */
	private Card card;
	/**
	 * Cantidad nueva a modificar
	 */
	private double newAmount;
	/**
	 * Cantidad antes de realizar la modificacion
	 */
	private double oldAmount;
	/**
	 * Tipo de limite a modificar (diario o mensual)
	 */
	private String type;
	/**
	 * Variable para saber si el comando ha sido ejecutado o no
	 */
	private boolean executed = false;
	/**
	 * Variable para saber si el comando ha sido deshecho o no
	 */
	private boolean undone = false;

	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param card
	 * @param amount
	 * @param type
	 * @throws AccountNotFoundException 
	 */
	public ModifyBuyLimitCommand(Handler cardId, Card card, double amount, String type) throws AccountNotFoundException {
		try {
			this.id = new CommandHandler(cardId);
			this.card = card;
			this.newAmount = amount;
			this.type = type;
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
	}

	/**
	 * Realiza la modificacion del limite de compra ya sea diario o mensual
	 * @throws CardNotFoundException 
	 */
	@Override
	public void execute() throws CardNotFoundException, IncorrectLimitException {
		//Buscamos la tarjeta con el identificador de la misma en la lista de tarjetas de la cuenta
		try {
			//Si el limite a modificar es diario
			if (type.equalsIgnoreCase("diary")) {
				//Guardamos la cantidad anterior para poder deshacer la operacion
				this.oldAmount = this.card.getBuyLimitDiary();
				//Cambiamos el limite por el indicado
				this.card.setBuyLimitDiary(newAmount);
				this.executed = true;
				//Si el limite a modificar es mensual
			} else if (type.equalsIgnoreCase("monthly")) {
				//Guardamos la cantidad anterior para poder deshacer la operacion
				this.oldAmount = this.card.getBuyLimitMonthly();
				//Cambiamos el limite por el indicado
				this.card.setBuyLimitMonthly(newAmount);
				this.executed = true;
				//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
			} else {
				LOG.info("Limit type not defined");
			}
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		} catch (IncorrectLimitException e) {
			LOG.info(e.getMessage());
			throw new IncorrectLimitException("Diary limit must been lower tha monthly limit");
		}
	}

	/**
	 * Deshace la modificacion del limite de compra dejandolo como estaba
	 * @throws CommandException 
	 */
	@Override
	public void undo() throws CommandException {
		//Si el tipo es diario
		if (this.executed){
			if (type.equalsIgnoreCase("diary")) {
				try {
					//Recuperamos el limite anterior
					this.card.setBuyLimitDiary(oldAmount);
					this.undone = true;
				} catch (IncorrectLimitException e) {
					LOG.info(e.getMessage());
				}
				//Si el tipo es mensual
			} else if (type.equalsIgnoreCase("monthly")) {
				try {
					//Recuperamos el limite anterior
					this.card.setBuyLimitMonthly(oldAmount);
					this.undone = true;
				} catch (IncorrectLimitException e) {
					LOG.info(e.getMessage());
				}
				//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
			} else {
				LOG.info("Limit type not defined");
			}
		} else {
			LOG.info("Can't undo because command has not executed yet.");
			throw new CommandException("Can't undo because command has not executed yet.");
		}

	}

	/**
	 * Rehace la modificacion del limite de compra despues de haberlo deshecho
	 * @throws CommandException 
	 */
	@Override
	public void redo() throws CommandException {
		if (this.undone){
			//Si el tipo es diario
			if (type.equalsIgnoreCase("diary")) {
				try {
					//Volvemos a cambiar el limite por el que lo habiamos cambiado anteriormente
					this.card.setBuyLimitDiary(newAmount);
					this.undone = false;
				} catch (IncorrectLimitException e) {
					LOG.info(e.getMessage());
				}
				//Si el tipo es mensual
			} else if (type.equalsIgnoreCase("monthly")) {
				try {
					//Volvemos a cambiar el limite por el que lo habiamos cambiado anteriormente
					this.card.setBuyLimitMonthly(newAmount);
					this.undone = false;
				} catch (IncorrectLimitException e) {
					LOG.info(e.getMessage());
				}
				//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
			} else {
				LOG.info("Limit type not defined");
			}
		} else {
			LOG.info("Can't undo because command has not undoned yet.");
			throw new CommandException("Can't undo because command has not undoned yet.");
		}
	}

	/**
	 * Devuelve el identificador del comando
	 * @return command id
	 */
	@Override
	public Handler getId() {
		return this.id;
	}
}
