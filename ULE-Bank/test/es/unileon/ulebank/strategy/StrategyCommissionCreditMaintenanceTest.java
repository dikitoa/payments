package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;

/**
 * @author Marta
 * 
 * Comprobamos que la comision para esta tarjeta siempre es 25
 */

public class StrategyCommissionCreditMaintenanceTest {

	
	private Client owner;
	private Card card;
	private float quantity;
	private final float DEFAULT_COMMISSION = 25;
	StrategyCommissionCreditMaintenance comision;
	
	/**
	 * Inicializamos los parámetros en el Before
	 */
	@Before
	public void setUp(){
		
		owner = new Client();
		quantity = 1500;
		comision = new StrategyCommissionCreditMaintenance(owner, card, quantity);
	}
	
	
	@Test
	public void testCalculateCommission1() {
		
		assertEquals(DEFAULT_COMMISSION + quantity, comision.calculateCommission(), 0);
	}


	@Test
	public void testCalculateCommission2() {
		
		quantity=3000;
		comision = new StrategyCommissionCreditMaintenance(owner, card, quantity);
		assertEquals(DEFAULT_COMMISSION + quantity, comision.calculateCommission(), 0);
	}
}
