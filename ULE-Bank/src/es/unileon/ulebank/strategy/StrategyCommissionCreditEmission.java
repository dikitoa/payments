package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionCreditEmission
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Emission in Credit Cards
 */
public class StrategyCommissionCreditEmission implements StrategyCommissionCredit {

	private float quantity; //Commission quantity

	/**
	 * Class constructor
	 * @param quantity
	 */
	public StrategyCommissionCreditEmission(float quantity){
		this.quantity = quantity;
	}
	
	@Override
	public float calculateCommission() {
		return this.quantity;
	}



}
