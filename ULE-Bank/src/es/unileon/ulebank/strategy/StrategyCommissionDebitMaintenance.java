package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Client;

/**
 * @class StrategyCommissionDebitMaintenance
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Debit Cards
 */
public class StrategyCommissionDebitMaintenance implements StrategyCommissionDebit {

	private Client owner;
	private float quantity;
	private final int MAXIMUM_AGE = 26;
	private final float DEFAULT_COMMISSION = 15;
	
	/**
	 * Class constructor
	 * @param owner
	 * @param quantity
	 */
	public StrategyCommissionDebitMaintenance(Client owner, float quantity){
		this.owner = owner;
		this.quantity = quantity;
	}

	@Override
	public float calculateCommission() {
		if (this.owner.getAge() >= this.MAXIMUM_AGE)
			return this.DEFAULT_COMMISSION;
		else
			return this.quantity;
	}

}
