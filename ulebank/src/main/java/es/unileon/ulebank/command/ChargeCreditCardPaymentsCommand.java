package es.unileon.ulebank.command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.CardTransaction;
import es.unileon.ulebank.history.TransactionException;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.taskList.Task;
import es.unileon.ulebank.taskList.TaskList;

/**
 * Charge Credit Card Payments Command Class
 * @author Rober dCR
 * @date 15/05/2014
 * @brief Class that implements the command which charges the effective date the amount of the purchases
 * taken with a credit card
 */
public class ChargeCreditCardPaymentsCommand implements Command {

	/**
	 * Logger Class
	 */
	private static final Logger LOG = Logger.getLogger(ChargeCreditCardPaymentsCommand.class.getName());
	/**
	 * Constant to search in card.properties
	 */
	private final String UNDO_PROPERTY = "undo_credit_card_command_concept";
	/**
	 * Constant to search in card.properties
	 */
	private final String EXECUTE_PROPERTY = "credit_card_command_concept";
	/**
	 * String for add in the concept when makes the undo method
	 */
	private String undoConcept;
	/**
	 * String for add in the concept when makes the execute method
	 */
	private String executeConcept;
	/**
	 * Command Identifier
	 */
	private Handler id;
	/**
	 * Card which makes the payment
	 */
	private CreditCard card;
	/**
	 * List which do and store the task
	 */
	private TaskList transactionTask;
	/**
	 * Date when is charge the amount of the purchases done by a credit caard
	 */
	private Date effectiveDate;
	
	/**
	 * Class constructor
	 * @param id
	 * @param card
	 * @param effectiveDate
	 * @throws IOException 
	 */
	public ChargeCreditCardPaymentsCommand(String id, CreditCard card, Date effectiveDate) throws IOException{
		this.id = new CommandHandler(id);
		this.card = card;
		this.transactionTask = new TaskList();
		this.effectiveDate = effectiveDate;
		Task chargePurchases = new Task(effectiveDate, this);
		this.transactionTask.addTask(chargePurchases);
		this.setUndoConcept();
		this.setExecuteConcept();
	}
	
	@Override
	public void execute() throws InvalidFeeException, PaymentException,
			TransactionException, TransferException {
		try{
			this.card.getAccount().doWithdrawal(new CardTransaction(this.card.getAmount(this.effectiveDate),this.effectiveDate, this.executeConcept + this.card.getId().toString()));
		} catch (TransactionException e) {
			LOG.info(e.getMessage());
			throw new TransactionException("Can't done transaction of charge amount of purchases with credit card " + this.card.getId().toString());
		}
	}

	@Override
	public void undo() throws TransferException, TransactionException,
			IOException {
		try{
			this.card.getAccount().doDeposit(new CardTransaction(this.card.getAmount(this.effectiveDate),this.effectiveDate, this.undoConcept + this.card.getId().toString()));
		} catch (TransactionException e) {
			LOG.info(e.getMessage());
			throw new TransactionException("Can't done transaction of repayment the amount of purchases with credit card " + this.card.getId().toString());
		}
	}

	@Override
	public void redo() throws PaymentException, TransactionException {
		try{
			this.card.getAccount().doWithdrawal(new CardTransaction(this.card.getAmount(this.effectiveDate),this.effectiveDate, this.executeConcept + this.card.getId().toString()));
		} catch (TransactionException e) {
			LOG.info(e.getMessage());
			throw new TransactionException("Can't done transaction of charge amount of purchases with credit card " + this.card.getId().toString());
		}
	}

	@Override
	public Handler getId() {
		return this.id;
	}
	
	/**
	 * Setter of undoConcept
	 * @throws IOException 
	 */
	private void setUndoConcept() throws IOException{
		try {
			Properties commissionProperty = new Properties();
			commissionProperty.load(new FileInputStream("src/es/unileon/ulebank/properties/card.properties"));
			
			/*Obtain the paramentes in card.properties*/
			this.undoConcept = commissionProperty.getProperty(this.UNDO_PROPERTY);
		}
		catch(FileNotFoundException e){
			throw new FileNotFoundException("File card.properties not found");
		}catch (IOException e2) {
			throw new IOException("Fail in card.properties when try open or close file.");
		}
	}
	
	/**
	 * Setter of executeConcept
	 * @throws IOException 
	 */
	private void setExecuteConcept() throws IOException{
		try {
			Properties commissionProperty = new Properties();
			commissionProperty.load(new FileInputStream("src/es/unileon/ulebank/properties/card.properties"));
			
			/*Obtain the parametres in card.properties*/
			this.executeConcept = commissionProperty.getProperty(this.EXECUTE_PROPERTY);
		}
		catch(FileNotFoundException e){
			throw new FileNotFoundException("File card.properties not found");
		}catch (IOException e2) {
			throw new IOException("Fail in card.properties when try open or close file.");
		}
	}

}
