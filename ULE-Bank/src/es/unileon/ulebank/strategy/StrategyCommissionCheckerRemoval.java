package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;

/**
 * @class StrategyCommissionCheckerRemoval
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Removal in Checker
 */
public class StrategyCommissionCheckerRemoval implements StrategyCommission {

	private Card card;
	
	public StrategyCommissionCheckerRemoval(Card card){
		this.card = card;
	}
	
	@Override
	public float calculateCommission() {
		return 0;
	}



}
