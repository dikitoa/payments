package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionMaintenanceDebitTest {

	StrategyCommissionMaintenanceDebit comision = new StrategyCommissionMaintenanceDebit();
	int ownerage = 15;
	
	@Test
	public void testCalculateCommission() {
		
		
		assertEquals(15, comision.calculateCommission(ownerage));
		ownerage = 30;
		assertEquals(0, comision.calculateCommission(ownerage));
		
	}

}
