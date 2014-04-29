package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;

public class StrategyCommissionCreditEmissionTest {

	StrategyCommissionCreditEmission emision;
	Client owner;
	Card card;
	float quantity;

	/**
	 * Comprobamos que la comision es siempre cero
	 */
	
	@Before
	public void SetUp(){

		owner = new Client();
		quantity = 1500;
		emision = new StrategyCommissionCreditEmission(owner, null, quantity, 0);
		
	}
	
	
	@Test
	public void testCalculateCommission1() {
		assertTrue(emision.calculateCommission()==quantity);
	}

	@Test
	public void testCalculateCommission2() {
		assertFalse(emision.calculateCommission()!=quantity); 
		
	}
}


