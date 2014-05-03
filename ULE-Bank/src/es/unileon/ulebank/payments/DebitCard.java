package es.unileon.ulebank.payments;

import java.io.IOException;

import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommissionDebitEmission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

/**
 * @author Israel
 */
public class DebitCard extends Card {
	
	public DebitCard(CardHandler cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			float commissionEmission, float commissionMaintenance, float commissionRenovate, double limitDebit) throws NumberFormatException, CommissionException, IOException {
		super(cardId, CardType.DEBIT,
				buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				new StrategyCommissionDebitEmission(commissionEmission), 
				new StrategyCommissionDebitMaintenance(owner, commissionMaintenance), 
				new StrategyCommissionDebitRenovate(commissionRenovate),
				limitDebit);
	}
}
