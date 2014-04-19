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
public class StrategyCommissionDebitMaintenanceTest {

	private Client owner;
	private Card card;
	private float quantity;
	private final float DEFAULT_COMMISSION = 15;
	StrategyCommissionDebitMaintenance emision;
	
	/**
	 * Inicialiamos las variables
	 */
	
	@Before
	public void SetUp(){
	
		owner = new Client();
		quantity = 1500;
		emision = new StrategyCommissionDebitMaintenance(owner, card, quantity);
	}
	
	/**
	 * Comprobamos que  el resultado de la emision de la comision
	 * es siempre cero.
	 */
	
	@Test
	public void testCalculateCommission1() {
		owner.setAge(18);
		assertTrue(emision.calculateCommission()==0);
	}

	@Test
	public void testCalculateCommission2() {
		owner.setAge(18);
		assertEquals(DEFAULT_COMMISSION+quantity, emision.calculateCommission(), 0);
	}
	
	
	@Test
	public void testCalculateCommission3() {
		owner.setAge(30);
		assertFalse(emision.calculateCommission()!=0);
	}

	@Test
	public void testCalculateCommission4() {
		owner.setAge(30);
		assertTrue(emision.calculateCommission()==quantity);
	}
	

}
