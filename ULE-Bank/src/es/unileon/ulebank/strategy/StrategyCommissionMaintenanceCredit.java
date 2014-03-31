package es.unileon.ulebank.strategy;

/**
 * @class StrategyCommissionMaintenanceCredit
 * @author Rober dCR
 * @date 19/03/2014
 * @brief Class that obtain Commision of Maintenance in Credit Cards
 */
public class StrategyCommissionMaintenanceCredit implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {

		return 25;
		
	}

}
