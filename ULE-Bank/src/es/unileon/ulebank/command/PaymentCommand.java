package es.unileon.ulebank.command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.PurseCard;
import es.unileon.ulebank.payments.RevolvingCard;
import es.unileon.ulebank.payments.Transfer;

/**
 * Payment Command Class
 * @author Rober dCR
 * @date 12/05/2014
 * @brief Class that implements the command for make a payment
 */
public class PaymentCommand implements Command {
	/**
	 * Logger Class
	 */
	private static final Logger LOG = Logger.getLogger(ModifyPinCommand.class.getName());
	private final String UNDO_PROPERTY = "concept_undo_payment";
	/**
	 * String for add in the concept when makes the undo method
	 */
	private String undoConcept;
	/**
	 * Command Identifier
	 */
	private Handler id;
	/**
	 * Card Identifier
	 */
	private Handler cardId;
	/**
	 * Card which makes the payment
	 */
	private Card card;
	/**
	 * Account which sends the payment
	 */
	private Account accountSender;
	/**
	 * Account which receives the payment
	 */
	private Account accountReceiver;
	/**
	 * Amount of the payment
	 */
	private double amount;
	/**
	 * Concept of the payment
	 */
	private String concept;
	/**
	 * Type of the Card
	 */
	private CardType type;
	
	/**
	 * Class constructor
	 * @param cardId
	 * @param office
	 * @param dni
	 * @param accountHandler
	 * @param dniReceiver
	 * @param accountReceiver
	 * @param amount
	 * @param concept
	 * @param type
	 */
	public PaymentCommand(Handler cardId, Office office, Handler dni, Handler accountHandler, Handler dniReceiver, Handler accountReceiver, double amount, String concept, CardType type) {
		try {
			this.id = new CommandHandler(cardId);
			this.cardId = cardId;
			this.accountSender = office.searchClient((DNIHandler) dni).searchAccount((AccountHandler) accountHandler);
			this.accountReceiver = office.searchClient((DNIHandler) dniReceiver).searchAccount((AccountHandler) accountReceiver);
			this.amount = amount;
			this.concept = concept;
			this.type = type;
		} catch (ClientNotFoundException e) {
			LOG.info("Client with dni " + dni.toString() + " is not found");
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
	}
	
	@Override
	public void execute() throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException {
		try {
			//Search the account for that card
			this.card = accountSender.searchCard((CardHandler) cardId);
			//Make the payment by the type of the card
			this.payByType(type);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
		
	}

	@Override
	public void undo() throws TransferException, es.unileon.ulebank.history.TransactionException {
		try {
			//Make the transfer for revert the payment
			Transfer revertPayment = new Transfer(accountReceiver, accountSender, amount);
			this.setUndoConcept();
			revertPayment.makeTransfer(this.undoConcept + this.cardId.toString());
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
		
	}

	@Override
	public void redo() throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException {
		try {
			//Make the payment by the type of the card
			this.payByType(this.type);
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
		
	}

	@Override
	public Handler getId() {
		return this.id;
	}
	
	/**
	 * Setter of undoConcept
	 */
	private void setUndoConcept(){
		try {
			Properties commissionProperty = new Properties();
			commissionProperty.load(new FileInputStream("src/es/unileon/ulebank/properties/card.properties"));
			
			/**Obtain the paramentes in card.properties*/
			this.undoConcept = commissionProperty.getProperty(this.UNDO_PROPERTY);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Method that makes the payment by the card type
	 * @param type of the card
	 * @throws PaymentException
	 * @throws TransactionException
	 * @throws es.unileon.ulebank.history.TransactionException
	 */
	private void payByType(CardType type) throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException{
		switch (this.type){
		case CREDIT:
			((CreditCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
			break;
		case DEBIT:
			((DebitCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
			break;
		case PURSE:
			((PurseCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
			break;
		case REVOLVING:
			((RevolvingCard) this.card).makeTransaction(this.accountReceiver, this.amount, this.concept);
			break;
		default:
			break;
		}
	}

}
