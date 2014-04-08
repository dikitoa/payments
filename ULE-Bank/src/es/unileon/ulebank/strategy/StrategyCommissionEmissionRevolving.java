package es.unileon.ulebank.strategy;

/**
 * @class StrategyCommissionEmissionRevolving
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Emission in Revolving Cards
 */
public class StrategyCommissionEmissionRevolving implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {
		return 0;
	}

	@Override
	public float calculateCommission(float interest, float quantity) {
		// Not necessary in this Strategy
		return 0;
	}
	
}
