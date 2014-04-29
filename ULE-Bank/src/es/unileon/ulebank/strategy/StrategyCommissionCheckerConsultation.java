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
	private float commission;
	
	public StrategyCommissionCheckerConsultation(Card card, float commission){
		this.card = card;
		this.commission = commission;
	}
	
	@Override
	public float calculateCommission() {
		return this.commission;
	}




}
