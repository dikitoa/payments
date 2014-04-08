package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Marta
 *
 */

public class StrategyCommissionRenovateCreditTest {

	StrategyCommissionRenovateCredit renovate = new StrategyCommissionRenovateCredit();
	int ownerage = 18;
	
	@Test
	public void testCalculateCommission() {
		
		//comprobamos que para cualquier edad, el resultado es de 30.
		assertEquals(30, renovate.calculateCommission(ownerage));
		
		ownerage = 30;
		assertEquals(30, renovate.calculateCommission(ownerage));
	}

}
