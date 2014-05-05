package es.unileon.ulebank.payments;

import java.io.IOException;

import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.strategy.StrategyCommissionDebitEmission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

/**
 * @author Israel
 * Clase que representa una tarjeta de Debito
 */
public class DebitCard extends Card {
	
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
	 * @throws NumberFormatException
	 * @throws CommissionException
	 * @throws IOException
	 */
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
