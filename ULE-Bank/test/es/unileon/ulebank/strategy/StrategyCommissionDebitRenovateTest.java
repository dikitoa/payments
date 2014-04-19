package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;

public class StrategyCommissionDebitRenovateTest {

	/**
	 * @author Marta
	 * 
	 * Comprobamos que la comision para esta tarjeta siempre es 20 mas la cantidad del cliente
	 */
	
	private Client owner;
	private Card card;
	private float quantity;
	private final float DEFAULT_COMMISSION = 20;
	StrategyCommissionDebitRenovate renovate;

	
	/**
	 * Inicializamos los valores para la realización de los tests
	 */
	@Before
	public void SetUp(){
		
		owner = new Client();
		quantity = 1500;
		renovate = new StrategyCommissionDebitRenovate(owner, card, quantity);
	}
	
	@Test
	public void testCalculateCommission1() {
	
		assertEquals(DEFAULT_COMMISSION + quantity, renovate.calculateCommission(),0);
	
	}
	
	@Test
	public void testCalculateCommission2() {
	
		quantity = 3000;
		assertEquals(DEFAULT_COMMISSION + quantity, renovate.calculateCommission(),0);
	
	}
}
