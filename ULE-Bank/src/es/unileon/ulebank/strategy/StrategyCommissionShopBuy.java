package es.unileon.ulebank.strategy;

import es.unileon.ulebank.exceptions.CommissionException;

/**
 * @class StrategyCommissionShopBuy
 * @author Rober dCR
 * @date 08/04/2014
 * @brief Class that obtain Commission of payment make by TPV
 */
public class StrategyCommissionShopBuy implements StrategyCommissionShop {

	private float quantity; //Quantity of the purchase
	private float interest; //Interest establish by the bank
	
	public StrategyCommissionShopBuy(float quantity, float interest) throws CommissionException{
		if (quantity >= 0)
			this.quantity = quantity;
		else
			throw new CommissionException("Commission can't been negative.");
		if (interest >= 0)
			this.interest = interest;
		else
			throw new CommissionException("Interest can't been negative.");
		
	}
	
	@Override
	public float calculateCommission() {		
		return this.interest * quantity;
	}

}
