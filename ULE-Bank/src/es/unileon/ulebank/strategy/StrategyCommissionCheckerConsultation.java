package es.unileon.ulebank.strategy;


/**
 * @class StrategyCommissionCheckerConsultation
 * @author Rober dCR
 * @date 20/03/2014
 * @brief Class that obtain Commision of Consultation in Checker
 */
public class StrategyCommissionCheckerConsultation implements
		StrategyCommissionChecker {

	private float commission; ; //Commission quantity
	
	public StrategyCommissionCheckerConsultation(float commission){
		this.commission = commission;
	}
	
	@Override
	public float calculateCommission() {
		return this.commission;
	}




}
