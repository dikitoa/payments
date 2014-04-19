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
	private final float DEFAULT_COMMISSION = 0;
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
	 * Comprobamos que  el resultado de la emision final es la cantidad 
	 * más la comision por defecto.
	 */
	@Test
	public void testCalculateCommission1() {

		assertTrue(emision.calculateCommission()==(DEFAULT_COMMISSION+quantity));
	}

	
	@Test
	public void testCalculateCommission2() {
		
		quantity = 3000;
		emision = new StrategyCommissionDebitEmission(owner, card, quantity);
		assertEquals(DEFAULT_COMMISSION+quantity, emision.calculateCommission(), 0);
	}

}
