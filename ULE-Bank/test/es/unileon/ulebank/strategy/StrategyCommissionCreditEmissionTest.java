package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionCreditEmissionTest {

	StrategyCommissionCreditEmission emision;
	
	/**
	 * Comprobamos que la comision es siempre cero
	 */
	
	@Before
	public void SetUp(){
	
		emision = new StrategyCommissionCreditEmission();
	}
	
	
	@Test
	public void testCalculateCommission1() {
		assertTrue(emision.calculateCommission()==0);
	}

	@Test
	public void testCalculateCommission2() {
		assertFalse(emision.calculateCommission()!=0); 
		
	}
}


