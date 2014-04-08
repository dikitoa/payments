package es.unileon.ulebank.strategy;

/**
 * @class StrategyCommissionMaintenanceDebit
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Debit Cards
 */
public class StrategyCommissionMaintenanceDebit implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {
		
		if (ownerAge > 26)
			return 15;
		else
			return 0;
	}

	@Override
	public float calculateCommission(float interest, float quantity) {
		// Not necessary in this Strategy
		return 0;
	}

}
