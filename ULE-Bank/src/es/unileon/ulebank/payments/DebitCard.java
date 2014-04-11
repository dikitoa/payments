package es.unileon.ulebank.payments;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommission;

/**
 * @author Israel
 */
public class DebitCard extends Card {
	
	public DebitCard(CardHandler cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			StrategyCommission commissionEmission, StrategyCommission commissionMaintenance, 
			StrategyCommission commissionRenovate, double limitDebit) {
		super(cardId, owner, account, CardType.DEBIT,
				buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				commissionEmission, commissionMaintenance, commissionRenovate,
				limitDebit);
	}
}
