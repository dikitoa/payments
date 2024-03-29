package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.exceptions.PaymentException;

/**
 * Payment Command Class
 * 
 * @author Rober dCR
 * @date 12/05/2014
 * @brief Class that implements the command for make a payment
 */
public class PaymentCommand implements Command {
	/**
	 * Logger Class
	 */
	private static final Logger LOG = Logger.getLogger(PaymentCommand.class
			.getName());
	/**
	 * Command Identifier
	 */
	private Handler id;
	/**
	 * Card which makes the payment
	 */
	private Cards card;
	/**
	 * Amount of the payment
	 */
	private double amount;
	/**
	 * Concept of the payment
	 */
	private String concept;
	/**
	 * Variable para saber si el comando ha sido ejecutado o no
	 */
	private boolean executed = false;
	/**
	 * Variable para saber si el comando ha sido deshecho o no
	 */
	private boolean undone = false;

	/**
	 * Class constructor
	 * 
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 * @param dniReceiver
	 * @param accountReceiver
	 * @param amount
	 * @param concept
	 * @param type
	 * @throws CommandException
	 */
	public PaymentCommand(Cards card, double amount, String concept)
			throws CommandException {
		this.id = new CommandHandler(card.getGenericHandler());
		this.card = card;
		if (amount != 0.00) {
			this.amount = amount;
		} else {
			LOG.log(Level.SEVERE, "Amount neutral.");
			throw new CommandException("Amount neutral.");
		}
		this.concept = concept;
	}

	/**
	 * Method to execute payment
	 * 
	 * @throws CommandException
	 */
	@Override
	public void execute() throws CommandException {
		try {
			// Make the payment by the type of the card
			this.card.makeTransaction(this.amount, this.concept);
			this.executed = true;
		} catch (TransactionException e) {
			LOG.log(Level.SEVERE, e.getMessage());
			throw new TransactionException(e.getMessage());
		} catch (PaymentException e) {
			LOG.log(Level.SEVERE, e.getMessage());
			throw new CommandException(e.getMessage());
		}

	}

	/**
	 * Method to undo payment
	 * 
	 * @throws PaymentException
	 */
	@Override
	public void undo() throws CommandException, PaymentException {
		if (this.executed) {
			try {
				// Make the payment by the type of the card
				this.card.makeTransaction(-this.amount, "Return payment from "
						+ this.concept);
				this.undone = true;
			} catch (TransactionException e) {
				LOG.log(Level.SEVERE, e.getMessage());
				throw new TransactionException(e.getMessage());
			}
		} else {
			LOG.log(Level.SEVERE,
					"Can't undo because command has not executed yet.");
			throw new CommandException(
					"Can't undo because command has not executed yet.");
		}
	}

	/**
	 * Method to redo payment
	 * 
	 * @throws CommandException
	 */
	@Override
	public void redo() throws PaymentException, TransactionException,
			CommandException {
		if (this.undone) {
			try {
				// Make the payment by the type of the card
				this.card.makeTransaction(this.amount, this.concept);
				this.undone = false;
			} catch (TransactionException e) {
				LOG.severe(e.getMessage());
				throw new TransactionException(e.getMessage());
			}
		} else {
			LOG.severe("Can't undo because command has not undoned yet.");
			throw new CommandException(
					"Can't undo because command has not undoned yet.");
		}

	}

	/**
	 * Method that returns command ID
	 * 
	 * @return command id
	 */
	@Override
	public Handler getID() {
		return this.id;
	}

}
