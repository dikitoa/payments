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
	 * Comprobamos que si el cliente tiene menos de 26 anos 
	 * su comision no consta.
	 */
	
	@Test
	public void testCalculateCommission1() {
		
		owner.setAge(18);
		emision = new StrategyCommissionDebitMaintenance(owner, card, quantity);
		assertTrue(emision.calculateCommission()!=0);
	}

	
	@Test
	public void testCalculateCommission2() {
		
		owner.setAge(18);
		emision = new StrategyCommissionDebitMaintenance(owner, card, quantity);
		assertTrue(emision.calculateCommission()==quantity);
	}
	
	
	/**
	 * Comprobamos que para un cliente mayor de 26 anos 
	 * su emision de comision es la cuantia mas la comision.
	 */
	@Test
	public void testCalculateCommission3() {
		
		owner.setAge(30);
		emision = new StrategyCommissionDebitMaintenance(owner, card, quantity);
		assertFalse(emision.calculateCommission()==0);
	}

	
	@Test
	public void testCalculateCommission4() {
		
		owner.setAge(30);
		emision = new StrategyCommissionDebitMaintenance(owner, card, quantity);
		assertTrue(emision.calculateCommission()==(DEFAULT_COMMISSION+quantity));
	}
	

}
