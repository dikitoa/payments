package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionDebitMaintenance
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Debit Cards
 */
public class StrategyCommissionDebitMaintenance implements StrategyCommissionDebit {

	/*private Client owner;
	private Card card;
	
	public StrategyCommissionDebitMaintenance(Client owner, Card card){
		this.card = card;
		this.owner = owner;
	}*/

	@Override
	public float calculateCommission() {
		/*if (this.owner.getAge() > 26)
			return 15;
		else*/
			return 0;
	}

}
