package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionCreditRenovate
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Renovate (substitution, theft or damage) in Debit Cards
 */

public class StrategyCommissionCreditRenovate implements StrategyCommissionCredit {

	/*private Client owner;
	private Card card;
	
	public StrategyCommissionCreditRenovate(Client owner, Card card){
		this.card = card;
		this.owner = owner;
	}*/

	@Override
	public float calculateCommission() {
		return 30;
	}

}
