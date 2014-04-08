package es.unileon.ulebank.strategy;

//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionShopBuy
 * @author Rober dCR
 * @date 08/04/2014
 * @brief Class that obtain Commision of payment make by TPV
 */
public class StrategyCommissionShopBuy implements StrategyCommissionShop {

	private float quantity;
	private float interest;
	
	public StrategyCommissionShopBuy(float quantity, float interest){
		this.quantity = quantity;
		this.interest = interest;
	}
	
	@Override
	public float calculateCommission() {		
		return this.interest * quantity;
	}

}
