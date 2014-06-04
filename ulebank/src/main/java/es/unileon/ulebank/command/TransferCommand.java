package es.unileon.ulebank.command;

import org.apache.log4j.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.history.TransactionException;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Transfer;

/**
 * Transfer Command Class
 * @author Rober dCR
 * @date 13/05/2014
 * @brief Command with which can make a transfer between two accounts
 */
public class TransferCommand implements Command {
	/**
	 * Class Logger
	 */
	private static final Logger LOG = Logger.getLogger(ModifyBuyLimitCommand.class.getName());
	/**
	 * Identifier of the command
	 */
	private Handler id;
	/**
	 * Account which makes the transfer
	 */
	private Account accountSender;
	/**
	 * Account which receives the amount
	 */
	private Account accountReceiver;
	/**
	 * Quantity of the transfer
	 */
	private double amount;
	/**
	 * Concept of the transfer
	 */
	private String concept;
	/**
	 * Prove if we can undo the command
	 */
	private boolean undo = false;
	/**
	 * Prove if we can redo the command
	 */
	private boolean redo = false;
	
	/**
	 * Class constructor 
	 * @param ofice
	 * @param accountSender
	 * @param dniSender
	 * @param accountReceiver
	 * @param dniReceiver
	 * @param amount
	 */
	public TransferCommand(Office office, String accountSender, String dniSender, String accountReceiver, String dniReceiver, double amount, String concept){
		try {
			this.id = new CommandHandler(accountSender);
			this.accountSender = office.searchClient(dniSender).searchAccount(accountSender);
			this.accountReceiver = office.searchClient(dniReceiver).searchAccount(accountReceiver);
			this.amount = amount;
			this.concept = concept;
		} catch (ClientNotFoundException e) {
			LOG.info("The client that is not found.");
		} catch (NullPointerException e) {
			LOG.info(e.getMessage());
		}
	}
	
	@Override
	public void execute() throws TransferException, TransactionException {
		try {
			Transfer transfer = new Transfer(this.accountSender, this.accountReceiver, this.amount);
			transfer.make(this.concept);
			this.undo = true;
		} catch (TransferException e) {
			LOG.info(e.getMessage());
		} catch (TransactionException e) {
			LOG.info(e.getMessage());
		}
	}

	@Override
	public void undo() {
		if (this.undo) {
			try {
				Transfer transfer = new Transfer(this.accountReceiver, this.accountSender, this.amount);
				transfer.make("Return transfer " + this.concept);
				this.redo = true;
				this.undo = false;
			} catch (TransferException e) {
				LOG.info(e.getMessage());
			} catch (TransactionException e) {
				LOG.info(e.getMessage());
			}
		}	
	}

	@Override
	public void redo() {
		if (this.redo) {
			try {
				Transfer transfer = new Transfer(this.accountSender, this.accountReceiver, this.amount);
				transfer.make(this.concept);
				this.undo = true;
				this.redo = false;
			} catch (TransferException e) {
				LOG.info(e.getMessage());
			} catch (TransactionException e) {
				LOG.info(e.getMessage());
			}
		}
		
	}

	@Override
	public Handler getId() {
		return this.id;
	}

}
