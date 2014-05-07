package es.unileon.ulebank.payments;

import java.util.Date;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.history.GenericTransaction;
import es.unileon.ulebank.history.TransactionType;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;

/**
 * @author Israel
 * Clase que representa la tarjeta de credito
 */
public class CreditCard extends Card {
	
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
	 * @throws CommissionException
	 */
	public CreditCard(CardHandler cardId, Client owner, Account account, double buyLimitDiary, double buyLimitMonthly, 
			double cashLimitDiary, double cashLimitMonthly, float commissionEmission, 
			float commissionMaintenance, float commissionRenovate, double limitDebit) throws CommissionException {
		super(cardId, CardType.CREDIT, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				new StrategyCommissionCreditEmission(commissionEmission),
				new StrategyCommissionCreditMaintenance(commissionMaintenance),
				new StrategyCommissionCreditRenovate(commissionRenovate), limitDebit);
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
		if (this.account.getBalance() >= quantity){
			this.account.setBalance(this.account.getBalance() - quantity);
			receiverAccount.setBalance(receiverAccount.getBalance() + quantity);
			this.addTransaction(new GenericTransaction(quantity, new Date(), payConcept, TransactionType.PAYMENT));
		}
		else throw new PaymentException("Credit Card has not the balance necessary.");
	}
}
