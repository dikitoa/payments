package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionMaintenanceRevolvingTest {

	StrategyCommissionMaintenanceRevolving comision = new StrategyCommissionMaintenanceRevolving();
	
	@Test
	public void testCalculateCommission() {
		int ownerage = 18;
		
		assertEquals(0, comision.calculateCommission(ownerage));
	}

}
