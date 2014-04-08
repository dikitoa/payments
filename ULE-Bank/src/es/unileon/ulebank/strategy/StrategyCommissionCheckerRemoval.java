package es.unileon.ulebank.strategy;

/**
 * @class StrategyCommissionCheckerRemoval
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Removal in Checker
 */
public class StrategyCommissionCheckerRemoval implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float calculateCommission(float interest, float quantity) {
		// Not necessary in this Strategy
		return 0;
	}

}
