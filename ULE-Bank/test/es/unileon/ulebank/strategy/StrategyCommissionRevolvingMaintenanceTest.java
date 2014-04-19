package es.unileon.ulebank.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;

public class StrategyCommissionRevolvingMaintenanceTest {

	/**
	 * @author Marta
	 * 
	 * Comprobamos que la comision para esta tarjeta siempre es 0
	 */
	private Client owner;
	private Card card;
	private float quantity;
	StrategyCommissionRevolvingMaintenance comision;
	
	@Before
	public void SetUp(){
		
		owner = new Client();
		quantity = 1500;
		comision  = new StrategyCommissionRevolvingMaintenance(owner, card, quantity);
	}
	
	
	@Test
	public void testCalculateCommission() {
		
		
		assertEquals(quantity, comision.calculateCommission(),0);
	}

}
