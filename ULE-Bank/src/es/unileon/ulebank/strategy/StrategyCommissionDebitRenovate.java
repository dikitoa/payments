package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionDebitRenovate
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Debit Cards
 */
public class StrategyCommissionDebitRenovate implements StrategyCommissionDebit {

	private float quantity;
	
	/**
	 * Class constructor
	 * @param quantity
	 */
	public StrategyCommissionDebitRenovate(float quantity){
		this.quantity = quantity;
	}
	
	@Override
	public float calculateCommission() {
		return this.quantity;
	}

	

}
