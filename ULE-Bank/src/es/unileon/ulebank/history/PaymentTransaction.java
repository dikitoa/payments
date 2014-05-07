package es.unileon.ulebank.history;

import java.util.Date;

public class PaymentTransaction extends GenericTransaction{

	public PaymentTransaction(double amount, Date date, String subject,
			Enum<TransactionType> type) {
		super(amount, date, subject, type);
		// TODO Auto-generated constructor stub
	}

}
