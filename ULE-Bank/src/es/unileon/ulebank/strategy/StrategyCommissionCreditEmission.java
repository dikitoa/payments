package es.unileon.ulebank.strategy;

import es.unileon.ulebank.exceptions.CommissionException;


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
	 * @throws CommissionException 
	 */
	public StrategyCommissionCreditEmission(float quantity) throws CommissionException{
		if (quantity >= 0)
			this.quantity = quantity;
		else
			throw new CommissionException("Commission can't been negative.");
	}
	
	@Override
	public float calculateCommission() {
		return this.quantity;
	}



}
