package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionRevolvingMaintenance
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Maintenance in Revolving Cards
 */
public class StrategyCommissionRevolvingMaintenance implements
		StrategyCommissionRevolving {

	private float quantity;
	
	/**
	 * Class constructor
	 * @param quantity
	 */
	public StrategyCommissionRevolvingMaintenance(float quantity){
		this.quantity = quantity;
	}
	
	@Override
	public float calculateCommission() {
		return this.quantity;
	}
}
