package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Marta
 * 
 * Comprobamos que la comision para esta tarjeta siempre es 25
 */

public class StrategyCommissionCreditMaintenanceTest {

	StrategyCommissionCreditMaintenance comision = new StrategyCommissionCreditMaintenance();
	
	
	@Test
	public void testCalculateCommission() {
		
		assertEquals(25, comision.calculateCommission(), 0);
	}

}
