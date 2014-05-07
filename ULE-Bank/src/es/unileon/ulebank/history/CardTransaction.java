package es.unileon.ulebank.history;

import java.util.Date;

import es.unileon.ulebank.account.Account;

public class CardTransaction extends GenericTransaction{

	private Account receiverAccount;
	private Account senderAccount;
	
	public CardTransaction(double amount, Date date, String subject,
			Enum<TransactionType> type, Account receiverAccount, Account senderAccount) {
		super(amount, date, subject, type);

		this.receiverAccount = receiverAccount;
		this.senderAccount = senderAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public Account getSederAccount() {
		return senderAccount;
	}

}
