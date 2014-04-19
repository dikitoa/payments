package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;


/**
 * 
 * @author Marta
 *
 */
public class StrategyCommissionDebitEmissionTest {

	private Client owner;
	private Card card;
	private float quantity;
	private final float DEFAULT_COMMISSION = 15;
	StrategyCommissionDebitEmission emision;
	
	/**
	 * Inicialiamos las variables
	 */
	
	@Before
	public void SetUp(){
	
		owner = new Client();
		quantity = 1500;
		emision = new StrategyCommissionDebitEmission(owner, card, quantity);
	}
	
	/**
	 * Comprobamos que  el resultado de la emision de la comision
	 * es siempre cero.
	 */
	
	@Test
	public void testCalculateCommission1() {

		assertTrue(emision.calculateCommission()==DEFAULT_COMMISSION+quantity);
	}

	@Test
	public void testCalculateCommission2() {
		
		quantity = 3000;
		assertEquals(DEFAULT_COMMISSION+quantity, emision.calculateCommission(), 0);
	}

}
