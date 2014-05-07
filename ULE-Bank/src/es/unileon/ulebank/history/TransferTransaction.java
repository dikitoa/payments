package es.unileon.ulebank.history;

import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.TransferException;

public class TransferTransaction extends GenericTransaction{
	
	private Account senderAccount; //Account from transfer the money
	private Account receiverAccount; //Account which receives the money

	public TransferTransaction(double amount, Date date, String subject,
			Enum<TransactionType> type, Account senderAccount, Account receiverAccount) throws TransferException {
		super(amount, date, subject, type);
		
		if (!senderAccount.equals(receiverAccount)){
			this.senderAccount = senderAccount;
			this.receiverAccount = receiverAccount;
		}
		else
			throw new TransferException("Sender Account number and Receiver Account number are the same.");
	}

}
