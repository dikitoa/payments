package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionMaintenanceCreditTest {

	StrategyCommissionMaintenanceCredit comision = new StrategyCommissionMaintenanceCredit();
	
	
	@Test
	public void testCalculateCommission() {

		int ownerage = 18;
		
		assertEquals(25, comision.calculateCommission(ownerage));
		
	}

}
