package es.unileon.ulebank.strategy;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;

public class StrategyCommissionRevolvingEmissionTest {

	private Client owner;
	private Card card;
	private float quantity;
	StrategyCommissionRevolvingEmission emision;
	
	@Before
	public void SetUp(){
		
		owner = new Client();
		quantity = 1500;
		emision = new StrategyCommissionRevolvingEmission(owner, card, quantity);
	}
	
	
	/**
	 * Comprobamos que para varias edades, el resultado de la emision de la comision
	 * sigue siendo cero.
	 */
	
	@Test
	public void testCalculateCommission1() {
		assertTrue(emision.calculateCommission()==quantity);
	}

	@Test
	public void testCalculateCommission2() {
		assertFalse(emision.calculateCommission()!=quantity);
	}

}
