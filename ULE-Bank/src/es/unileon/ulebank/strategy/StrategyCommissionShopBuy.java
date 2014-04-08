package es.unileon.ulebank.strategy;

//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionShopBuy
 * @author Rober dCR
 * @date 08/04/2014
 * @brief Class that obtain Commision of payment make by TPV
 */
public class StrategyCommissionShopBuy implements StrategyCommissionShop {

	//private Client shop;
	private float quantity;
	private float interest;
	
	/*public StrategyCommissionShopBuy(Client shop, float quantity, float interest){
		this.shop = shop;
		this.quantity = quantity;
		this.interest = interest;
	}*/
	
	@Override
	public float calculateCommission() {		
		return this.interest * quantity;
	}

}
