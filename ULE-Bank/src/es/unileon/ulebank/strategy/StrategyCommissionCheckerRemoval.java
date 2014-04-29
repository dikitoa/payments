package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionCheckerRemoval
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Removal in Checker
 */
public class StrategyCommissionCheckerRemoval implements StrategyCommission {

	private float commission; ; //Commission quantity
	
	public StrategyCommissionCheckerRemoval(float commission){
		this.commission = commission;
	}
	
	@Override
	public float calculateCommission() {
		return this.commission;
	}

}
