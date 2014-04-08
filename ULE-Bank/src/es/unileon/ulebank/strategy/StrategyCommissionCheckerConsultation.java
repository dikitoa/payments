package es.unileon.ulebank.strategy;

import es.unileon.ulebank.payments.Card;

/**
 * @class StrategyCommissionCheckerConsultation
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Consultation in Checker
 */
public class StrategyCommissionCheckerConsultation implements
		StrategyCommissionChecker {

	private Card card;
	
	public StrategyCommissionCheckerConsultation(Card card){
		this.card = card;
	}
	
	@Override
	public float calculateCommission() {
		return 0;
	}




}
