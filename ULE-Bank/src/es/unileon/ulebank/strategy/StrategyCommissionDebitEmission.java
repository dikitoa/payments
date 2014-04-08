package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;
//import es.unileon.ulebank.client.Client;

/**
 * @class StrategyCommissionDebitEmission
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Emission in Debit Cards
 */
public class StrategyCommissionDebitEmission implements StrategyCommissionDebit {

	/*private Client owner;
	private Card card;
	
	public StrategyCommissionDebitEmission(Client owner, Card card){
		this.card = card;
		this.owner = owner;
	}*/

	@Override
	public float calculateCommission() {
		return 0;
	}

}
