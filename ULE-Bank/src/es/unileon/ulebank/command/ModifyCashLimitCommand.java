package es.unileon.ulebank.command;


import org.apache.log4j.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 * Comando que se encarga de la modificacion de los limites de estraccion en cajero de la tarjeta
 * recibiendo el tipo de limite que tiene que modificar (diario o mensual)
 */
public class ModifyCashLimitCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ModifyCashLimitCommand.class.getName());
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Objeto tarjeta cuyos limites se van a modificar
	 */
	private Card card;
	/**
	 * Cuenta a la que esta asociada la tarjeta que vamos a modificar
	 */
	private Account account;
	/**
	 * Identificador de la tarjeta que se va a modificar
	 */
	private Handler cardId;
	/**
	 * Nueva cantidad a modificar
	 */
	private double newAmount;
	/**
	 * Cantidad antes de la modificacion
	 */
	private double oldAmount;
	/**
	 * Tipo de limite a modificar (diario o mensual)
	 */
	private String type;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 * @param amount
	 * @param type
	 * @throws ClientNotFoundException 
	 */
	public ModifyCashLimitCommand(Handler cardId, Office office, Handler dni, Handler accountHandler, double amount, String type) throws ClientNotFoundException {
		this.id = new CommandHandler(cardId);
		this.cardId = cardId;
		this.account = office.searchClient((DNIHandler) dni).searchAccount((AccountHandler) accountHandler);
		this.newAmount = amount;
		this.type = type;
	}
	
	/**
	 * Realiza la modificacion del limite de extraccion en cajero ya sea diario o mensual
	 * @throws IncorrectLimitException 
	 * @throws CommandException 
	 */
	@Override
	public void execute() throws IncorrectLimitException, CommandException {
		//Buscamos la tarjeta con el identificador de la misma en la lista de tarjetas de la cuenta
		this.card = account.searchCard((CardHandler) cardId);

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
			LOG.info("Limit type not defined");
			throw new CommandException("Limit type not defined");
		}	
	}

	/**
	 * Deshace la modificacion del limite de compra dejandolo como estaba
	 * @throws IncorrectLimitException 
	 * @throws CommandException 
	 */
	@Override
	public void undo() throws IncorrectLimitException, CommandException {
		//Si el tipo es diario
		if (type.equalsIgnoreCase("diary")) {
			//Recuperamos el limite anterior
			this.card.setCashLimitDiary(oldAmount);
			//Si el tipo es mensual
		} else if (type.equalsIgnoreCase("monthly")) {
			//Recuperamos el limite anterior
			this.card.setCashLimitMonthly(oldAmount);
			//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
		} else {
			LOG.info("Limit type not defined");
			throw new CommandException("Limit type not defined");
		}
	}

	/**
	 * Rehace la modificacion del limite de compra despues de haberlo deshecho
	 * @throws IncorrectLimitException 
	 * @throws CommandException 
	 */
	@Override
	public void redo() throws IncorrectLimitException, CommandException {
		//Si el tipo es diario
		if (type.equalsIgnoreCase("diary")) {
			//Volvemos a cambiar el limite por el que lo habiamos cambiado anteriormente
			this.card.setCashLimitDiary(newAmount);
			//Si el tipo es mensual
		} else if (type.equalsIgnoreCase("monthly")) {
			//Volvemos a cambiar el limite por el que lo habiamos cambiado anteriormente
			this.card.setCashLimitMonthly(newAmount);
			//Si no se indica el tipo de limite a modificar adecuadamente no va a realizar la operacion
		} else {
			LOG.info("Limit type not defined");
			throw new CommandException("Limit type not defined");
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
