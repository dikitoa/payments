package es.unileon.UleBank.Payments;

/**
 * @class StrategyCommissionEmissionDebit
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Emission in Debit Cards
 */
public class StrategyCommissionEmissionDebit implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {
		return 0;
	}



}
