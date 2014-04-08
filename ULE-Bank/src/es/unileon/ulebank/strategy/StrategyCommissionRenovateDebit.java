package es.unileon.ulebank.strategy;

/**
 * @class StrategyCommissionRenovateDebit
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Debit Cards
 */
public class StrategyCommissionRenovateDebit implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {
		return 20;
	}

	@Override
	public float calculateCommission(float interest, float quantity) {
		// Not necessary in this Strategy
		return 0;
	}

	

}
