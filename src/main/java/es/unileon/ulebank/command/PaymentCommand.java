package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
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
    private static final Logger LOG = Logger.getLogger(PaymentCommand.class.getName());
    /**
     * Concept which add when makes the undo method
     */
    private static final String UNDO_CONCEPT = "Return payment from ";
    /**
     * String of incorrect undo error
     */
    private static final String ERROR_UNDO = "Can't undo because command has not undoned yet.";
    /**
     * String of incorrect redo error
     */
    private static final String ERROR_REDO = "Can't redo because command has not undoned yet.";
    /**
     * String of error when the amount is neutral
     */
    private static final String NEUTRAL_AMOUNT = "Amount neutral.";
    /**
     * Command Identifier
     */
    private final Handler id;
    /**
     * Card Identifier
     */
    private final Handler cardId;
    /**
     * Card which makes the payment
     */
    private Card card;
    /**
     * Account which sends the payment
     */
    private final Account accountSender;
    /**
     * Amount of the payment
     */
    private final double amount;
    /**
     * Concept of the payment
     */
    private final String concept;
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
    public PaymentCommand(Handler cardId, Office office, Handler dni,
            Handler accountHandler, double amount, String concept) throws CommandException {
        this.id = new CommandHandler(cardId);
        this.cardId = cardId;
        this.accountSender = office.searchClient(dni).searchAccount(
                accountHandler);
        if (amount != 0.00) {
            this.amount = amount;
        } else {
        	LOG.log(Level.SEVERE, PaymentCommand.NEUTRAL_AMOUNT);
        	throw new CommandException(PaymentCommand.NEUTRAL_AMOUNT);
        }
        this.concept = concept;
    }

    /**
     * Method to execute payment
     * @throws CommandException 
     */
    @Override
    public void execute() throws CommandException {
        try {
            // Search the account for that card
            this.card = this.accountSender.searchCard(this.cardId);
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
     * @throws PaymentException 
     */
    @Override
    public void undo() throws CommandException {
    	if (this.executed) {
    		try {
                // Make the payment by the type of the card
    			this.card.makeTransaction(-this.amount, PaymentCommand.UNDO_CONCEPT + this.concept);
                this.undone = true;
    		} catch (TransactionException e) {
    			 LOG.log(Level.SEVERE, e.getMessage());
    			throw new TransactionException(e.getMessage());
    		}
    	} else {
    		 LOG.log(Level.SEVERE, PaymentCommand.ERROR_UNDO);
    		throw new CommandException(PaymentCommand.ERROR_UNDO);
    	}
    }

    /**
     * Method to redo payment
     * @throws CommandException 
     */
    @Override
    public void redo() throws TransactionException, CommandException {
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
    		LOG.severe(PaymentCommand.ERROR_REDO);
    		throw new CommandException(PaymentCommand.ERROR_REDO);
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
