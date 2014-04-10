package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionRevolvingRenovate
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Revolving Cards
 */
public class StrategyCommissionRevolvingRenovate implements StrategyCommissionRevolving {

	private Client owner;
	private Card card;
	private float quantity;
	
	/**
	 * Class constructor
	 * @param owner
	 * @param card
	 * @param quantity
	 */
	public StrategyCommissionRevolvingRenovate(Client owner, Card card, float quantity){
		this.card = card;
		this.owner = owner;
		this.quantity = quantity;
	}
	
	@Override
	public float calculateCommission() {
		return this.quantity;
	}



}
