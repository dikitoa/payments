package es.unileon.ulebank.payments;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.strategy.StrategyCommission;

public class RevolvingCard extends Card {
	private float interest = 1;
	
	/**
	 * Class Constructor 
	 * @param cardId
	 * @param owner
	 * @param account
	 * @param type
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 * @param limitDebit
	 */
	public RevolvingCard(Handler cardId, Client owner, Account account,
			CardType type, double buyLimitDiary, double buyLimitMonthly,
			double cashLimitDiary, double cashLimitMonthly,
			StrategyCommission commissionEmission,
			StrategyCommission commissionMaintenance,
			StrategyCommission commissionRenovate) {
		super(cardId, type, buyLimitDiary, buyLimitMonthly,
				cashLimitDiary, cashLimitMonthly, commissionEmission,
				commissionMaintenance, commissionRenovate);
	}

	/**
	 * Getter of the interest
	 * @return float
	 */
	public float getInterest() {
		return interest;
	}

	/**
	 * Setter of the interest
	 * @param interest
	 */
	public void setInterest(float interest) {
		this.interest = interest;
	}
	
	/**
	 * Method that makes the payment
	 * @param receiverAccount Account which receives the money from the card
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException
	 */
	public void makeTransaction(Account receiverAccount, double quantity, String payConcept) throws PaymentException{
		//TODO
	}
}
