package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyCommissionRenovateDebitTest {

	
	StrategyCommissionDebitRenovate renovate = new StrategyCommissionDebitRenovate();
	int ownerage = 18;
	
	@Test
	public void testCalculateCommission1() {
	
		//comprobamos que para cualquier edad, el resultado es de 30.
		assertEquals(20, renovate.calculateCommission(ownerage));
	
	}
	
	@Test
	public void testCalculateCommission2() {
		ownerage = 30;
		assertEquals(20, renovate.calculateCommission(ownerage));
		
	}

}
