package es.unileon.ulebank.payments;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommission;

/**
 * @author Israel
 */
public class CreditCard extends Card {

	public CreditCard(CardHandler cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			StrategyCommission commissionEmission, StrategyCommission commissionMaintenance, 
			StrategyCommission commissionRenovate, double limitDebit) {
		super(cardId, owner, account, CardType.CREDIT,
				buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				commissionEmission, commissionMaintenance, commissionRenovate,
				limitDebit);
	}
}
