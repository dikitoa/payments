package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionEmissionRevolvingTest {

	StrategyCommissionRevolvingRenovate emision = new StrategyCommissionRevolvingRenovate();
	int ownerage = 18;
	
	/**
	 * Comprobamos que para varias edades, el resultado de la emision de la comision
	 * sigue siendo cero.
	 */
	@Test
	public void testCalculateCommission1() {
		
		assertEquals(0, emision.calculateCommission(ownerage));
	}

	@Test
	public void testCalculateCommission2() {
		
		ownerage = 35;
		assertEquals(0, emision.calculateCommission(ownerage));
	}

}
