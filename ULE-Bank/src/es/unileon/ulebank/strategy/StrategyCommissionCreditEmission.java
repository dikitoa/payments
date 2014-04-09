package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionCreditEmission
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Emission in Credit Cards
 */
public class StrategyCommissionCreditEmission implements StrategyCommissionCredit {

	//private Client owner;
	private Card card;
	private float quantity;
	private final float DEFAULT_COMMISSION = 0;
	/**
	 * Class constructor
	 * @param owner
	 * @param card
	 * @param quantity
	 */
	public StrategyCommissionCreditEmission(/*Client owner, */Card card, float quantity){
		this.card = card;
		//this.owner = owner;
		this.quantity = quantity;
	}
	
	@Override
	public float calculateCommission() {
		return this.DEFAULT_COMMISSION + this.quantity;
	}



}
