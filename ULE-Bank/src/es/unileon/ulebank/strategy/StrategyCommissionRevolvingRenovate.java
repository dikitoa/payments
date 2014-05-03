package es.unileon.ulebank.strategy;

import es.unileon.ulebank.exceptions.CommissionException;


/**
 * @class StrategyCommissionRevolvingRenovate
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Revolving Cards
 */
public class StrategyCommissionRevolvingRenovate implements StrategyCommissionRevolving {

	private float quantity; //Commission establish by the employee
	
	/**
	 * Class constructor
	 * @param quantity
	 * @throws CommissionException 
	 */
	public StrategyCommissionRevolvingRenovate(float quantity) throws CommissionException{
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
