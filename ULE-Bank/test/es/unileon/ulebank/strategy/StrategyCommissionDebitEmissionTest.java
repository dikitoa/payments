package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrategyCommissionDebitEmissionTest {

	StrategyCommissionDebitEmission emision;
	
	@Before
	public void SetUp(){
		emision = new StrategyCommissionDebitEmission();
	}
	
	/**
	 * Comprobamos que  el resultado de la emision de la comision
	 * es siempre cero.
	 */
	
	@Test
	public void testCalculateCommission1() {
		assertTrue(emision.calculateCommission()==0);
	}

	@Test
	public void testCalculateCommission2() {
		assertFalse(emision.calculateCommission()!=0);
	}


}
