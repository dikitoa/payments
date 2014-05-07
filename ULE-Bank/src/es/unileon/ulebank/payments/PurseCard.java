package es.unileon.ulebank.payments;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.strategy.StrategyCommission;

/**
 * @author Israel
 */
public class PurseCard extends Card {

	public PurseCard(Handler cardId, Client owner, Account account,
			CardType type, double buyLimitDiary, double buyLimitMonthly,
			double cashLimitDiary, double cashLimitMonthly,
			StrategyCommission commissionEmission,
			StrategyCommission commissionMaintenance,
			StrategyCommission commissionRenovate) {
		super(cardId, type, buyLimitDiary, buyLimitMonthly,
				cashLimitDiary, cashLimitMonthly, commissionEmission,
				commissionMaintenance, commissionRenovate);
		// TODO Auto-generated constructor stub
	}
}
