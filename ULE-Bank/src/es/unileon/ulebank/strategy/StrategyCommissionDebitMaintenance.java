package es.unileon.ulebank.strategy;

import es.unileon.ulebank.exceptions.CommissionException;
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
	 * @throws CommissionException 
	 */
	public StrategyCommissionDebitMaintenance(Client owner, float quantity) throws CommissionException{
		this.owner = owner;
		if (quantity >= 0)
			this.quantity = quantity;
		else
			throw new CommissionException("Commission can't been negative.");
	}

	@Override
	public float calculateCommission() {
		if (this.owner.getAge() >= this.MAXIMUM_AGE)
			return this.DEFAULT_COMMISSION;
		else
			return this.quantity;
	}

}
