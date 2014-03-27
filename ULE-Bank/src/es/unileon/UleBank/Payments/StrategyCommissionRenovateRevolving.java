package es.unileon.UleBank.Payments;

/**
 * @class StrategyCommissionRenovateRevolving
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Revolving Cards
 */
public class StrategyCommissionRenovateRevolving implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {
		return 0;
	}

}
