package es.unileon.ulebank.strategy;

/**
 * @class StrategyCommissionCreditRenovate
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Debit Cards
 */

public class StrategyCommissionCreditRenovate implements StrategyCommissionCredit {

	private float quantity; ; //Commission quantity

	/**
	 * Class constructor
	 * @param quantity
	 */
	public StrategyCommissionCreditRenovate(float quantity){
		this.quantity = quantity;
	}

	@Override
	public float calculateCommission() {
		return this.quantity;
	}

}
