package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionCreditMaintenance
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Credit Cards
 */
public class StrategyCommissionCreditMaintenance implements StrategyCommissionCredit {


	private float quantity; ; //Commission quantity
	
	/**
	 * Class constructor
	 * @param quantity
	 */
	public StrategyCommissionCreditMaintenance(float quantity){
		this.quantity = quantity;
	}

	@Override
	public float calculateCommission() {
		return this.quantity;
	}


}
