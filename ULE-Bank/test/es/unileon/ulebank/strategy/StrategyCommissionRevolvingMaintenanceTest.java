package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionRevolvingMaintenanceTest {

	/**
	 * @author Marta
	 * 
	 * Comprobamos que la comision para esta tarjeta siempre es 0
	 */
	
	StrategyCommissionRevolvingMaintenance comision;
	
	@Before
	public void SetUp(){
		
		comision  = new StrategyCommissionRevolvingMaintenance();
	}
	
	
	@Test
	public void testCalculateCommission() {
		
		assertEquals(0, comision.calculateCommission(),0);
	}

}
