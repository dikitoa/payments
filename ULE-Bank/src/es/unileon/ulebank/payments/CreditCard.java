package es.unileon.ulebank.payments;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommissionCreditEmission;
import es.unileon.ulebank.strategy.StrategyCommissionCreditMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionCreditRenovate;

/**
 * @author Israel
 * Clase que representa la tarjeta de credito
 */
public class CreditCard extends Card {
	
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
	}
}
