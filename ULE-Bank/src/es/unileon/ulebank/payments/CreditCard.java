package es.unileon.ulebank.payments;

import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;

/**
 * @author Israel
 */
public class CreditCard extends Card {

	public CreditCard(CardHandler cardId, Client owner, Account account, double buyLimitDiary, double buyLimitMonthly, 
			double cashLimitDiary, double cashLimitMonthly, float commissionEmission, 
			float commissionMaintenance, float commissionRenovate, double limitDebit) throws CommissionException {
		super(cardId, CardType.CREDIT,
				buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				new StrategyCommissionCreditEmission(commissionEmission),
				new StrategyCommissionCreditMaintenance(commissionMaintenance),
				new StrategyCommissionCreditRenovate(commissionRenovate),
				limitDebit);
	}
}
