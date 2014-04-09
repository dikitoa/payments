package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionCreditMaintenance
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Credit Cards
 */
public class StrategyCommissionCreditMaintenance implements StrategyCommissionCredit {

	private Client owner;
	private Card card;
	private float quantity;
	private final float DEFAULT_COMMISSION = 25;
	
	/**
	 * Class constructor
	 * @param card
	 * @param quantity
	 */
	public StrategyCommissionCreditMaintenance(Client owner, Card card, float quantity){
		this.card = card;
		this.owner = owner;
		this.quantity = quantity;
	}

	@Override
	public float calculateCommission() {
		return this.DEFAULT_COMMISSION + this.quantity;
	}


}
