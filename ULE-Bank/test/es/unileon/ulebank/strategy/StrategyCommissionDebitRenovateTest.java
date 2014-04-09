package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionDebitRenovateTest {

	/**
	 * @author Marta
	 * 
	 * Comprobamos que la comision para esta tarjeta siempre es 20
	 */
	
	StrategyCommissionDebitRenovate renovate;
	
	@Before
	public void SetUp(){
		renovate = new StrategyCommissionDebitRenovate();
	}
	
	@Test
	public void testCalculateCommission1() {
	
		assertEquals(20, renovate.calculateCommission(),0);
	
	}
	
}
