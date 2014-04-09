package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionRevolvingRenovateTest {

	/**
	 * @author Marta
	 * 
	 * Comprobamos que la comision para esta tarjeta siempre es 0
	 */
	
	StrategyCommissionRevolvingRenovate renovate;

	@Before 
	public void SetUp(){
		renovate = new StrategyCommissionRevolvingRenovate();
	}
	
	
	@Test
	public void testCalculateCommission1() {
		
		assertEquals(0, renovate.calculateCommission(),0);
	}
	
}
