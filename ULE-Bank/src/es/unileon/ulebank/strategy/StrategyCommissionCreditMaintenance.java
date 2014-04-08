package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionCreditMaintenance
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Credit Cards
 */
public class StrategyCommissionCreditMaintenance implements StrategyCommissionCredit {

	/*private Client owner;
	private Card card;
	
	public StrategyCommissionCreditMaintenance(Client owner, Card card){
		this.card = card;
		this.owner = owner;
	}*/

	@Override
	public float calculateCommission() {
		return 25;
	}


}
