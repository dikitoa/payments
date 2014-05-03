package es.unileon.ulebank.payments;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommission;

public class RevolvingCard extends Card {
	private float interest = 1;
	
	public RevolvingCard(CardHandler cardId, Client owner, Account account,
			CardType type, double buyLimitDiary, double buyLimitMonthly,
			double cashLimitDiary, double cashLimitMonthly,
			StrategyCommission commissionEmission,
			StrategyCommission commissionMaintenance,
			StrategyCommission commissionRenovate, double limitDebit) {
		super(cardId, type, buyLimitDiary, buyLimitMonthly,
				cashLimitDiary, cashLimitMonthly, commissionEmission,
				commissionMaintenance, commissionRenovate, limitDebit);
		// TODO Auto-generated constructor stub
	}

	
	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}
}
