package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionDebitMaintenance
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Debit Cards
 */
public class StrategyCommissionDebitMaintenance implements StrategyCommissionDebit {

	private Client owner;
	private Card card;
	private float quantity;
	private final int MAXIMUM_AGE = 26;
	private final float DEFAULT_COMMISSION = 15;
	
	/**
	 * Class constructor
	 * @param owner
	 * @param card
	 * @param quantity
	 */
	public StrategyCommissionDebitMaintenance(Client owner, Card card, float quantity){
		this.card = card;
		this.owner = owner;
		this.quantity = quantity;
	}

	@Override
	public float calculateCommission() {
		if (this.owner.getAge() >= this.MAXIMUM_AGE)
			return this.DEFAULT_COMMISSION + this.quantity;
		else
			return this.quantity;
	}

}
