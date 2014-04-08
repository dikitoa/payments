package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionRenovateRevolvingTest {

	
	StrategyCommissionRenovateRevolving renovate = new StrategyCommissionRenovateRevolving();
	int ownerage = 18;
	
	@Test
	/**Comprobamos que para cualquier edad, el resultado a devolver es cero
	 *  
	 */
	public void testCalculateCommission1() {
		
		assertEquals(0, renovate.calculateCommission(ownerage));
	}
	
	@Test
	public void testCalculateCommission() {
		
		ownerage = 30;
		assertEquals(0, renovate.calculateCommission(ownerage));
	}

	
}
