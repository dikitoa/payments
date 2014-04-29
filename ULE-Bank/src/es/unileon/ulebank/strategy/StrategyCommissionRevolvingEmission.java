package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionRevolvingEmission
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Emission in Revolving Cards
 */
public class StrategyCommissionRevolvingEmission implements StrategyCommissionRevolving {

	private float quantity;
	
	/**
	 * Class constructor
	 * @param quantity
	 */
	public StrategyCommissionRevolvingEmission(float quantity){
		this.quantity = quantity;
	}

	@Override
	public float calculateCommission() {
		return this.quantity;
	}
	
}
