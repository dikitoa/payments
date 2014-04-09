package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionMaintenanceRevolvingTest {

	StrategyCommissionRevolvingMaintenance comision = new StrategyCommissionRevolvingMaintenance();
	
	@Test
	public void testCalculateCommission() {
		int ownerage = 18;
		
		assertEquals(0, comision.calculateCommission(ownerage));
	}

}
