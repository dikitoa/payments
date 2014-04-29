package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionRevolvingRenovate
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Revolving Cards
 */
public class StrategyCommissionRevolvingRenovate implements StrategyCommissionRevolving {

	private float quantity;
	
	/**
	 * Class constructor
	 * @param quantity
	 */
	public StrategyCommissionRevolvingRenovate(float quantity){
		this.quantity = quantity;
	}
	
	@Override
	public float calculateCommission() {
		return this.quantity;
	}

}
