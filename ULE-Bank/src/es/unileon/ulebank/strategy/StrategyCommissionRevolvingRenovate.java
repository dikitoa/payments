package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionRevolvingRenovate
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Revolving Cards
 */
public class StrategyCommissionRevolvingRenovate implements StrategyCommissionRevolving {

	/*private Client owner;
	private Card card;
	
	public StrategyCommissionRevolvingRenovate(Client owner, Card card){
		this.card = card;
		this.owner = owner;
	}*/
	
	@Override
	public float calculateCommission() {
		return 0;
	}



}
