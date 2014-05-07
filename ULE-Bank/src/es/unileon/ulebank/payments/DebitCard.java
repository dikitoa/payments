package es.unileon.ulebank.payments;

import java.io.IOException;
import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.TransactionType;
import es.unileon.ulebank.strategy.StrategyCommissionDebitEmission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

/**
 * @author Israel
 * Clase que representa una tarjeta de Debito
 */
public class DebitCard extends Card {
	
	private Account account;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param owner
	 * @param account
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 * @param limitDebit
	 * @throws NumberFormatException
	 * @throws CommissionException
	 * @throws IOException
	 */
	public DebitCard(CardHandler cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			float commissionEmission, float commissionMaintenance, float commissionRenovate, double limitDebit) throws NumberFormatException, CommissionException, IOException {
		super(cardId, CardType.DEBIT,
				buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				new StrategyCommissionDebitEmission(commissionEmission), 
				new StrategyCommissionDebitMaintenance(owner, commissionMaintenance), 
				new StrategyCommissionDebitRenovate(commissionRenovate),
				limitDebit);
		this.account = account;
	}
	
	/**
	 * Method that makes the payment
	 * @param receiverAccount Account which receives the money from the card
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException
	 */
	public void makePayment(Account receiverAccount, double quantity, String payConcept) throws PaymentException{
		if (this.account.getBalance() + this.getLimitDebit() >= quantity ){
			this.account.setBalance(this.account.getBalance() - quantity);
			receiverAccount.setBalance(receiverAccount.getBalance() + quantity);
			this.addTransaction(new GenericTransaction(quantity, new Date(), payConcept, TransactionType.PAYMENT));
		}
		else throw new PaymentException("Debit Card has not the balance necessary.");
	}
}
