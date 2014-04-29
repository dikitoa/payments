package es.unileon.ulebank.strategy;

/**
 * @class StrategyCommissionDebitEmission
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Emission in Debit Cards
 */
public class StrategyCommissionDebitEmission implements StrategyCommissionDebit {

	private float quantity; ; //Commission quantity

	/**
	 * Class constructor
	 * @param owner
	 * @param card
	 * @param quantity
	 */
	public StrategyCommissionDebitEmission(float quantity){
		this.quantity = quantity;
	}

	@Override
	public float calculateCommission() {
		return this.quantity;
	}

}
