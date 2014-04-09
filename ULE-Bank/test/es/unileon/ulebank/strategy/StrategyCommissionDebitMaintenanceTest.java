package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Marta
 * 
 * Comprobamos que la comision para esta tarjeta siempre es 0
 */

public class StrategyCommissionDebitMaintenanceTest {

	StrategyCommissionDebitMaintenance comision = new StrategyCommissionDebitMaintenance();
	
	
	
	@Test
	public void testCalculateCommission() {
		
		assertEquals(0, comision.calculateCommission(), 0);
	}

	


}
