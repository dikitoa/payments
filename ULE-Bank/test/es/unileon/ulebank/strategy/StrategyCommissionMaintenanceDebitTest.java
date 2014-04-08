package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionMaintenanceDebitTest {

	StrategyCommissionMaintenanceDebit comision = new StrategyCommissionMaintenanceDebit();
	int ownerage = 15;
	
	@Test
	public void testCalculateCommissionLessThan26() {
		
		//comprobamos que para los menores de 26 da como solucion 15
		assertEquals(15, comision.calculateCommission(ownerage));
		
	}
	
	@Test
	public void testCalculateCommissionMoreThan26() {
		//comprobamos que para mayores de 26 su solucion es 0
		ownerage = 30;
		assertEquals(0, comision.calculateCommission(ownerage));
		
	}

}
