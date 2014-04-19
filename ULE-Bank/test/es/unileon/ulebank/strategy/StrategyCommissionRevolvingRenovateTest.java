package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;

public class StrategyCommissionRevolvingRenovateTest {

	/**
	 * @author Marta
	 * 
	 * Comprobamos que la comision para esta tarjeta siempre es 0
	 */
	private Client owner;
	private Card card;
	private float quantity;
	StrategyCommissionRevolvingRenovate renovate;

	@Before 
	public void SetUp(){
		owner = new Client();
		quantity = 1500;
		renovate = new StrategyCommissionRevolvingRenovate(owner, card, quantity);
	}
	
	
	@Test
	public void testCalculateCommission1() {
		
		assertEquals(quantity, renovate.calculateCommission(),0);
	}
	
	@Test
	public void testCalculateCommission2() {
		assertFalse(renovate.calculateCommission()!=quantity);
	}
}
