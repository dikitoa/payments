package es.unileon.ulebank.payments;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommission;

/**
 * @author Israel
 */
public class DebitCard extends Card {
	
	public DebitCard(CardHandler cardId, Client owner, Account account,
			float buyLimitDiary, float buyLimitMonthly, float cashLimitDiary, float cashLimitMonthly,
			StrategyCommission commissionEmission, StrategyCommission commissionMaintenance, 
			StrategyCommission commissionRenovate, float limitDebit) {
		super(cardId, owner, account, CardType.DEBIT,
				buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				commissionEmission, commissionMaintenance, commissionRenovate,
				limitDebit);
	}
}
