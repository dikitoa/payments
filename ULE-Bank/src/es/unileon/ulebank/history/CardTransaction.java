package es.unileon.ulebank.history;

import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.TransactionException;

/**
 * Transaction for the Card
 * @author Rober dCR
 * @date 8/05/2014
 */
public class CardTransaction extends GenericTransaction{

	/**
	 * Account which receives the amount in the transaction
	 */
	private Account receiverAccount;
	/**
	 * Account which emits the amount in the transaction
	 */
	private Account senderAccount;
	
	/**
	 * Class constructor
	 * @param amount
	 * @param date
	 * @param subject
	 * @param type
	 * @param senderAccount
	 * @param receiverAccount
	 * @throws TransactionException 
	 * @throws es.unileon.ulebank.history.TransactionException 
	 */
	public CardTransaction(double amount, Date date, String subject,
			Account senderAccount, Account receiverAccount) throws TransactionException, es.unileon.ulebank.history.TransactionException {
		super(amount, date, subject);

		this.receiverAccount = receiverAccount;
		this.senderAccount = senderAccount;
	}

	/**
	 * Getter ReceiverAccount
	 * @return ReceiverAccount
	 */
	public Account getReceiverAccount() {
		return receiverAccount;
	}

	/**
	 * Getter SederAccount
	 * @return SederAccount
	 */
	public Account getSederAccount() {
		return senderAccount;
	}

}
